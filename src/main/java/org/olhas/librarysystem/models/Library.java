package org.olhas.librarysystem.models;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents the real-world library.
 * Contains information like the name of the library, a list of books, and a list of loans.
 *
 * @author Olha
 * @version 1.0
 */
@XmlRootElement(name = "Library")
@XmlType(propOrder = { "name", "books", "loans" })
public class Library {
     private String name;
    private List<Book> books;
    private List<Loan> loans;

    /**
     * Class constructor that creates a new {@link Library} entity.
     *
     * @param name the name of the library
     * @param books the list of books present in the library
     * @param loans the list of loans
     */
    public Library(String name, List<Book> books, List<Loan> loans) {
        this.name = name;
        this.books = books;
        this.loans = loans;
    }

    /**
     * Default no-arg constructor.
     */
    public Library() {
        this.books = new ArrayList<>();
        this.loans = new ArrayList<>();
    }

    /**
     * Returns a name of the library.
     *
     * @return the name of the library
     */
    public String getName() {
        return name;
    }

    /**
     * Sets a name of the library.
     *
     * @param name a new name of the library
     */
    @XmlElement(name = "Library_Name")
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns a list of books present in the library.
     *
     * @return a list of {@link Book}
     */
    public List<Book> getBooks() {
        return books;
    }

    /**
     * Sets a new list of books for this library.
     *
     * @param books a new list of books
     */
    @XmlElement(name = "Library_Book")
    @XmlElementWrapper(name = "Books")
    public void setBooks(List<Book> books) {
        this.books = books;
    }

    /**
     * Returns a list of loans.
     *
     * @return a list of loans
     */
    public List<Loan> getLoans() {
        return loans;
    }

    /**
     * Sets a new list of loans for this library.
     *
     * @param loans a new list of loans
     */
    @XmlElement(name = "Library_Loan")
    @XmlElementWrapper(name = "Loans")
    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Library library)) return false;

        if (!getName().equals(library.getName())) return false;
        if (!getBooks().equals(library.getBooks())) return false;
        return getLoans().equals(library.getLoans());
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getBooks().hashCode();
        result = 31 * result + getLoans().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Library{" +
                "name='" + name + '\'' +
                ", books=" + books +
                ", loans=" + loans +
                '}';
    }
}
