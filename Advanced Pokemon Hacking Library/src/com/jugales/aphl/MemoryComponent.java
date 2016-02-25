package com.jugales.aphl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.MalformedParametersException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * 
 * This memory component represents the Read-Only Memory (or ROM) we are interacting with and includes 
 * functionality to interact with the data it possesses such as Strings, integers, pointers, header info, ect. </br>
 * </br>
 * All changes to the ROM are not final until the save() function is used, and any changes be reverted to the 
 * previous save's (or initial ROM load) information using the revert() function.
 * 
 * @author Kurroku
 */
public class MemoryComponent {
	
	/** Represents the ROM file itself */
	protected final File romFile;
	
	/** Represents the ROM byte information since any changes were made.  */
	protected ByteBuffer romBuffer;
	
	/** Represents the past ROM save information, used in the case of a revert */
	protected ByteBuffer backupBuffer;
	
	/** The ROM header with information on the ROM title, gamecode, version, ect */
	private MemoryHeader romHeader;
	
	/**
	 * 
	 * @param filePath The path to the ROM file
	 * @throws IOException 
	 */
	public MemoryComponent(String filePath) throws IOException {
		this (new File(filePath));
	}
	
	/**
	 * 
	 * @param file The ROM file
	 * @throws IOException
	 */
	public MemoryComponent(File file) throws IOException {
		// load the ROM file's bytes into an array
		InputStream is = new FileInputStream(file);
		byte[] bytes = new byte[(int) file.length()];
		is.read(bytes, 0, (int) file.length());
		is.close();
		
		// wrap the ROM bytes into buffers (one for editing, one for backup), then read the header and set file
		this.romBuffer = ByteBuffer.wrap(bytes);
		this.backupBuffer = ByteBuffer.wrap(bytes);
		this.romHeader = new MemoryHeader(this);
		this.romFile = file;
		
		// set the byte order of our buffers so that values we get and put are automatically converted
		this.romBuffer.order(ByteOrder.LITTLE_ENDIAN);
		this.backupBuffer.order(ByteOrder.LITTLE_ENDIAN);
	}
	
	/**
	 * Retrieve a value from the ROM buffer at a specific offset. The data type returned is determined by the 
	 * value of the {@link DataType} parameter. Possible data types include byte, character, short, integer, and 
	 * pointer. All values are read from the ROM in little endian byte order.
	 * 
	 * @param location The offset to read a value from
	 * @param type The data type to return
	 * @return The value read from the ROM
	 */
	public int get(int location, DataType type) {
		romBuffer.position(location);
		return get(type);
	}
	
	/**
	 * Retrieve a value from the ROM at the current buffer location. The data type returned is determined by the 
	 * value of the {@link DataType} parameter. Possible data types include byte, character, short, integer, and 
	 * pointer. All values are read from the ROM in little endian byte order and are unsigned.
	 * 
	 * @param type The data type to return
	 * @return The value read from the ROM
	 */
	public int get(DataType type) {
		switch (type) {
		case BYTE: // intended. char and byte have the same read, so we don't do it twice
		case CHARACTER:
			return romBuffer.get() & 0xFF;
		case SHORT:
			return romBuffer.getShort() & 0xFFFF;
		case INTEGER:
			return romBuffer.getInt() & 0xFFFFFFFF;
		case POINTER:
			return romBuffer.getInt() & 0x1FFFFFFF;
		default:
			return 0;
		}
	}
	
	/**
	 * Reads a raw ASCII string from the ROM at a specific location and length. 
	 * 
	 * @param length The length of characters to read
	 * @param location The location to read a string at
	 * @return The string read from the ROM
	 */
	public String getAsciiString(int length, int location) {
		romBuffer.position(location);
		
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < length; i++)
			builder.append((char) get(DataType.CHARACTER));
			
		return builder.toString();
	}
	
	public void putAsciiString(String characters, int location) {
		romBuffer.position(location);
		
		for (int i = 0; i < characters.length(); i++) {
			put (characters.charAt(i), DataType.CHARACTER);
		}
	}
	
	/**
	 * Reads a game-oriented string from the ROM at a specific location. This function uses a "smart reading" 
	 * loop that will stop when the value of 0xFF is hit, since most game-oriented strings end at that point. 
	 * All strings are converted automatically from ASCII to the proprietary Pokemon character set.
	 * 
	 * @param location The location to read a string from
	 * @return The string read from the ROM
	 */
	public String getGameString(int location) {
		return getGameString(-1, location);
	}
	
	/**
	 * Reads a game-oriented string from the ROM at a specific location and length. 
	 * All strings are converted automatically from ASCII to the custom Pokemon character set. The maximum 
	 * length you can read is 0x11111111.
	 * 
	 * @param length The length of characters to read
	 * @param location The location to read a string from
	 * @return The string read from the ROM
	 */
	public String getGameString(int length, int location) {
		if (length == -1)
			length = 0x11111111;
		
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < length; i++) {
			byte current = (byte) get(DataType.BYTE);
			char character = 0; // TODO: Exchange characters via Pokemon charset conversion
			if(length == 0x11111111 && current == 0xFF) // Game strings usually end in 0xFF
				break;
			builder.append(character);
		}
		
		return builder.toString();
	}
	
	/**
	 * Writes a value into the ROM at a specific buffer location. The data type to write (which determines 
	 * how many bytes) is determined by the value of the {@link DataType} parameter. Possible data types include 
	 * byte, character, short, integer, and pointer. All values are written in little endian automatically.
	 * 
	 * @param value The value to write to the ROM buffer
	 * @param location The address to write at
	 * @param type The data type to write
	 */
	public void put(int value, int location, DataType type) {
		romBuffer.position(location);
		put(value, type);
	}
	
	/**
	 * Writes a value into the ROM at the current buffer location. The data type to write (which determines 
	 * how many bytes) is determined by the value of the {@link DataType} parameter. Possible data types include 
	 * byte, character, short, integer, and pointer. All values are written in little endian automatically.
	 * 
	 * @param value The value to write to the ROM buffer
	 * @param type The data type to write
	 */
	public void put(int value, DataType type) {
		switch (type) {
		case BYTE:
			if (value < 0 || value > 255)
				throw new MalformedParametersException ("Attempted to write byte of value: " + value + " at address: " + romBuffer.position());
			
			romBuffer.put((byte) (value & 0xFF));
			break;
		case CHARACTER:
			if (value < 0 || value > 255)
				throw new MalformedParametersException ("Attempted to write char of value: " + value + " at address: " + romBuffer.position());
			
			romBuffer.put((byte) (value & 0xFF));
			break;
		case SHORT:
			if (value < 0 || value > 65535)
				throw new MalformedParametersException ("Attempted to write short of value: " + value + " at address: " + romBuffer.position());
			
			romBuffer.putShort((short) (value & 0xFFFF));
			break;
		case INTEGER: // Same as "pointer", so instead of writing twice we just don't set a break keyword
		case POINTER:
			if (value < 0 || value > 4294967295L)
				throw new MalformedParametersException ("Attempted to write integer of value: " + value + " at address: " + romBuffer.position());
			
			romBuffer.putInt((int) value & 0xFFFFFFFF);
			break;
		default:
			break;
		}
	}
	
	/**
	 * Write a new header to the ROM buffer. This change will not be permanent until the save() function is called.
	 * 
	 * @param header The new ROM header to be written to the ROM buffer
	 */
	public void setHeader(MemoryHeader header) {
		// TODO: Verify that the new header isn't the same as the old
		// TODO: If it is the same, don't write. That's pointless.
		// TODO: Verify that the values in the header are valid (no null title for example).
		// TODO: If all is well, write the values of the new header to the respective ROM header locations.
		putAsciiString(header.getGameCode(), 0xAC);
		putAsciiString(header.getMakerCode(), 0xB0);
		putAsciiString(header.getTitle(), 0xA0);
		put(header.getVersion(), 0xBC, DataType.BYTE);
	}
	
	/**
	 * 
	 * @return The current ROM header and its contents. 
	 */
	public MemoryHeader getHeader() {
		return romHeader;
	}
	
	/**
	 * Reverts all changes made to the ROM buffer since the last save() function call (or the initial ROM load if 
	 * a new save hasn't occurred). 
	 */
	public void revert() {
		this.romBuffer = backupBuffer.duplicate();
	}
	
	/**
	 * Saves all current changes to the ROM buffer. This function cannot be undone, so be careful on when you save. 
	 * If revert() is called after this function, the ROM buffer will be reset to the last save point.
	 */
	public void save() {
		this.backupBuffer = romBuffer.duplicate();
		
		try {
			FileOutputStream fos = new FileOutputStream(romFile, false);
			
			fos.write(romBuffer.array());
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Represents a valid primitive data type (plus "pointer") that can be read from the ROM. {@link String} is not 
	 * included via this enumerated type as it's a bit harder to IO to/from the ROM buffer.
	 * 
	 * @author Kurroku
	 */
	public enum DataType {
		BYTE,
		CHARACTER,
		SHORT,
		INTEGER,
		POINTER;
	}
}