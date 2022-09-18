package com.novare.minet.view;

import java.io.Console;
import java.util.List;
import java.util.Scanner;

import com.novare.minet.util.PrintHandler;

public abstract class BaseView {
	private final Scanner scanner;

	public BaseView(String title) {
		this.scanner = new Scanner(System.in);
		PrintHandler.clearScreen();
		PrintHandler.appTitle();
		setTitle(title);
	}

	public abstract List<String> getMenuOptions();

	public abstract void printNavigationMenu();

	public void setTitle(String title) {
		System.out.println(title);
	}

	public void setMenuOptions(List<?> menuOptions) {
		PrintHandler.optionList(menuOptions);
		printNavigationMenu();

	}

	public void setMenuOptionsInRow(List<?> menuOptions) {
		PrintHandler.optionListInRow(menuOptions);
	}

	protected Scanner getUserTerminal() {
		return scanner;
	}

	public void goodBye() {
		System.exit(0);
	}

	public String readPassword() {
		Console console = System.console();
		if (console == null) {
			printMessage("Enter Password: ");
			return getUserText();
		}
		char[] pwd = console.readPassword("Enter Password: ");
		return new String(pwd);
	}

	public void printSaveMessage() {
		System.out.println("Data successfully Saved/Updated");
	}

	public void printInvalidOption() {
		System.out.println("Invalid option");
	}

	public void printUserRequest() {
		System.out.print("Choose an option and press enter: ");
	}

	public void printMessage(String message) {
		System.out.print(message);
	}

	public boolean askConfirmationYesOrNo() {
		String input = getUserTerminal().nextLine();
		if (input.isEmpty()) {
			throw new IllegalArgumentException();
		}

		return input.equalsIgnoreCase("Yes") || input.equalsIgnoreCase("y");
	}

	public int askUserToChooseIndexFromList() {
		printUserRequest();
		String input = getUserTerminal().nextLine();
		if (input.isEmpty()) {
			throw new IllegalArgumentException();
		}
		try {
			return Integer.parseInt(input);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException();
		}
	}

	public boolean waitForDecision() {
		printMessage("\n\n");
		printMessage("[C] Continue\n");
		printMessage("[Q] Quit\n");
		printMessage("Options: ");
		boolean wait = false;
		while (!wait) {
			String input = getUserTerminal().nextLine();
			wait = input.equalsIgnoreCase("C") || input.equalsIgnoreCase("Q");
			if (input.equalsIgnoreCase("Q")) {
				printMessage("BYE !");
				System.exit(0);
			}
			if(input.equalsIgnoreCase("C")) {
				return true;
			}
		}
		return wait;

	}

	public int getUserInput() {
		String input = getUserTerminal().nextLine();
		try {
			int selection = Integer.parseInt(input);
			return selection;
		} catch (Exception exception) {
			printInvalidOption();
			printUserRequest();
			return getUserInput();
		}
	}

	public String getUserText() {
		try {
			String input = getUserTerminal().nextLine();
			if (input.isEmpty()) {
				throw new IllegalArgumentException();
			}
			return input;
		} catch (Exception exception) {
			printInvalidOption();
			printUserRequest();
			return getUserText();
		}

	}

	public Double getUserInputDouble() {
		String input = getUserTerminal().nextLine();
		try {
			Double selection = Double.parseDouble(input);
			return selection;
		} catch (Exception exception) {
			printInvalidOption();
			printUserRequest();
			return getUserInputDouble();
		}
	}

	public int getSelectedOptionFromMenu(int menuItems) {
		try {
			int selection = askUserToChooseIndexFromList();
			if (selection < 0 || selection > menuItems) {
				throw new ArrayIndexOutOfBoundsException();
			}
			return selection;
		} catch (Exception exception) {
			printInvalidOption();
			return getSelectedOptionFromMenu(menuItems);
		}
	}

	public String askUserName() {
		printMessage("Enter User name: ");
		return getUserText();

	}

	public String askUserPassword() {
		try {
			String input = readPassword();
			if (input.isEmpty()) {
				throw new IllegalArgumentException();
			}
			return input;
		} catch (Exception exception) {
			printInvalidOption();
			printUserRequest();
			return readPassword();
		}
	}

}
