/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthadvisor.encodedmd5;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author khattout
 */
public class MD5Password {
    
	public static String getEncodedPassword(String key) {
	  byte[] uniqueKey = key.getBytes();
	  byte[] hash = null;
	  try {
		hash = MessageDigest.getInstance("MD5").digest(uniqueKey);
	  } catch (NoSuchAlgorithmException e) {
		throw new Error("no MD5 support in this VM");
	  }
	  StringBuffer hashString = new StringBuffer();
	  for ( int i = 0; i < hash.length; ++i ) {
		String hex = Integer.toHexString(hash[i]);
		if ( hex.length() == 1 ) {
		  hashString.append('0');
		  hashString.append(hex.charAt(hex.length()-1));
		} else {
		  hashString.append(hex.substring(hex.length()-2));
		}
	  }
	  return hashString.toString();
	}

  public static boolean testPassword(String clearTextTestPassword,
				   String encodedActualPassword)
				   throws NoSuchAlgorithmException
	{
	String encodedTestPassword = MD5Password.getEncodedPassword(
					  clearTextTestPassword);

	return (encodedTestPassword.equals(encodedActualPassword));
	}
}
