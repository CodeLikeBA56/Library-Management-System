import java.util.*;
import java.util.LinkedList;
import java.util.Date;
import java.text.SimpleDateFormat;
public class Main {
	
	private static Scanner read = new Scanner(System.in);
	private static Manager receptionist = new Manager();
	private static LinkedList<Book> searchedBooks;
	
	private static String username, password, SearchBook;
	private static User currentUser = null;;
	private static Book book;
	private static int choice = -1;
	
	public static void main(String[] args) {		
		System.out.println("\u001B[32m"+"Welcome to our library's digital collection. Explore a world of books and ideas online!"+"\u001B[0m");
		System.out.println("Please select an option:");
		while(choice==-1) {
			System.out.println(receptionist.ANSI_Light_Blue+"1. Sign in\t\t2. Sign up"+"\n3. Guest Account\t4. Exit"+"\u001B[0m");
			System.out.print("Enter your choice: ");
			choice = read.nextInt();
			System.out.println("----------------------------------------------------------");
			if(choice==1) {
				Login();
				if(currentUser!=null)
					choice = -2;
				else
					choice = -1;
			}else if(choice==2) {
				receptionist.addUser();
				choice = -1;
			}else if(choice==3) {
				choice = -2;
			}else if(choice==4){
				System.out.println("\u001B[31m"+"\t\t    Goodbye!"+"\u001B[0m");
				System.out.println("----------------------------------------------------------");
				System.exit(0);
			}else {
				System.out.println("\u001B[31m"+"\tInvalid choice. Please try again."+"\u001B[0m");
				System.out.println("----------------------------------------------------------");
				choice = -1;
			}
		} // Login Process
		
		receptionist.GiftLibrary.displayTransactionsByUser();
		
		// Patrons Module
		while(choice==-2) {
			System.out.println("Please select an option:");
			System.out.println("\u001B[36m"+"1. Search Book\t2. Borrow book\n3. Return Book\t4. Pay Fine\n5. Sign out"+"\u001B[0m");
			choice = read.nextInt();
			System.out.println("----------------------------------------------------------");
			if(choice==1) {
				searchBook(-1);
				choice = -2;
			}else if(choice==2) {
				if(currentUser==null) {
					validation();
				}else {
					BorrowBook(1);
				}
				choice = -2;
			}else if(choice==3) {
				returnBook();
				choice = -2;
			}else if(choice==4) {
				currentUser.setFine(0);
				choice = -2;
			}else if(choice==5) {
				choice = -1;
				System.out.println("\u001B[33m"+"You have been logged out. Have a great day!"+"\u001B[0m");
				System.out.println("----------------------------------------------------------");
				main(null);
			}else {
				System.out.println("\u001B[31m"+"\tInvalid choice. Please try again."+"\u001B[0m");
				System.out.println("----------------------------------------------------------");
				choice = -2;
			}
		}
		
		
	} // Main-Method

	public static void searchBook(int Choice) {
		while(Choice==-1) {
			System.out.println("1. Search By Title\n2. Search By Author\n3. Search By ID\n4. Go Back");
			Choice = read.nextInt();
			System.out.println("----------------------------------------------------------");
			if(Choice==1) { // Search Book By Title
				while(Choice==1) {
					System.out.print("Enter Title Here: ");
					SearchBook = read.next();
					SearchBook += read.nextLine();
					System.out.println("----------------------------------------------------------");
					receptionist.GiftLibrary.SearchBookByTitle(SearchBook);  // Will display relevant books related to users search criteria.
					System.out.println("----------------------------------------------------------");
					while(Choice==1) {
						System.out.println("1. Borrow Book\n2. Search By Title Again\n3. Go Back");
						Choice = read.nextInt();
						System.out.println("----------------------------------------------------------");
							if(Choice==1) { // Borrow Book
								if(currentUser!=null) { // Is user using guest account? --> No = execute if statement || Yes = execute else
									BorrowBook(1);
									Choice = -2;
									break;			
								}else { // User is using guest account. Now, user have to login first.
									validation();
									Choice = -2;
									break;
								}
							}else if(Choice==2) {
								Choice = 1;
								break;
							}else if(Choice==3) {
								Choice = -1;
								break;
							}else {
								System.out.println("\u001B[31m"+"\tInvalid choice. Please try again."+"\u001B[0m");
								System.out.println("----------------------------------------------------------");
								Choice = 1;
							}
					} // Inner-while-Loop		
				} // Outer-while-Loop
			}else if(Choice==2) { // Search Book By Author
				while(Choice==2) {
					System.out.print("Enter Author Name Here: ");
					SearchBook = read.next();
					SearchBook += read.nextLine();
					System.out.println("--------------------------------------");
					receptionist.GiftLibrary.SearchBookByAuthor(SearchBook);
					System.out.println("--------------------------------------");
					while(Choice==2) {
						System.out.println("1. Borrow Book\n2. Search By Auther Again\n3. Go Back");
						Choice = read.nextInt();
						System.out.println("----------------------------------------------------------");
							if(Choice==1) {
								if(currentUser!=null) { // Is user using guest account? --> No = execute if statement || Yes = execute else
									BorrowBook(1);
									Choice = -2;
									break;
								}else { // User is using guest account. Now, user have to login first.
									validation();
									Choice = -2;
									break;
								}
							}else if(Choice==2) {
								Choice = 2;
								break;
							}else if(Choice==3) {
								Choice = -1;
								break;
							}else {
								System.out.println("\u001B[31m"+"\tInvalid choice. Please try again."+"\u001B[0m");
								System.out.println("----------------------------------------------------------");
								Choice = 1;
							}
					} // Inner-while-Loop
				} // Outer-while-Loop
			}else if(Choice==3) { // Search Book By ID
				while(Choice==3) {
					System.out.print("Enter ID Here: ");
					SearchBook = read.next();
					SearchBook += read.nextLine();
					System.out.println("----------------------------------------------------------");
					receptionist.GiftLibrary.SearchBookByID(SearchBook);
					System.out.println("----------------------------------------------------------");
					while(Choice==3) {
						System.out.println("1. Borrow Book\n2. Search By ID Again\n3. Go Back");
						Choice = read.nextInt();
						System.out.println("----------------------------------------------------------");
							if(Choice==1) {
								if(currentUser!=null) { // Is user using guest account? --> No = execute if statement || Yes = execute else
									BorrowBook(1);
									Choice = -2;
									break;
								}else { // User is using guest account. Now, user have to login first.
									validation();
									Choice = -2;
									break;
								}
							}else if(Choice==2) {
								Choice = 3;
								break;
							}else if(Choice==3) {
								Choice = -1;
								break;
							}else {
								System.out.println("\u001B[31m"+"\tInvalid choice. Please try again."+"\u001B[0m");
								System.out.println("----------------------------------------------------------");
								Choice = 1;
							}
					} // Inner-while-Loop
				} // While Loop for search book by id
			}else if(Choice==4) { // Go Back
				break;
			}else{
				System.out.println("\u001B[31m"+"\tInvalid choice. Please try again."+"\u001B[0m");
				System.out.println("----------------------------------------------------------");
				Choice = -1;
			}
		}	// Search Book While Loop
	} // Search-Book-Method
	
	public static void BorrowBook(int Choice) {
		if(currentUser.getFine()==0) {
			while(Choice==1) {
				System.out.print("Enter Book ID here: ");
				String id = read.next();
				book = receptionist.GiftLibrary.findBookByID(id);
				System.out.println("----------------------------------------------------------");
				if(book != null) {
					System.out.print("How many days until you plan to return the book? (e.g: 7) : ");
					Choice = read.nextInt();
					Transaction n = receptionist.GiftLibrary.borrowBook(book, currentUser, new Date(), Choice);
					currentUser.addTransaction(n);
					System.out.println("----------------------------------------------------------");
					System.out.println("You have successfully borrowed '"+n.getBook().getTitle()+"' Book. Please return it by "+formatDate(n.getDueDate())+".");
					System.out.println("----------------------------------------------------------");
					System.out.println(n.toString());
					System.out.println("----------------------------------------------------------");
					Choice = 0;
				}else {
					while(Choice==1) {
						System.out.println("1. Enter Book ID\t2. Search Book");
						System.out.print("Enter your choice: ");
						Choice = read.nextInt();
						System.out.println("----------------------------------------------------------");
						if(Choice==1) {
							Choice = 1;
							break;
						}else if(Choice==2) {
							searchBook(-1);
							break;
						}else {
							System.out.println("\u001B[31m"+"\tInvalid choice. Please try again."+"\u001B[0m");
							System.out.println("----------------------------------------------------------");
							choice = 1;
						}
					} // While- Loop
				} // Else
			}
		}else {
			System.out.println(receptionist.ANSI_RED+"Your account currently has a fine of Rs."+currentUser.getFine()+" due to an overdue book.\nPlease settle this before borrowing any more items."+receptionist.ANSI_RESET);
			System.out.println("----------------------------------------------------------");
		}
	} // Borrow-Book-Method
	
	public static void returnBook() {
		currentUser.displayTransactions();
		Transaction n = null;
		while(n==null) {
			System.out.print("Enter Book ID: ");
			String id = read.next();
			System.out.println("----------------------------------------------------------");
			n = currentUser.getTransaction(id);
			if(n!=null) {
				receptionist.GiftLibrary.returnBook(n);
				currentUser.removeTransaction(n);
				System.out.println("----------------------------------------------------------");
			}else {
				while(choice==3) {
					System.out.println("1. Again Enter Book ID\t2. Go Back");
					choice = read.nextInt();
					System.out.println("----------------------------------------------------------");
					if(choice==1) {
						break;
					}else if(choice==2) {
						choice = 2;
					}else {
						System.out.println("\u001B[31m"+"\tInvalid choice. Please try again."+"\u001B[0m");
						System.out.println("----------------------------------------------------------");
						choice = 3;
					}
				} // while-loop
				if(choice==2) {
					break;
				}
			} // else
		}
		
		
	}
	
	public static void Login() {
		while(choice==1) {
			System.out.print("Username: ");
			username = read.next();
			System.out.print("Password: ");
			password = read.next();
			System.out.println("----------------------------------------------------------");
			if((receptionist.getUsername()).equals(username) && (receptionist.getPassword().equals(password))) {
				receptionist.ManagerFrame();
				choice = -1;
			}else if(receptionist.GiftLibrary.validateUser(username, password)) {
				currentUser = receptionist.GiftLibrary.findUserByID(username);
				System.out.println("\u001B[32m"+"You have signed in!"+"\u001B[0m");
				System.out.println("----------------------------------------------------------");
				break;
			}else {
				System.out.println("----------------------------------------------------------");
				choice = 1;
				break;
			}
		}
	} // Login-Method
	
	public static void validation() {
		while(currentUser==null) {
			System.out.println("\u001B[35m"+"You are using the Guest Account."+"\u001B[0m");
			System.out.println("----------------------------------------------------------");
			System.out.println("\u001B[36m"+"1. Sign in		2. Sign up\n3. Go Back"+"\u001B[0m");
			choice = read.nextInt();
			System.out.println("----------------------------------------------------------");
			if(choice==1) {
				Login();
				if(currentUser!=null) {
					BorrowBook(1);
				}	
			}else if(choice==2) {
				receptionist.addUser();
				System.out.println("----------------------------------------------------------");
				Login();
			}else if(choice==3) {
				break;
			}
		
		}
	}
	
	private static String formatDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy");
		return sdf.format(date);
	}
	
}
