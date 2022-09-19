package com.novare.minet.view;

import java.io.Console;
import java.util.List;
import java.util.Scanner;

import com.novare.minet.util.PrintHandler;

public abstract class BaseView {
	private final Scanner scanner;

	public BaseView(String menuTitle) {
		this("** Welcome To MiNet **", menuTitle);
	}

	public BaseView(String appHeader, String menuTitle) {
		this.scanner = new Scanner(System.in);
		PrintHandler.clearScreen();
		setAppHeader(appHeader);
		setMenuTitle(menuTitle);
	}

	public abstract List<String> getMenuOptions();

	public abstract void printNavigationMenu();

	public void setAppHeader(String appHeader) {
		System.out.println(appHeader);
		System.out.println(); // on purpose to make a space between the title
	}

	public void setMenuTitle(String title) {
		System.out.println(title);
	}

	public void setMenuOptions(List<?> menuOptions) {
		setMenuOptions(menuOptions, true);
	}

	public void setMenuOptions(List<?> menuOptions, boolean navigation) {
		PrintHandler.optionList(menuOptions);
		if (navigation) {
			printNavigationMenu();
		}

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
			return getUserText("Enter Password: ");
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
			if (input.equalsIgnoreCase("C")) {
				return true;
			}
		}
		return wait;

	}

	public int getUserInput() {
		return getUserInput("");
	}

	public int getUserInput(String message) {
		try {
			printMessage(message);
			String input = getUserTerminal().nextLine();
			int selection = Integer.parseInt(input);
			return selection;
		} catch (Exception exception) {
			printInvalidOption();
			return getUserInput(message);
		}
	}

	public String getUserText(String message) {
		try {
			printMessage(message);
			String input = getUserTerminal().nextLine();
			if (input.isEmpty()) {
				throw new IllegalArgumentException();
			}
			return input;
		} catch (Exception exception) {
			printInvalidOption();
			return getUserText(message);
		}

	}

	public Double getUserInputDouble(String message) {
		try {
			printMessage(message);
			String input = getUserTerminal().nextLine();
			Double selection = Double.parseDouble(input);
			return selection;
		} catch (Exception exception) {
			printInvalidOption();
			return getUserInputDouble(message);
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
		return getUserText("Enter User name: ");

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

	public void displayResultNotFound() {
		printMessage("");
		printMessage("-----------------------------\n");
		printMessage("No results found ! \n");
		printMessage("-----------------------------\n");
		printMessage("");
	}

	public int askForCreate(List<?> items) {
		printMessage("Select the item for create: ");
		setMenuOptionsInRow(items);
		int selection = getSelectedOptionFromMenu(items.size()) - 1;
		printMessage("Do you want to edit " + items.get(selection) + " [Yes/No]: ");
		return askConfirmationYesOrNo() ? selection : -1;
	}

	public int askForEdit(List<?> items) {
		printMessage("Select the item for edit: ");
		setMenuOptionsInRow(items);
		int selection = getSelectedOptionFromMenu(items.size()) - 1;
		printMessage("Do you want to edit " + items.get(selection) + " [Yes/No]: ");
		return askConfirmationYesOrNo() ? selection : -1;

	}

	public int askForDelete(List<?> items) {
		printMessage("Select the item for delete: ");
		setMenuOptionsInRow(items);
		int selection = getSelectedOptionFromMenu(items.size()) - 1;
		printMessage("Do you want to delete " + items.get(selection) + " [Yes/No]: ");
		return askConfirmationYesOrNo() ? selection : -1;

	}

	public String askSearch() {
		return getUserText("Enter Search keyword: ");
	}

}
