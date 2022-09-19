package com.novare.minet;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.novare.minet.action.WelcomeMenuAction;
import com.novare.minet.model.User;
import com.novare.minet.util.DateUtil;
import com.novare.minet.util.MenuContext;

public class MinetApp {

	public static void saveUsers(List<User> users) throws JsonProcessingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setDateFormat(new SimpleDateFormat(DateUtil.DATE_FORMAT_PATTERN));
		String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(users);
		System.out.println(json);
		Files.write(Paths.get("assets/user.json"), json.getBytes());
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

	private static String getAppHeader(User user) throws ParseException {
		StringBuilder builder = new StringBuilder();
		builder.append(String.format(" %70s %n", "").replace(' ', '*'));
		builder.append(String.format("* %-68s * %n", "Welcome to MiNet WareHouse Management System"));
		builder.append(String.format("* Name: %-45s Counter No: %4s *%n", "MALLfsfsfsfsfsfsfsfsfsESWARI".toUpperCase(),
				"0001"));
		builder.append(String.format("* Role: %-36s Date: %7s *%n", "CACsdfsdfsdfsdfsfsfsfsfsHIER", DateUtil.getNow()));
		builder.append(String.format(" %70s %n", "").replace(' ', '*'));
		builder.append("\n");
		builder.append(String.format(" %-30s | %5s | %10s | %15s %n", "Item Name", "Qty", "Price", "Amount"));
		builder.append(String.format(" %70s %n", "").replace(' ', '-'));
		builder.append(String.format(" %-30s  %6s  %11s  %16s %n", "Item Name", "Qty", "Price", "Amount"));
		builder.append("\n");
		builder.append(String.format(" %70s %n", "").replace(' ', '-'));
		builder.append(String.format(" Total Amount: %55s %n", "Amount"));
		builder.append(String.format(" %70s %n", "").replace(' ', '-'));
		builder.append(String.format(" Balance: %60s %n", "Amount"));
		builder.append(String.format(" %70s %n", "").replace(' ', '*'));
		builder.append(String.format(" %20s %-20s %10s %n", "", "Thank you come again".toUpperCase(), ""));
		builder.append(String.format(" %70s %n", "").replace(' ', '*'));

		return builder.toString();
	}

	public static void main(String[] args) throws ParseException {
//		System.out.println(getAppHeader(null));
//		System.exit(0);
//		try {
////			System.out.println(Ids.get().getUserId());
////			Ids id=Ids.get();
////			id.getInventoryHistoryId();
////			id.done();
//			;
//			User user = new User("FulleName", "UserName", "Password", "Email", "phoneNumber", "address", Role.ADMIN);
//			Transaction transaction = new Transaction(DateUtil.toDate(LocalDateTime.now()), user, TransactionType.SOLD,
//					1000);
//			Product product = new Product("FullName", "Short Name", "Description", 100, 80, 1);
//
//			TransactionItems item = new TransactionItems(product, 2, product.getSellingPrice() * 2);
//			item.setTransaction(transaction);
//
//			transaction.addTransactionItems(item);
//			ObjectMapper mapper = new ObjectMapper();
//			mapper.setDateFormat(new SimpleDateFormat(DateUtil.DATE_FORMAT_PATTERN));
//			String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(transaction);
//			System.out.println(json);
//			Files.write(Paths.get("assets/transaction.json"), json.getBytes());
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		User currentUser = null;
		try {
			new WelcomeMenuAction(MenuContext.WELCOME, currentUser);
		} catch (Exception e) {
			System.out.println("Error, Not able to run the application !\n Due to the " + e.getMessage());
		}
//		List<User> product = List.of(
//				new User("FulleName", "UserName", "Password", "Email", "phoneNumber", "address", Role.ADMIN),
//				new User("FulleName", "UserName", "Password", "Email", "phoneNumber", "address", Role.ADMIN));
//		try {
//			saveUsers(product);
//			List<User> loadUsers = loadUsers();
//			System.out.println(loadUsers);
////			for (User user : loadUsers) {
////				product.add(user);
////			}
////			saveUsers(product);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
}
