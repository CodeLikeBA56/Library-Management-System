import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Transaction implements Comparable<Transaction>{
    private Book book;
    private User user;
    private Date date;
    private Date dueDate;

    public Transaction(Book book, User user, Date date, Date dueDate) {
        this.book = book;
        this.user = user;
        this.date = date;
        this.dueDate = dueDate;
    }
    
    public void setBook(Book book){this.book = book;}
    public void setUser(User user){this.user = user;}
    public void setDate(Date date){this.date = date;}
    public void setDueDate(Date dueDate){this.dueDate = dueDate;}

    public Book getBook(){return this.book;}
    public User getUser(){return this.user;}
    public Date getDate(){return this.date;}
    public Date getDueDate(){return this.dueDate;}

    public String toString() {
    	return "Borrower Name: "+user.getName()+"\nBorrow Book: "+book.getTitle()+"\nBook ID: "+book.getBookID()
    		+"\nBorrow Date: "+formatDate(this.getDate())+"\nDue Date: "+formatDate(this.dueDate);
    }
    
    public void display() {
    	System.out.println("User Detail: \n\t"+this.user.toString());
    	System.out.println("Book Detail: \n\t"+this.book.toString());
    	System.out.println("Issue Date: "+formatDate(this.date));
    	System.out.println("Due Date: "+formatDate(this.dueDate));
    }
    
    private String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss a");
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Karachi"));
        return sdf.format(date);
    }

	@Override
	public int compareTo(Transaction o) {
		if(this.getDate().compareTo(o.getDate())==0) {
    		return 0;
    	}else if(this.getDate().compareTo(o.getDate())>0) {
    		return 1;
    	}else {
    		return -1;
    	}
	}
    
}