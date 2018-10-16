package info.andrewwilliams.libraryapp;
/*
* Author: Andrew Williams
* Description: This is a library catolog program
*/
import java.util.InputMismatchException;
import java.util.Scanner;

public class Logic {
	static Scanner keyboard = new Scanner(System.in);
	
	public static void printMenu() {
		System.out.print("=========================================\n" +
						 "                  Menu                   \n" +
						 "=========================================\n" +
				 		 "1.  Add a new book\n"                        +
						 "2.  Remove a book\n"                         +
						 "3.  Check out a book\n"                      +	
						 "4.  Check in a book\n"                       +
						 "5.  Display all book details\n"                  +
						 "6.  Display all available book details\n"    +
						 "7.  Display all checked out books\n"         +
						 "8.  Exit Program\n"
						);
	}
	
	public static void menuLogic() {
		int input;
		int bookCount = 0;
		Book[] catalog = new Book[100];
		
		do {
			printMenu();
			input = keyboard.nextInt();
			
			switch(input) {
			case 1 : if(bookCount < 100) {
					 	addBook(catalog, bookCount);
					 	bookCount++;
					 } else {
						 System.out.println("Catalog is at MAXIMUM CAPACITY. Remove items to add more.");
					 }
					 break;
			case 2 : catalog = removeBook(catalog, bookCount);
					 bookCount--;
					 break;
			case 3 : checkOutBook(catalog, bookCount);
					 break;
			case 4 : checkInBook(catalog, bookCount);
					 break;
			case 5 : showBookDetails(catalog, bookCount);
					 break;
			case 6 : showAllBookDetails(catalog, bookCount);
					 break;
			case 7 : showCheckedOut(catalog, bookCount);
					 break;
			case 8 : System.out.println("Logging out of system.");
					 input = 8;
					 break;
			default : System.out.println("Invalid option. Try again");
					  break;
			}
		} while (input != 8);
	}
	
	public static boolean validateIsbn(Book[] p, long isbn, int count) {
		for(int i = 0; i < count; i++) {
			if (p[i].getIsbn() == isbn) {
				return false;
			}
		} return true;
	}
	public static void addBook(Book[] p, int count) {
		long isbn = 0;
		String title;
		String type;
		String publisher;
		int pages = 0;
		float price = 0;
		int year = 0;
		int availability = 0;
		String user;
		boolean valid = true;
		
		do {
			try{
				System.out.println("Enter ISBN: ");
				isbn = keyboard.nextLong();
				
				if(String.valueOf(isbn).length() != 10) {
					System.out.println("Invalid ISBN. Must be a 10 digit number. Try again.");
					valid = true;
				} else if(!validateIsbn(p, isbn, count)) {
					System.out.println("ISBN already in database. Try again.");
					valid = true;
				} else {
				valid = false;
				}
			}
			catch (InputMismatchException ex) {
				System.out.println("Invalid input.");
				keyboard.nextLine();
			}
		} while (valid);
		
		System.out.println("Enter title of media: ");
		title = keyboard.next().toString();
		
		System.out.println("Enter type of media: ");
		type = keyboard.next().toString();
		
		System.out.println("Enter publisher: ");
		publisher = keyboard.next().toString();
		
		valid = true;
		do {
			try{
				System.out.println("Enter number of pages: ");
				pages = keyboard.nextInt();
				valid = false;
			}
			catch (InputMismatchException ex) {
				System.out.println("Invalid input.");
				keyboard.nextLine();
			}
		} while (valid);
		
		valid = true;
		do {
			try{
				System.out.println("Enter price: ");
				price = keyboard.nextFloat();
				valid = false;
			}
			catch (InputMismatchException ex) {
				System.out.println("Invalid input.");
				keyboard.nextLine();
			}
		} while (valid);
		
		valid = true;
		do {
			try{
				System.out.println("Enter publication year: ");
				year = keyboard.nextInt();
				valid = false;
			}
			catch (InputMismatchException ex) {
				System.out.println("Invalid input.");
				keyboard.nextLine();
			}
		} while (valid);
		
		availability = 0;
		user = "";
		
		p[count] = new Book(isbn, title, type, publisher, pages, price, year, availability, user);
		System.out.println("Media has been added.");
	}
	
	public static Book[] removeBook(Book[] p, int count) {
		System.out.println("Enter ISBN of media you wish to remove: ");
		long isbn = keyboard.nextLong();
		
		if(validateIsbn(p, isbn, count)) {
			System.out.println("This ISBN does not belong to any media in collection. Returning to menu. . .");
			return p;
		}else {
			Book[] newCatalog = new Book[p.length];
			for(int i = 0; i < p.length; i++) {
				if (p[i].getIsbn() != isbn) {
					newCatalog[i] = p[i];
				} else {
				   newCatalog[i] = p[i+1];
				   do{
				       newCatalog[i] = p[i+1];
				       i++;
				   } while (i < p.length-1); break;
				}
			}System.out.println("Media has been removed."); return newCatalog;
		} 
	} 
	
	public static void checkOutBook(Book[] p, int count) {
		System.out.println("Enter ISBN of media you wish to checkout: ");
		long isbn = keyboard.nextLong();
		System.out.println("Enter your name: ");
		String user = keyboard.next();
		
		if(validateIsbn(p, isbn, count)) {
			System.out.println("This ISBN does not belong to any media in collection. Returning to menu. . .");
		}else {
			for(int i = 0; i < count; i++) {
				if (p[i].getIsbn() == isbn) {
					if(p[i].getAvailability() == 0) {
						p[i].setAvailability(1);
						p[i].setUser(user);
						System.out.println("Media has been successfully checked out.");
					} else {
						System.out.println("Media is OUT OF STOCK. Returning to menu.");
					}
				}
			}
		}
	}
	
	public static void checkInBook(Book[] p, int count) {
		System.out.println("Enter ISBN of media you wish to check in: ");
		long isbn = keyboard.nextLong();
		
		if(validateIsbn(p, isbn, count)) {
			System.out.println("This ISBN does not belong to any media in collection. Returning to menu. . .");
		}else {
			for(int i = 0; i < count; i++) {
				if (p[i].getIsbn() == isbn) {
					if(p[i].getAvailability() == 1) {
						p[i].setAvailability(0);
						p[i].setUser("");
						System.out.println("Media has been checked in.");
					} else {
						System.out.println("Media has already been checked in. Returning to menu.");
					}
				}
			}
		}
	}
	
	public static void showBookDetails(Book[] p, int count){
		for(int i = 0; i < count; i++) {
			p[i].bookDetails();
		}
	}
	
	public static void showAllBookDetails(Book[] p, int count){
		for(int i = 0; i < count; i++) {
			p[i].bookAllDetails();
		}
	}
	
	public static void showCheckedOut(Book[] p, int count){
		for(int i = 0; i < count; i++) {
			if(p[i].getAvailability() == 1) {
				System.out.println(p[i].getTitle());
			}
		}
	}
}
