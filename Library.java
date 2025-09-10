import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.time.temporal.ChronoUnit;

public class Library {
    private LinkedList<Book> books;
    private AList<User> users;
    private BST<Transaction> transactions;
    private int count = 1;
    private final String ANSI_Reset = "\u001B[0m", ANSI_Red = "\u001B[31m";
    
    public Library() {
        books = new LinkedList<>();
        users = new AList<>();
        transactions = new BST<>();    
        
        	// Adding 2 users & 5 Books
        
        users.add(new User("Kashif Ali", "211400065", "0300-2233444", "54321"));
        users.add(new User("Khizer Farooq", "211400037", "0300-3344555", "12345"));
        
        books.add(new Book("HCI", "Arslan Tariq", "1", 0));
        books.add(new Book("DSA", "Kamal Ashraf", "2", 10));
        books.add(new Book("Linear Algebra", "Dr. Hassan Imran", "3", 10));
        books.add(new Book("Requirement Engineering", "Amir Saleem", "4", 10));
        books.add(new Book("Enterprenaurial Mindset", "Dr. Muhammad Shahid Qureshi", "5", 10));
        
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(books.search(book));
    }

    public Book findBookByTitle(String title) {
        for (int i = 0 ; i<books.size() ;i++) {
            if (books.get(i).getTitle().equals(title)) {
                return books.get(i);
            }
        }
        System.out.println(this.ANSI_Red+"The book you are looking for is not available in the library."+this.ANSI_Reset);
        System.out.println("----------------------------------------------------------");
        return null;
    }
    
    public Book findBookByAuthor(String author) {
        for (int i = 0 ; i<books.size() ;i++) {
            if (books.get(i).getAuthor().equals(author)) {
                return books.get(i);
            }
        }
        System.out.println(this.ANSI_Red+"The book you are looking for is not available in the library."+this.ANSI_Reset);
        System.out.println("----------------------------------------------------------");
        return null;
    }

    public Book findBookByID(String id) {
        for (int i = 0 ; i<books.size() ;i++) {
            if (books.get(i).getBookID().equals(id)) {
                return books.get(i);
            }
        }
        System.out.println(this.ANSI_Red+"The book you are looking for is not available in the library."+this.ANSI_Reset);
        return null;
    }
    
    public void SearchBookByTitle(String title) {
        for (int i = 0 ; i<books.size() ;i++) {
            if ((books.get(i).getTitle().toLowerCase()).contains(title.toLowerCase())) {
            	System.out.println(books.get(i));
            }
        }
        this.count = 1;
    }
    
    public void SearchBookByAuthor(String author) {
        for (int i = 0 ; i<books.size() ;i++) {
            if ((books.get(i).getAuthor().toLowerCase()).contains(author.toLowerCase())) {
            	System.out.println(books.get(i));
            }
        }
        this.count = 1;
    }
    
    public void SearchBookByID(String bookID) {
        for (int i = 0 ; i<books.size() ;i++) {
            if ((books.get(i).getBookID().toLowerCase()).contains(bookID)) {
                System.out.println(books.get(i));

            }
        }
        this.count = 1;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void removeUser(User user) {
        users.remove(user);
    }

    public User findUserByID(String ID) {
        for (int i = 0 ; i<users.size() ;i++) {
            if ((users.get(i).getID()).equals(ID)) {
                return users.get(i);
            }
        }
        return null;
    }
    
    public boolean validateUser(String username, String password) {
    	User a = this.findUserByID(username);
    	if(a!=null) {
    		if(a.getID().equals(username)) {
    			if(a.getPassword().equals(password)) {
    				return true;
    			}else {
    				System.out.println(this.ANSI_Red+"The password you entered is incorrect."+"\u001B[0m"+this.ANSI_Reset);
    				return false;
    			}
        	}else {
        		return false;
        	}
    	}else {
    		System.out.println(this.ANSI_Red+"Sorry, we couldn't find a user account with that username.\nPlease try again or sign up for a new account."+this.ANSI_Reset);
    		return false;
    	}	
    }

    public boolean checkDuplicateUserID(String ID) {
    	int count = 0;
    	for (int i = 0 ; i<users.size() ;i++) {
            if ((users.get(i).getID()).equals(ID))
            	count++;
        }
    	if(count==0) {
    		return true;
    	}else {
    		return false;
    	}
    }
    
    public boolean checkDuplicateID(String ID) {
    	int count = 0;
    	for (int i = 0 ; i<books.size() ;i++) {
            if (books.get(i).getBookID().equals(ID))
            	count++;
        }
    	if(count==0) {
    		return true;
    	}else {
    		System.out.println("This ID has already been assigned to another book.");
    		System.out.println("----------------------------------------------------------");
    		return false;
    	}
    }
    
    public Transaction borrowBook(Book book, User user, Date date, int days) {
        Transaction transaction = new Transaction(book, user, date, new Date(date.getTime() + (days * 86400000L)));
        transactions.insert(transaction);
        book.setNumOfCopies(book.getNumOfCopies() - 1);
        return transaction;
    }

    public void returnBook(Transaction transaction) {
        if (transaction != null) {
        	calculateFine(transaction);
            transaction.getBook().setNumOfCopies(transaction.getBook().getNumOfCopies() + 1);
            transaction.getUser().removeTransaction(transaction);
            transactions.remove(transaction);
            System.out.println("Thank you for returning your book!");
        }
    }

    public void calculateFine(Transaction transaction) {
    	long calculateFine = 0;    	
    	if (transaction != null) {
    		long days = TimeUnit.DAYS.convert(transaction.getDate().getTime() - transaction.getDueDate().getTime(), TimeUnit.MILLISECONDS);
            if(days>0) {
            	calculateFine = 75*days;
            	transaction.getUser().setFine(transaction.getUser().getFine() + calculateFine);
            	System.out.println(this.ANSI_Red+"As per our policy, a fine (Rs."+transaction.getUser().getFine()+") has been added to \nyour account for the late return of the book."+this.ANSI_Reset);
            	System.out.println("----------------------------------------------------------");
            }
        }
    }
    
    public void displayAllBooks() {
    	for(int i = 0 ; i<books.size() ;i++) {
    		System.out.println(books.get(i).toString());
    	}
    }
    
    public void displayAllUsers() {
    	if(users != null) {
    		for(int i = 0 ; i<users.size() ;i++) {
    			System.out.println(users.get(i).toString());
    		}
    	}else {
    		System.out.println("Not a single user is registered yet.");
    	}
    }	
    
    public void displayTransactionsByUser() {
    	transactions.traverseInOrder(transactions.getRoot());
    }

    public LinkedList<Book> getBooks() {
        return books;
    }

    public AList<User> getUsers() {
        return users;
    }

    public BST<Transaction> getTransactions() {
        return transactions;
    }
}