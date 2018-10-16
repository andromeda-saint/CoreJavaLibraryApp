package info.andrewwilliams.libraryapp;
/*
* Author: Andrew Williams
* Description: This is a library catolog program
*/
public class Book {
	private long isbn;
	private String title;
	private String type;
	private String publisher;
	private int pages;
	private float price;
	private int year;
	private int availability;
	private String user;
	
	public Book(long isbn, String title, String type, String publisher, int pages, float price, int year,
			int availability, String user) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.type = type;
		this.publisher = publisher;
		this.pages = pages;
		this.price = price;
		this.year = year;
		this.availability = availability;
		this.user = user;
	}
	
	//Lists book details minus user
	public void bookDetails() {
		System.out.println("====Details====\n"                   +
							"ISBN: "         + getIsbn()         + "\n"  +
							"Title: "        + getTitle()        + "\n"  +
							"Type: "         + getType()         + "\n"  +
							"Publisher: "    + getPublisher()    + "\n"  +
							"Pages: "        + getPages()        + "\n"  +
							"Year: "         + getYear()         + "\n"  );
	}
	
	//Lists book details including user
	public void bookAllDetails() {
		System.out.println("====Details====\n"                     +
							"ISBN: "         + getIsbn()           + "\n"  +
							"Title: "        + getTitle()          + "\n"  +
							"Type: "         + getType()           + "\n"  +
							"Publisher: "    + getPublisher()      + "\n"  +
							"Pages: "        + getPages()          + "\n"  +
							"Price: "        + getPrice()          + "\n"  +
							"Year: "         + getYear()           + "\n"  +
							"Availability: " + printAvailability() + "\n"  +
							"User: "         + getUser()           + "\n"  );
	}
	
	public long getIsbn() {
		return isbn;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getType() {
		return type;
	}
	
	public String getPublisher() {
		return title;
	}
	
	public int getPages() {
		return pages;
	}
	
	public float getPrice() {
		return price;
	}
	
	public int getYear() {
		return year;
	}
	
	public int getAvailability() {
		if(availability == 0) {
			return 0;
		}   return 1;
	}
	
	public String printAvailability() {
		if(availability == 0) {
			return "In Stock";
			
		} return "Out of stock";
	}
	
	public String getUser() {
		if(availability == 0) {
			return "Currently not checked out";
		}
		return user;
	}
	
	public void setAvailability(int n) {
		if(n == 0) {
			availability = 0;
		} else {
			availability = 1;
		}
	}
	
	public void setUser(String name) {
		user = name;
	}
}
