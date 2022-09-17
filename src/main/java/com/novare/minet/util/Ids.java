package com.novare.minet.util;

import java.nio.file.Files;
import java.nio.file.Paths;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Generating the unique sequential Ids 
 */
public class Ids {

	private int userId;
	private int inventoryId;
	private int inventoryHistoryId;
	private int orderId;
	private int orderHistoryId;
	private int productId;
	private int productHistoryId;
	private int supplierId;
	private int transactionId;
	private int transactionItemId;

	private Ids() {
	}

	/**
	 * @return the userId
	 */
	public int getUserId() {

		return userId++;
	}

	/**
	 * @return the inventoryId
	 */
	public int getInventoryId() {

		return inventoryId++;
	}

	/**
	 * @return the inventoryHistoryId
	 */
	public int getInventoryHistoryId() {

		return inventoryHistoryId++;
	}

	/**
	 * @return the orderId
	 */
	public int getOrderId() {

		return orderId++;
	}

	/**
	 * @return the orderHistoryId
	 */
	public int getOrderHistoryId() {

		return orderHistoryId++;
	}

	/**
	 * @return the productId
	 */
	public int getProductId() {

		return productId++;
	}

	/**
	 * @return the productHistoryId
	 */
	public int getProductHistoryId() {

		return productHistoryId++;
	}

	/**
	 * @return the supplierId
	 */
	public int getSupplierId() {

		return supplierId++;
	}

	/**
	 * @return the transactionId
	 */
	public int getTransactionId() {

		return transactionId++;
	}

	/**
	 * @return the transactionItemId
	 */
	public int getTransactionItemId() {

		return transactionItemId++;
	}

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	public void close() {
		try {
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);
			Files.write(Paths.get("assets/ids.json"), json.getBytes());
		} catch (Exception e) {
		}
	}

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	public static Ids get() {
		try {
			ObjectMapper mapper = new ObjectMapper();
			Ids readValue = mapper.readValue(Paths.get("assets/ids.json").toFile(), Ids.class);
			return readValue;
		} catch (Exception e) {
			return new Ids();
		}
	}
}
