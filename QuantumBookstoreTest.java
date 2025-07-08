import java.time.Year;
import java.util.*;

import javax.print.DocFlavor.STRING;

abstract class book{
    private String title;
    private String author;
    private String ISBN;
    private int PublishYear;
    private double price;

    public book(String title, String author, String ISBN, int PublishYear, double price) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.PublishYear = PublishYear;
        this.price = price;
    }
    public int getPublishYear() {
        return PublishYear;
    }
    public String getISBN(){
        return ISBN;
    }
    public double getPrice() {
        return price;
    }
    public String getTitle() {
        return title;
    }
    //checks if the book is outdated relative to the current year
    public boolean isOutdated(int age){
        Year currentYear = Year.now();
        if(currentYear.getValue() - PublishYear > age) {
            return true;
        }
        return false;
    }

    public abstract void DeliveryService(String address,String email,int quantity);

}


interface sellable{
    void reduceQuantity(int quantity);
    boolean isAvailable(int quantity);
    int getQuantity();
}

class PaperBook extends book implements sellable{
    int quantity;
    public PaperBook(String title, String author, String ISBN, int PublishYear, double price, int quantity) {
        super(title, author, ISBN, PublishYear, price);
        this.quantity = quantity;
    }

    @Override 
    public boolean isAvailable(int quantity) {
        return this.quantity >= quantity;
    }

    @Override
    public void reduceQuantity(int quantity) {
        this.quantity -= quantity;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public void DeliveryService(String address, String email, int quantity) {
        System.out.println("Delivering PaperBook to " + address + ". Confirmation sent to " + email);
    }
    
}

class EBook extends book implements sellable{
    int quantity;
    String filetype;

    public EBook(String title, String author, String ISBN, int PublishYear, double price, int quantity, String filetype) {
        super(title, author, ISBN, PublishYear, price);
        this.quantity = quantity;
        this.filetype = filetype;
    }

    @Override 
    public boolean isAvailable(int quantity) {
        return this.quantity >= quantity;
    }

    @Override
    public void reduceQuantity(int quantity) {
        this.quantity -= quantity;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public void DeliveryService(String address, String email, int quantity) {
        System.out.println("Sending EBook to " + email);
    }
    
}

class DemoBook extends book{

    public DemoBook(String title, String author, String ISBN, int PublishYear, double price) {
        super(title, author, ISBN, PublishYear,0.0);
    }

    @Override
    public void DeliveryService(String address, String email, int quantity) {
        System.out.println("DemoBook is not for sale. No delivery service available.");
    }
}

class QuantumBookStore{ 
    private List<book> books;
    
    public QuantumBookStore() {
        books = new ArrayList<>();
    }

    public void addBook(book b) {
        books.add(b);
        System.out.println("Book added with ISBN: " + b.getISBN()+ " and title: " + b.getTitle());
    }

    public void removeBook(String ISBN) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getISBN().equals(ISBN)) {
                books.remove(i);
                System.out.println("Book with ISBN: " + ISBN + " removed.");
                return;
            }
        }
        System.out.println("Book with ISBN: " + ISBN + " not found.");
    }

    public void removeOutdatedBook(int age, String ISBN) {
     for (int i = 0; i < books.size(); i++) {
            book b = books.get(i);
            if (b.getISBN().equals(ISBN)) {
                if (b.isOutdated(age)) {
                    books.remove(i);
                    System.out.println("Book with ISBN: " + ISBN + " is outdated and removed.");
                } else {
                    System.out.println("Book with ISBN: " + ISBN + " is not outdated.");
                }
                return;
            }
        }
        System.out.println("Book with ISBN: " + ISBN + " not found.");
    }

    public double buyBook(String ISBN, int quantity, String address, String email) {
        double total = 0.0;
        for(book b:books){
            if(b.getISBN().equals(ISBN)&& !(b instanceof sellable)){
                System.out.println("This book is not for sale.");
                return total;
            }
            else if(b.getISBN().equals(ISBN) ){
                sellable book= (sellable) b;
                if(book.isAvailable(quantity)){
                    book.reduceQuantity(quantity);
                    b.DeliveryService(address, email, quantity);
                    System.out.println("Book purchased successfully.");
                    total = b.getPrice() * quantity;
                    System.out.println("Total price: " + total);
                    return total;
                } else {
                    System.out.println("Not enough stock available.");
                    return total;
                }
            }
        }
        
        System.out.println("Book with ISBN: " + ISBN + " not found.");
        return total;
}
}
public class QuantumBookstoreTest {
    public static void main(String[] args) {
        QuantumBookStore store = new QuantumBookStore();
        PaperBook b1 = new PaperBook("Mickey Mouse", "Asser Tamer", "1", 2025, 10, 20);
        EBook b2 = new EBook("Harry Potter", "Asser Tamer", "2", 2023, 15, 30, "PDF");
        DemoBook b3 = new DemoBook("Percy Jackson", "Asser Tamer", "3", 2025, 0);

        store.addBook(b1);
        store.addBook(b2);
        store.addBook(b3);

        store.buyBook("1", 5, "Khamayel Compound,Giza", "asserwagdy22@gmail.com");
        store.buyBook("2", 2, "Khamayel Compound,Giza", "asserwagdy22@gmail.com");
        store.buyBook("2", 40, "Khamayel Compound,Giza", "asserwagdy22@gmail.com");
        store.buyBook("3", 1, "Khamayel Compound,Giza", "asserwagdy22@gmail.com");

        store.removeOutdatedBook(1, "2");
        store.removeBook("1");
        store.removeOutdatedBook(1, "3");
        store.removeBook("4");

    }

}

