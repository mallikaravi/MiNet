package com.novare.minet.util;

import java.util.List;

public class PrintHandler {
	
	public static void appTitle() {
		System.out.println("** Welcome To MiNet **");
		System.out.println(); // on purpose to make a space between the title
	}

	public static void optionLogout() {
		System.out.println("[0] Logout");
	}

	public static void optionQuit() {
		System.out.println("[0] Quit");
	}

	public static void optionBackToMainMenu() {
		System.out.println("[0] Back");
	}

	public static void optionList(List<?> options) {
		for (int index = 0; index < options.size(); index++) {
			int number = index + 1;
			String label = options.get(index).toString();

			System.out.println("[" + number + "] " + label);
		}
	}

	public static void optionListWithoutIndex(List<?> options) {
		for (Object label : options) {
			System.out.println(label.toString());
		}
	}

	public static void optionListInRow(List<?> options) {
		for (int index = 0; index < options.size(); index++) {
			int number = index + 1;

			if (index % 4 == 0) {
				System.out.println();
			}
			String label = options.get(index).toString();
			System.out.format("[%-2d] %-40s ", number, label);
		}
		System.out.println();
	}

	/**
	 * Method to clear screen in the terminal.I have written a condition to clear
	 * the screen in all the operating systems.
	 * 
	 */
	public static void clearScreen() {

		try {
			String terminal = System.getenv("SHELL");
			if (terminal == null) {
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			} else {
				throw new Exception("Found the shell terminal.");
			}
		} catch (Exception e) {
			String clearScreenASCIICode = "\033[H\033[2J";
			System.out.print(clearScreenASCIICode);
			System.out.flush();
		}
	}

	/**
	 * This method is to parse and convert the input string into Integer value,
	 * otherwise it will return -1 which is invalid option
	 * 
	 * @param userInput, user entered value in the terminal @return, integer value
	 */
	public static int readInput(String userInput) {
		try {
			return Integer.parseInt(userInput);
		} catch (NumberFormatException e) {
			return -1;
		}
	}

}
