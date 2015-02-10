//Copyright (C) 2015 CarbideWolf

//This program is free software: you can redistribute it and/or modify it under the terms of version 3 of the GNU General Public License as published by the Free Software Foundation.
//The program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
//See the GNU General Public License for more details.
//You should  have received a copy of the GNU General Public License along with this program. If not, see http://www.gnu.org/licenses/

package com.carbidewolf;

import gnu.crypto.*;
import gnu.crypto.hash.*;
import gnu.crypto.util.*;

public class Main
{
	public static void main(String args[])
	{
		//Call the window constructor
		new Window();
	}
	
	public static void selector(String password, int algorithm)
	{
		//Create message digest object
		IMessageDigest md = null;
		
		//Set value of message digest object depending on algorithm selected
		switch(algorithm)
		{
			case 0 : md = HashFactory.getInstance(Registry.HAVAL_HASH);
				break;
			case 1 : md = HashFactory.getInstance(Registry.MD2_HASH);
				break;
			case 2 : md = HashFactory.getInstance(Registry.MD4_HASH);
				break;
			case 3 : md = HashFactory.getInstance(Registry.MD5_HASH);
				break;
			case 4 : md = HashFactory.getInstance(Registry.RIPEMD128_HASH);
				break;
			case 5 : md = HashFactory.getInstance(Registry.RIPEMD160_HASH);
				break;
			case 6 : md = HashFactory.getInstance(Registry.TIGER_HASH);
				break;
			case 7 : md = HashFactory.getInstance(Registry.SHA160_HASH);
				break;
			case 8 : md = HashFactory.getInstance(Registry.SHA256_HASH);
				break;
			case 9 : md = HashFactory.getInstance(Registry.SHA384_HASH);
				break;
			case 10 : md = HashFactory.getInstance(Registry.SHA512_HASH);
				break;
			case 11 : md = HashFactory.getInstance(Registry.WHIRLPOOL_HASH);
				break;
		}
		
		//Call the hash method
		hash(password, md);
	}
	
	public static void hash(String password, IMessageDigest md)
	{
		//Turn the string into a byte array
		byte[] inputArray = password.getBytes();
		
		//Add the input to the message digest object
		md.update(inputArray, 0, inputArray.length);
		
		//Hash the input
		byte[] digest = md.digest();
		
		//Turn the output of the hasher into a string
		String hash = Util.toString(digest).toLowerCase();
		
		//Put the output in the output box
		Window.hashField.setText(hash);
	}
}