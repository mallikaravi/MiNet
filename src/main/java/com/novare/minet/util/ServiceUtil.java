package com.novare.minet.util;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;

public class ServiceUtil {

	private ServiceUtil() {
	}

	/**
	 * THis method is used for encryption of password.
	 * 
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String password) throws Exception {
		MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
		messageDigest.update(password.getBytes());
		byte[] digest = messageDigest.digest();
		StringBuilder encryptedHash = new StringBuilder();
		for (byte b : digest) {
			encryptedHash.append(String.format("%02x", b));
		}
		return encryptedHash.toString();
	}

	/**
	 * THis method is used to check the assets folder.If the name of assset folder
	 * changes,the program will not execute
	 * 
	 * @return
	 * @throws Exception
	 */
	public static boolean checkAssetFolder() throws Exception {
		if (Files.exists(Paths.get("assets"))) {
			return true;
		}
		throw new FileNotFoundException("Asset Folder doesn't exist, Bye !");
	}
}
