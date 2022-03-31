package p01;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class BookClient {

    static Scanner scanner = new Scanner(System.in);
    private static final ArrayList<Book> books = new ArrayList<>();

    public static void main(String[] args) {

        // adding some books to start with
        books.add(new Book(
                "ISBN123456789",
                "Peter",
                "Müller",
                "Das Leben")
        );
        books.add(new Book(
                "ISBN987654321",
                "Lisa",
                "Schmidt",
                "Alles ode nichts")
        );

        int input = -1;
        while (input != 0) {
            System.out.println("Choose");
            System.out.println(" (0) Quit program");
            System.out.println(" (1) Load books from file");
            System.out.println(" (2) Show Books");
            System.out.println(" (3) Add Book");
            System.out.println(" (4) Delete Book");
            System.out.println(" (5) Save books in file");
            input = Integer.parseInt(scanner.nextLine());
            switch (input) {
                case 0 -> System.out.println("program exit");
                case 1 -> loadBooks();
                case 2 -> showBooks();
                case 3 -> addBook();
                case 4 -> deleteBook();
                case 5 -> saveBooks();
                default -> System.err.println("invalid input");
            }
        }
        scanner.close();
    }

    /**
     * shows the books saved in the container given as field
     */
    public static void showBooks() {
        System.out.println("\n -- all books --");
        System.out.println("ISBN                     | Author                   | Title");
        System.out.println("------------------------------------------------------------------------------");
        StringBuilder line;
        for (Book book : books) {
            line = new StringBuilder();
            line.append(book.getISBN())
                    .append(" ".repeat(Math.max(0, 25 - book.getISBN().length())))
                    .append("| ")
                    .append(book.getAuthorFirstname())
                    .append(" ")
                    .append(book.getAuthorLastname())
                    .append(" ".repeat(Math.max(0,
                            24 - book.getAuthorFirstname().length() - book.getAuthorLastname().length())))
                    .append("| ")
                    .append(book.getTitle());
            System.out.println(line);
        }
        System.out.println();
    }

    /**
     * adds a book to the container given as field
     */
    public static void addBook() {
        System.out.println("\n -- add a book --");
        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("Autor firstname: ");
        String firstname = scanner.nextLine();
        System.out.print("Author lastname: ");
        String lastname = scanner.nextLine();
        System.out.print("Title: ");
        String title = scanner.nextLine();
        System.out.println();

        books.add(new Book(isbn, firstname, lastname, title));
    }

    /**
     * deletes a book from the container given as field
     */
    public static void deleteBook() {
        System.out.println("\n -- delete a book --");
        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();
        books.removeIf(book -> book.getISBN().equals(isbn));
        System.out.println();
    }

    /**
     * save the books into the file given as field
     */
    public static void saveBooks() {
        try {
            ObjectOutputStream objectOutputStream =
                    new ObjectOutputStream(new FileOutputStream("books.txt"));
            objectOutputStream.writeObject(books);
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("\n -- save all books --\n");
    }

    /**
     * loads the books from the file given as field
     */
    @SuppressWarnings("unchecked")
    public static void loadBooks() {
        try {
            ObjectInputStream objectInputStream =
                    new ObjectInputStream(new FileInputStream("books.txt"));

            ArrayList<Book> loadedBooks = (ArrayList<Book>) objectInputStream.readObject();

            books.clear();
            books.addAll(loadedBooks);
            objectInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("\n -- loaded all books --\n");
    }
}
