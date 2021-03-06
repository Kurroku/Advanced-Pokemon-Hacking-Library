package com.gbahck.aphl.world.lvl.struct;

import com.gbahck.aphl.MemoryBuffer;

/*****************************************************************************\ 
 *                                                                           * 
 *          .8.            8 888888888o     8 8888        8   8 8888         * 
 *         .888.           8 8888    `88.   8 8888        8   8 8888         * 
 *        :88888.          8 8888     `88   8 8888        8   8 8888         * 
 *       . `88888.         8 8888     ,88   8 8888        8   8 8888         * 
 *      .8. `88888.        8 8888.   ,88'   8 8888        8   8 8888         * 
 *     .8`8. `88888.       8 888888888P'    8 8888888888888   8 8888         * 
 *    .8' `8. `88888.      8 8888           8 8888        8   8 8888         * 
 *   .8'   `8. `88888.     8 8888           8 8888        8   8 8888         * 
 *  .888888888. `88888.    8 8888           8 8888        8   8 8888         * 
 * .8'       `8. `88888.   8 8888           8 8888        8   8 888888888888 * 
 *                                                                           * 
 *                     ADVANCED POK�MON  HACKING LIBRARY                     * 
 *                                                                           * 
 *     A Java library for developers interested in helping ROM hackers.      * 
 *                                                                           * 
 *                Copyright (C) 2016  P. Groves, A. Nicholi                  * 
 *                                                                           * 
 * This program is free software; you can redistribute it and/or modify it   * 
 * under the terms of the GNU General Public License as published by the     * 
 * Free Software Foundation; either version 2 of the License, or (at your    * 
 * option) any later version.                                                * 
 *                                                                           * 
 * This program is distributed in the hope that it will be useful, but       * 
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANT-      * 
 * ABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the LICENSE file for    * 
 * more details.                                                             * 
 *                                                                           * 
 * You should have received a copy of the GNU General Public License along   * 
 * with this program; if not, write to the Free Software Foundation, Inc.,   * 
 * 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.             * 
\*****************************************************************************/

/**
 * <caption>3rd Generation Level Footer Data Structure. Size: 26 bytes</caption>
 * <table style="border: 1px solid">
 * <tr><th>Offset</th><th>Field</th><th>Size</th></tr>
 * <tr><td>0</td><td>Level Width</td><td>4 Bytes</td></tr>
 * <tr><td>4</td><td>Level Height</td><td>4 Bytes</td></tr>
 * <tr><td>8</td><td>Border Pointer</td><td>4 Bytes</td></tr>
 * <tr><td>12</td><td>Tile Structure Pointer</td><td>4 Bytes</td></tr>
 * <tr><td>16</td><td>Primary Tileset Pointer</td><td>4 Bytes</td></tr>
 * <tr><td>20</td><td>Secondary Tileset Pointer</td><td>4 Bytes</td></tr>
 * <tr><td>24</td><td>Border Width</td><td>1 Byte</td></tr>
 * <tr><td>25</td><td>Border Height</td><td>1 Byte</td></tr
 * </table>
 * @author Phillip Groves
 *
 */
public class LevelFooter {

	private final MemoryBuffer memory;
	
	private final int address;
	
	public LevelFooter( MemoryBuffer memory, int address ) {
		this.memory = memory;
		this.address = address;
	}
	
	public int getWidth() {
		return memory.getInteger( address );
	}
	
	public int getHeight() {
		return memory.getInteger( address + 4 );
	}
	
	public int getBorderAddress() {
		return memory.getPointer( address + 8 );
	}
	
	public int getTileLayoutAddress() {
		return memory.getPointer( address + 12 );
	}
	
	public int getPrimaryTilesetAddress() {
		return memory.getPointer( address + 16 );
	}
	
	public int getSecondaryTilesetAddress() {
		return memory.getPointer( address + 20 );
	}
	
	public int getBorderWidth() {
		return memory.get( address + 24 );
	}
	
	public int getBorderHeight() {
		return memory.get( address + 25 );
	}
}