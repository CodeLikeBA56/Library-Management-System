import java.util.*;
public class User implements Comparable<User>{
    private String name;
    private String ID;
    private String mobileNo;
    private String password;
    private LinkedList<Transaction> borrowedBooks;
    private double fine;

    private final String ANSI_RESET = "\u001B[0m";
    private final String ANSI_GREEN = "\u001B[32m";
    private final String ANSI_Light_Blue = "\u001B[94m";
    private final String ANSI_Light_Yellow = "\u001B[33m";
    private final String ANSI_Light_Mengta = "\u001B[95m";
    private final String ANSI_RED = "\u001B[31m";
    
    public User(String name, String ID, String mobileNo, String password) {
        this.name = name;
        this.ID = ID;
        this.mobileNo = mobileNo;
        this.password = password;
        this.fine = 0.0;
        borrowedBooks = new LinkedList<>();
    }

    public void setID(String ID) {this.ID = ID;}
    public void setName(String name) {this.name = name;}
    public void setFine(double fine) {this.fine = fine;}
    public void setMobileNo(String mobileNo) {this.mobileNo = mobileNo;}
    public void setPassword(String password) {this.password = password;}
    
    public String getID(){return ID;}
    public String getName(){return name;}
    public double getFine() {return this.fine;}
    public String getMobileNo(){return mobileNo;}
    public String getPassword(){return this.password;}
    
    public void addTransaction(Transaction c) {
    	borrowedBooks.add(c);
    }
    
    public Transaction getTransaction(String id) {
    	for(int i = 0 ; i<borrowedBooks.size() ; i++) {
    		if(borrowedBooks.get(i).getBook().getBookID().equals(id)) {
    			return borrowedBooks.get(i);
    		}
    	}
    	System.out.println(this.ANSI_RED+"Sorry, no transaction found for the provided book ID. Please check the ID and try again."+this.ANSI_RESET);
		System.out.println("----------------------------------------------------------");
    	return null;
    }
    
    public void removeTransaction(Transaction c) {
    	borrowedBooks.remove(borrowedBooks.search(c));
    }
    
    public void displayTransactions() {
    	for(int i = 0 ; i<borrowedBooks.size() ; i++) {
    		System.out.println(borrowedBooks.get(i).toString());
    		if(i<borrowedBooks.size()-1)
    			System.out.println("\t<--------------->");
    	}
    	System.out.println("----------------------------------------------------------");
    }
    
    public String toString() {
    	return "--> "+this.ANSI_Light_Blue+"Name: "+this.ANSI_Light_Yellow+this.getName()+ANSI_Light_Blue+", ID: "+ANSI_Light_Yellow+this.getID()+ANSI_Light_Blue+", Password: "+ANSI_Light_Yellow+this.getPassword()+this.ANSI_RESET;
    }
    
    public void display() {
    	System.out.println("Name: "+this.getName());
    	System.out.println("ID: "+this.getID());
    	System.out.println("Mobile No.: "+this.getMobileNo());
    	System.out.println("Password: "+this.getPassword());
    }

	public int compareTo(String ID) {
		if(this.getID().compareTo(ID)==0) {
    		return 0;
    	}else if(this.getID().compareTo(ID)>0) {
    		return 1;
    	}else {
    		return -1;
    	}
	}

	@Override
	public int compareTo(User o) {
		if(this.getID().compareTo(o.getID())==0) {
    		return 0;
    	}else if(this.getID().compareTo(o.getID())>0) {
    		return 1;
    	}else {
    		return -1;
    	}
	} // This method is used to remove User from AList. [remove(T value) --> remove(User value)] & For sorting user according to User ID
    
}