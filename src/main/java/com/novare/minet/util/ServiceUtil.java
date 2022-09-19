package com.novare.minet.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.novare.minet.model.Inventory;
import com.novare.minet.model.Order;
import com.novare.minet.model.User;

public class ServiceUtil {

	private ServiceUtil() {
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

	public static void saveUsers(List<User> users) throws JsonProcessingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setDateFormat(new SimpleDateFormat(DateUtil.DATE_FORMAT_PATTERN));
		String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(users);
		Files.write(Paths.get("assets/atm.json"), json.getBytes());
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

	public static String printOrderDetails(Order order, Inventory inventory, User currentUser) {
		StringBuilder builder = new StringBuilder();
		builder.append(String.format("| %62s |%n", "").replace(' ', '-'));
		builder.append(String.format("| Hello,%-55s |%n", currentUser.getFullName()));
		builder.append(String.format("| %-19s:%42s |%n", "Order Status", order.getStatus()));
		builder.append(String.format("| %62s |%n", "").replace(' ', '-'));
		return builder.toString();
	}

	public static String printHeader(User currentUser) throws ParseException {
		return printHeader(currentUser, DateUtil.getNow());
	}

	public static String printHeader(User currentUser, String time) throws ParseException {
		String fullName = currentUser == null ? "xxxxxxxxxxx" : currentUser.getFullName();
		String name = currentUser == null ? "xxxxxxxxxxx" : currentUser.getRole().name();

		StringBuilder builder = new StringBuilder();
		builder.append(String.format(" %70s %n", "").replace(' ', '*'));
		builder.append(String.format("* %-68.68s * %n", "WELCOME TO MINET WAREHOUSE MANAGEMENT SYSTEM"));
		builder.append(String.format("* NAME: %-45.45s COUNTER NO: %4s *%n", fullName.toUpperCase(), "0001"));
		builder.append(String.format("* ROLE: %-36s DATE: %7s *%n", name.toUpperCase(), time));
		builder.append(String.format(" %70s %n", "").replace(' ', '*'));

		return builder.toString();
	}

}
