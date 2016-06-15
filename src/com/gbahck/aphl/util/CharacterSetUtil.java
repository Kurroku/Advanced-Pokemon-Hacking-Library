package com.gbahck.aphl.util;

import java.util.HashMap;
import java.util.Map;

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
 * temporarily character set implementation.
 * 
 * @author Phillip Groves
 *
 */
public class CharacterSetUtil {
	
	private static Map<Integer, String> characters = new HashMap<Integer, String>();
	
	public static String get( int value ) {
		if ( characters.containsKey( value ) )
			return characters.get( value );
		else
			return characters.get( 0xFF );
	}
	
	public static int valueOf( String character ) {
		if ( characters.containsValue( character ) ) {
			for ( int i = 0; i <= 0xFF; i++ ) {
				if ( characters.containsKey( i ) && characters.get( i ).equals( character ) ) {
					return i;
				}
			}
		}
		return 0;
	}
	
	static {
		// TODO: Load this from file instead
		characters.put( 0x00, " " );
		characters.put( 0x01, "�" );
		characters.put( 0x02, "�" );
		characters.put( 0x03, "�" );
		characters.put( 0x04, "�" );
		characters.put( 0x05, "�" );
		characters.put( 0x06, "�" );
		characters.put( 0x07, "�" );
		characters.put( 0x08, "�" );
		characters.put( 0x09, "�" );
		characters.put( 0x0B, "�" );
		characters.put( 0x0C, "�" );
		characters.put( 0x0D, "�" );
		characters.put( 0x0E, "�" );
		characters.put( 0x0F, "�" );
		characters.put( 0x10, "�" );
		characters.put( 0x11, "�" );
		characters.put( 0x12, "�" );
		characters.put( 0x13, "�" );
		characters.put( 0x14, "�" );
		characters.put( 0x15, "�" );
		characters.put( 0x16, "�" );
		characters.put( 0x17, "�" );
		characters.put( 0x19, "�" );
		characters.put( 0x1A, "�" );
		characters.put( 0x1B, "�" );
		characters.put( 0x1C, "�" );
		characters.put( 0x1D, "�" );
		characters.put( 0x1E, "�" );
		characters.put( 0x20, "�" );
		characters.put( 0x21, "�" );
		characters.put( 0x22, "�" );
		characters.put( 0x23, "�" );
		characters.put( 0x24, "�" );
		characters.put( 0x25, "�" );
		characters.put( 0x26, "�" );
		characters.put( 0x27, "�" );
		characters.put( 0x28, "�" );
		characters.put( 0x29, "�" );
		characters.put( 0x2A, "�" );
		characters.put( 0x2B, "�" );
		characters.put( 0x2D, "&" );
		characters.put( 0x2E, "+" );
		characters.put( 0x34, "[Lv]" );
		characters.put( 0x35, "=" );
		characters.put( 0x36, ";" );
		characters.put( 0x51, "�" );
		characters.put( 0x52, "�" );
		characters.put( 0x53, "[pk]" );
		characters.put( 0x54, "[mn]" );
		characters.put( 0x55, "[po]" );
		characters.put( 0x56, "[k�]" );
		characters.put( 0x57, "[bl]" );
		characters.put( 0x58, "[oc]" );
		characters.put( 0x59, "[k]" );
		characters.put( 0x5A, "�" );
		characters.put( 0x5B, "%" );
		characters.put( 0x5C, "(" );
		characters.put( 0x5D, ")" );
		characters.put( 0x68, "�" );
		characters.put( 0x6F, "�" );
		characters.put( 0x79, "[U]" );
		characters.put( 0x7A, "[D]" );
		characters.put( 0x7B, "[L]" );
		characters.put( 0x7C, "[R]" );
		characters.put( 0x85, "<" );
		characters.put( 0x86, ">" );
		characters.put( 0xA1, "0" );
		characters.put( 0xA2, "1" );
		characters.put( 0xA3, "2" );
		characters.put( 0xA4, "3" );
		characters.put( 0xA5, "4" );
		characters.put( 0xA6, "5" );
		characters.put( 0xA7, "6" );
		characters.put( 0xA8, "7" );
		characters.put( 0xA9, "8" );
		characters.put( 0xAA, "9" );
		characters.put( 0xAB, "!" );
		characters.put( 0xAC, "?" );
		characters.put( 0xAD, "." );
		characters.put( 0xAE, "-" );
		characters.put( 0xAF,"�" );
		characters.put( 0xB0, "..." );
		characters.put( 0xB1, "�" );
		characters.put( 0xB2, "�" );
		characters.put( 0xB3, "'" );
		characters.put( 0xB4, "'" );
		characters.put( 0xB5, "|m|" );
		characters.put( 0xB6, "|f|" );
		characters.put( 0xB7, "$" );
		characters.put( 0xB8, "," );
		characters.put( 0xB9, "*" );
		characters.put( 0xBA, "/" );
		characters.put( 0xBB, "A" );
		characters.put( 0xBC, "B" );
		characters.put( 0xBD, "C" );
		characters.put( 0xBE, "D" );
		characters.put( 0xBF, "E" );
		characters.put( 0xC0, "F" );
		characters.put( 0xC1, "G" );
		characters.put( 0xC2, "H" );
		characters.put( 0xC3, "I" );
		characters.put( 0xC4, "J" );
		characters.put( 0xC5, "K" );
		characters.put( 0xC6, "L" );
		characters.put( 0xC7, "M" );
		characters.put( 0xC8, "N" );
		characters.put( 0xC9, "O" );
		characters.put( 0xCA, "P" );
		characters.put( 0xCB, "Q" );
		characters.put( 0xCC, "R" );
		characters.put( 0xCD, "S" );
		characters.put( 0xCE, "T" );
		characters.put( 0xCF, "U" );
		characters.put( 0xD0, "V" );
		characters.put( 0xD1, "W" );
		characters.put( 0xD2, "X" );
		characters.put( 0xD3, "Y" );
		characters.put( 0xD4, "Z" );
		characters.put( 0xD5, "a" );
		characters.put( 0xD6, "b" );
		characters.put( 0xD7, "c" );
		characters.put( 0xD8, "d" );
		characters.put( 0xD9, "e" );
		characters.put( 0xDA, "f" );
		characters.put( 0xDB, "g" );
		characters.put( 0xDC, "h" );
		characters.put( 0xDD, "i" );
		characters.put( 0xDE, "j" );
		characters.put( 0xDF, "k" );
		characters.put( 0xE0, "l" );
		characters.put( 0xE1, "m" );
		characters.put( 0xE2, "n" );
		characters.put( 0xE3, "o" );
		characters.put( 0xE4, "p" );
		characters.put( 0xE5, "q" );
		characters.put( 0xE6, "r" );
		characters.put( 0xE7, "s" );
		characters.put( 0xE8, "t" );
		characters.put( 0xE9, "u" );
		characters.put( 0xEA, "v" );
		characters.put( 0xEB, "w" );
		characters.put( 0xEC, "x" );
		characters.put( 0xED, "y" );
		characters.put( 0xEE, "z" );
		characters.put( 0xEF, "|>|" );
		characters.put( 0xF0, ":" );
		characters.put( 0xF1, "�" );
		characters.put( 0xF2, "�" );
		characters.put( 0xF3, "�" );
		characters.put( 0xF4, "�" );
		characters.put( 0xF5, "�" );
		characters.put( 0xF6, "�" );
		characters.put( 0xF7, "|A|" );
		characters.put( 0xF8, "|V|" );
		characters.put( 0xF9, "|<|" );
		characters.put( 0xFA, "|nb|" );
		characters.put( 0xFB, "|nb2|" );
		characters.put( 0xFC, "|FC|" );
		characters.put( 0xFD, "|FD|" );
		characters.put( 0xFE, "|br|" );
		characters.put( 0xFF, "|end|" );
	}
}
