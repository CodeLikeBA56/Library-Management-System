import java.io.*;
import java.util.*;
public class Manager { // Manager or Receptionist
	Scanner read = new Scanner(System.in);
	public Library GiftLibrary = new Library();
	private String username;
	private String password;
	
	final String ANSI_RESET = "\u001B[0m";
    final String ANSI_GREEN = "\u001B[32m";
    final String ANSI_RED = "\u001B[31m";
    final String ANSI_Light_Blue = "\u001B[94m";
    final String ANSI_Light_Yellow = "\u001B[93m";
    final String ANSI_Light_Mengta = "\u001B[95m";
    
	public Manager() {
		this.username = "211400068";
		this.password = "123";
	}
	
	public String getUsername() {return this.username;}
	public String getPassword() {return this.password;}
	
	public void ManagerFrame() {
		int choice = -1;
		while(choice==-1) {
			System.out.println("1. Add Book\t2. Update Book information\n3. Remove Book\t4. Display All Books\n5. Add User\t6. Update User profile\n7. Remove User\t8. Display All Users\n9. Sign out");
			choice = read.nextInt();
			System.out.println("----------------------------------------------------------");
			if(choice==1) {
				addBook();
				choice = -1;
			}else if(choice==2) {
				updateBookRecord();
				choice = -1;
			}else if(choice==3) {
				removeBook();
				choice = -1;
			}else if(choice==4){
				GiftLibrary.displayAllBooks();
				System.out.println("----------------------------------------------------------");
				choice = -1;
			}else if(choice==5) {
				addUser();
				choice = -1;
			}else if(choice==6) {
				updateUserProfile();
				choice = -1;
			}else if(choice==7) {
				removeUser();
				choice = -1;
			}else if(choice==8) {
				GiftLibrary.displayAllUsers();
				System.out.println("----------------------------------------------------------");
				choice = -1;
			}else if(choice==9) {
				System.out.println("\u001B[33m"+"You have been logged out. Have a great day!"+"\u001B[0m");
				System.out.println("----------------------------------------------------------");
				break;
			}else {
				System.out.println("\u001B[31m"+"\tInvalid choice. Please try again."+"\u001B[0m");
				System.out.println("----------------------------------------------------------");
				choice = -1;
			}
		}
		
	}
	
	public void addUser() {
		
		System.out.print("Name: ");
		String name = read.next();
		name += read.nextLine();
		System.out.print("Student ID: ");
		String ID = read.next();
			while(!(GiftLibrary.checkDuplicateUserID(ID)) || ID.equals(this.getUsername())) {
				System.out.println("--------------------------------------");
				System.out.println("The ID you entered is already existed.");
				System.out.println("--------------------------------------");
				System.out.print("Student ID: ");
				ID = read.next();
			}/*while(ID.equals(this.getUsername())) {
				System.out.println("--------------------------------------");
				System.out.println("The ID you entered is already existed.");
				System.out.println("--------------------------------------");
				System.out.print("Student ID: ");
				ID = read.next();
			}*/		
		System.out.print("Password: ");
		String pass = read.next();
		System.out.print("Mobile No.: ");
		String number = read.next();
		
		User n = new User(name, ID, number, pass);
		GiftLibrary.addUser(n);
		
	}
	
	public void addBook() {
		try {
			System.out.print("Enter book title: ");
			String title = read.next();
			title += read.nextLine();
			System.out.print("Enter book author: ");
			String author = read.next();
			author += read.nextLine();
			System.out.print("Enter book ID: ");
			String id = read.next();
			id += read.nextLine();
			System.out.println("Enter No of copies: ");
			int copies = read.nextInt();
			Book book = new Book(title, author, id, copies);
	        GiftLibrary.addBook(book);
		}catch(Exception e) {
			System.out.println(ANSI_RED + "Failed to add book to the library." + ANSI_RESET);
		}
        System.out.println(ANSI_GREEN+"Book added to the library!"+ANSI_RESET);
	}
	
	public void removeBook() {
		System.out.println("Select a book:");
		System.out.println("----------------------------------------------------------");
		GiftLibrary.displayAllBooks(); // It will display all books so that manager can remove whatever he wants to remove (By ID only).
		System.out.println("----------------------------------------------------------");
		int choice = -1;
		while(choice==-1) {
			System.out.println("1. Enter Book ID\t2. Go Back");
			choice = read.nextInt();
			System.out.println("----------------------------------------------------------");
			if(choice==1) {
				System.out.print("Enter book id: ");
				String id = read.next();
				System.out.println("----------------------------------------------------------");
				Book b = GiftLibrary.findBookByID(id);
				if(b==null) {
					System.out.println(ANSI_RED + "Failed to remove book from the library." + ANSI_RESET);
					choice = -1;
				}else {
					GiftLibrary.removeBook(b);
					System.out.println(ANSI_GREEN+"Book removed from the library!"+ANSI_RESET);
					System.out.println("----------------------------------------------------------");
				}
			}else if(choice==2) {
				break;
			}else {
				System.out.println("\u001B[31m"+"\tInvalid choice. Please try again."+"\u001B[0m");
				System.out.println("----------------------------------------------------------");
				choice = -1;
			}
		}
	}
	
	public void updateBookRecord() {
		System.out.println("\t\tSelect a book:");
		System.out.println("----------------------------------------------------------");
		GiftLibrary.displayAllBooks(); // It will display all books so that manager can update book's data whatever he wants to remove (By ID only).
		System.out.println("----------------------------------------------------------");
		int choice = -1;
		while(choice==-1) {
			System.out.println("1. Enter Book ID\t2. Go Back");
			choice = read.nextInt();
			System.out.println("----------------------------------------------------------");
			if(choice==1) {
				System.out.print("Enter book id: ");
				String id = read.next();
				System.out.println("----------------------------------------------------------");
				Book b = GiftLibrary.findBookByID(id);
				System.out.println("----------------------------------------------------------");
				if(b==null) {
					choice = -1;
				}else {
					b.display();
					System.out.println("----------------------------------------------------------");
					while(choice==1) {
						System.out.println("1. Change Title\t\t2. Change Author name\n3. Change Book ID\t4. Update No. of Copies\n5. Go Back");
						choice = read.nextInt();
						System.out.println("----------------------------------------------------------");
						if(choice==1) {
							System.out.print("Enter Title: ");
							id = read.next();
							id += read.nextLine();
							System.out.println("----------------------------------------------------------");
							b.setTitle(id);
							System.out.println(this.ANSI_GREEN+"The book's title has been updated successfully."+this.ANSI_RESET);
					    	System.out.println("----------------------------------------------------------");
							b.display();
							System.out.println("----------------------------------------------------------");
						}else if(choice==2) {
							System.out.print("Enter Auther name: ");
							id = read.next();
							id += read.nextLine();
							System.out.println("----------------------------------------------------------");
							b.setAuthor(id);
							System.out.println(this.ANSI_GREEN+"The author of the book has been updated successfully."+this.ANSI_RESET);
					    	System.out.println("----------------------------------------------------------");
							b.display();
							System.out.println("----------------------------------------------------------");
							choice = 1;
						}else if(choice==3) {
							while(choice==3) {
								System.out.print("Enter Book ID: ");
								id = read.next();
								System.out.println("----------------------------------------------------------");
								if(GiftLibrary.checkDuplicateID(id)) {
									choice = 1;
									b.setBookID(id);
									System.out.println(this.ANSI_GREEN+"The book's ID has been successfully updated."+this.ANSI_RESET);
							    	System.out.println("----------------------------------------------------------");
									b.display();
									System.out.println("----------------------------------------------------------");
								}else {
									choice = 3;
								}
							}
						}else if(choice==4) {
							System.out.print("Enter No. of Copies: ");
							choice = read.nextInt();
							System.out.println("----------------------------------------------------------");
							b.setNumOfCopies(choice);
							System.out.println(this.ANSI_GREEN+"The number of book copies has been successfully updated."+this.ANSI_RESET);
					    	System.out.println("----------------------------------------------------------");
							choice = 1;
							b.display();
							System.out.println("----------------------------------------------------------");
						}else if(choice==5) {
							choice = -1;
						}else {
							System.out.println("\u001B[31m"+"\tInvalid choice. Please try again."+"\u001B[0m");
							System.out.println("----------------------------------------------------------");
							choice = 1;
						}
					}
					
				}
			}else if(choice==2) {
				break;
			}else {
				System.out.println("\u001B[31m"+"\tInvalid choice. Please try again."+"\u001B[0m");
				System.out.println("----------------------------------------------------------");
				choice = -1;
			}
		}
	} // Update Book Record Method

	public void updateUserProfile() {
		System.out.println("\t\tSelect a User:");
		System.out.println("----------------------------------------------------------");
		GiftLibrary.displayAllUsers(); // It will display all Users so that manager can update User's data whatever he wants to remove (By ID only).
		System.out.println("----------------------------------------------------------");
		int choice = -1;
		while(choice==-1) {
			System.out.println("1. Enter User ID\t2. Go Back");
			System.out.print("1. Enter your choice: ");
			choice = read.nextInt();
			System.out.println("----------------------------------------------------------");
			if(choice==1) {
				System.out.print("Enter User ID: ");
				String id = read.next();
				System.out.println("----------------------------------------------------------");
				User b = GiftLibrary.findUserByID(id);
				if(b==null) {
					choice = -1;
				}else {
					b.display();
					System.out.println("----------------------------------------------------------");
					while(choice==1) {
						System.out.println("1. Change Username\t2. Change User ID\n3. Change Password\t5. Go Back");
						choice = read.nextInt();
						System.out.println("----------------------------------------------------------");
						if(choice==1) {
							System.out.print("Enter name: ");
							id = read.next();
							id += read.nextLine();
							System.out.println("----------------------------------------------------------");
							b.setName(id);
							b.display();
							System.out.println("----------------------------------------------------------");
						}else if(choice==2) {
							while(choice==2) {
								System.out.print("Enter User ID: ");
								id = read.next();
								System.out.println("----------------------------------------------------------");
								if(GiftLibrary.checkDuplicateUserID(id)) {
									choice = 1;
									b.setID(id);;
									b.display();
									System.out.println("----------------------------------------------------------");
								}else {
									choice = 2;
								}
							}
						}else if(choice==3) {
							System.out.print("Enter Password: ");
							id = read.next();
							id += read.nextLine();
							System.out.println("----------------------------------------------------------");
							b.setPassword(id);;
							choice = 1;
							b.display();
							System.out.println("----------------------------------------------------------");
						}else if(choice==5) {
							choice = -1;
						}else {
							System.out.println("\u001B[31m"+"\tInvalid choice. Please try again."+"\u001B[0m");
							System.out.println("----------------------------------------------------------");
							choice = 1;
						}
					}
					
				}
			}else if(choice==2) {
				break;
			}else {
				System.out.println("\u001B[31m"+"\tInvalid choice. Please try again."+"\u001B[0m");
				System.out.println("----------------------------------------------------------");
				choice = -1;
			}
		}
	} // Update User Record Method
	
	public void removeUser() {
		System.out.println("Select a User:");
		System.out.println("----------------------------------------------------------");
		GiftLibrary.displayAllUsers(); // It will display all books so that manager can remove whatever he wants to remove (By ID only).
		System.out.println("----------------------------------------------------------");
		int choice = -1;
		while(choice==-1) {
			System.out.println("1. Enter User ID\t2. Go Back");
			choice = read.nextInt();
			System.out.println("----------------------------------------------------------");
			if(choice==1) {
				System.out.print("Enter User id: ");
				String id = read.next();
				System.out.println("----------------------------------------------------------");
				User b = GiftLibrary.findUserByID(id);
				if(b==null) {
					System.out.println(ANSI_RED + "Failed to remove user from the system." + ANSI_RESET);
					choice = -1;
				}else {
					GiftLibrary.removeUser(b);
					System.out.println(ANSI_GREEN+"User removed from the system.!"+ANSI_RESET);
					System.out.println("----------------------------------------------------------");
				}
			}else if(choice==2) {
				break;
			}else {
				System.out.println("\u001B[31m"+"\tInvalid choice. Please try again."+"\u001B[0m");
				System.out.println("----------------------------------------------------------");
				choice = -1;
			}
		}
	} // Remove User form the system

}
