package org.olhas.librarysystem.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {

    /**
     * Tests that {@link Library} is properly converted to its string representation.
     */
    @Test
    void testToString() {
        Library library = createMockLibraryInstance();
        Assertions.assertTrue(library.toString().contains("Book{id=5, authorID=3, title='Hooked', edition='4', releaseDate=2014-07-11}"));
        Assertions.assertTrue(library.toString().contains("name='The British Library'"));
        Assertions.assertTrue(library.toString().contains("Book{id=3, authorID=3, title='Thinking, Fast and Slow', edition='2', releaseDate=2022-02-10}"));
        Assertions.assertTrue(library.toString().contains("Loan{id=2, studentID=3, bookID=4, loanFrom=2023-03-03, loanTo=2023-03-10}"));
    }

    /**
     * Checks that hashCode() method works properly for {@link Library} class.
     * Since instances of {@link Library} don't have any unique values like detailed address or some ID,
     * the result of hashcode() for all objects will be the same.
     */
    @Test
    void testHashCode() {
        Library library1 = createMockLibraryInstance();
        Library library2 = createMockLibraryInstance();
        Library library3 = library1;
        assertEquals(library1.hashCode(), library2.hashCode());
        assertEquals(library3.hashCode(), library1.hashCode());
        assertEquals(library3.hashCode(), library2.hashCode());
    }

    /**
     * Checks that equals() method works properly for {@link Library} class.
     * equals() should return true if two instances are the same semantically. Otherwise, false.
     */
    @Test
    void testEquals() {
        Library library1 = createMockLibraryInstance();
        Library library2 = createMockLibraryInstance();
        assertTrue(library1.equals(library2));

        Library library3 = createMockLibraryInstance();
        library3.setName("Some name");
        assertFalse(library2.equals(library3));
        assertFalse(library3.equals(library1));
    }

    /**
     * Helper method that creates a mock {@link Library} instance
     *
     * @return {@link Library} instance
     */
    private static Library createMockLibraryInstance() {
        Library library = new Library();
        List<Book> books = new ArrayList<>();
        List<Loan> loans = new ArrayList<>();

        books.add(new Book(1, 1, "Java Core", 9, LocalDate.of(2016, 2, 10)));
        books.add(new Book(2, 3, "Atomic Habits", 3, LocalDate.of(2018, 7, 7)));
        books.add(new Book(3, 3, "Thinking, Fast and Slow", 2, LocalDate.of(2022, 2, 10)));
        books.add(new Book(4, 3, "46 seconds", 1, LocalDate.of(2019, 5, 18)));
        books.add(new Book(5, 3, "Hooked", 4, LocalDate.of(2014, 7, 11)));
        books.add(new Book(6, 3, "Emotional Intelligence", 10, LocalDate.of(2015, 3, 19)));

        loans.add(new Loan(1, 2, 1, LocalDate.of(2023, 3, 3),
                LocalDate.of(2023, 3, 10)));
        loans.add(new Loan(2, 3, 4, LocalDate.of(2023, 3, 3),
                LocalDate.of(2023, 3, 10)));
        loans.add(new Loan(3, 3, 6, LocalDate.of(2023, 3, 3),
                LocalDate.of(2023, 3, 12)));

        library.setName("The British Library");
        library.setBooks(books);
        library.setLoans(loans);

        return library;
    }
}