package com.novare.minet.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.novare.minet.model.User;

public class ServiceUtil {

	private ServiceUtil() {
	}

	public static List<User> loadUsers() {
		List<User> users = new ArrayList<>();
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.setDateFormat(new SimpleDateFormat(DateUtil.DATE_FORMAT_PATTERN));
			CollectionType javaType = mapper.getTypeFactory().constructCollectionType(List.class, User.class);
			users = mapper.readValue(Paths.get("assets/user.json").toFile(), javaType);
			return users;
		} catch (Exception e) {
			return users;
		}
	}

	public static void saveUsers(List<User> users) throws JsonProcessingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setDateFormat(new SimpleDateFormat(DateUtil.DATE_FORMAT_PATTERN));
		String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(users);
		Files.write(Paths.get("assets/user.json"), json.getBytes());
	}

	public static <T> List<T> loadModel(Class<T> modelClass, String storage) {
		List<T> modelData = new ArrayList<>();
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.setDateFormat(new SimpleDateFormat(DateUtil.DATE_FORMAT_PATTERN));
			CollectionType javaType = mapper.getTypeFactory().constructCollectionType(List.class, modelClass);
			modelData = mapper.readValue(Paths.get(storage).toFile(), javaType);
			return modelData;
		} catch (Exception e) {
			e.printStackTrace();
			return modelData;
		}
	}

	public static <T> void saveModel(List<T> models, String storage) throws JsonProcessingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setDateFormat(new SimpleDateFormat(DateUtil.DATE_FORMAT_PATTERN));
		String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(models);
		Files.write(Paths.get(storage), json.getBytes());
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
