Overview:
The Quantum Bookstore is a Java-based inventory management system that supports multiple types of books with different sales and delivery mechanisms. The system is designed with extensibility in mind, allowing easy addition of new book types without modifying existing code.

Core Functionality:
- Add books to inventory with ISBN, title, author, publication year, and price
- Remove outdated books based on publication age
- Purchase books with quantity, delivery address, and email
- Automatic stock management and availability checking
- Delivery service integration (shipping for paper books, email for e-books)

Running the Application:

1.Compile the Java file:javac QuantumBookstoreTest.java

2.Run the test class:java QuantumBookstoreTest

Expected Output:
Quantum book store Book added with ISBN: 1 and title: Mickey Mouse
Quantum book store Book added with ISBN: 2 and title: Harry Potter
Quantum book store Book added with ISBN: 3 and title: Percy Jackson
Quantum book store Delivering PaperBook to Khamayel Compound,Giza. Confirmation sent to asserwagdy22@gmail.com
Quantum book store Book purchased successfully.
Quantum book store Total price: 50.0
Quantum book store Sending EBook to asserwagdy22@gmail.com
Quantum book store Book purchased successfully.
Quantum book store Total price: 30.0
Quantum book store Not enough stock available.
Quantum book store DemoBook is not for sale. No delivery service available.
Quantum book store This book is not for sale.
Quantum book store Book with ISBN: 2 is not outdated.
Quantum book store Book with ISBN: 1 removed.
Quantum book store Book with ISBN: 3 is not outdated.
Quantum book store Book with ISBN: 4 not found.
