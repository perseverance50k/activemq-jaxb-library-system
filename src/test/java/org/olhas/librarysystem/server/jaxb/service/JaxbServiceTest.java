package org.olhas.librarysystem.server.jaxb.service;

import jakarta.xml.bind.JAXBException;
import org.junit.jupiter.api.Test;
import org.olhas.librarysystem.models.Book;
import org.olhas.librarysystem.models.Library;
import org.olhas.librarysystem.models.Loan;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JaxbServiceTest {

    private final JaxbService jaxbService = new JaxbService();

    /**
     * Checks that {@link JaxbService} performs marshalling operation correctly.
     *
     * @throws JAXBException if XML code isn't valid
     */
    @Test
    void testTransformToXML() throws JAXBException, IOException {
        // TODO: write a test for marshalling operation
        final String expected = Files.readString(Paths.get("src/test/resources/test_library.xml")) + "\n";
        Library library = createMockLibraryInstance();
        String marshalledToXml = jaxbService.transformToXML(library);

        assertEquals(expected, marshalledToXml);
    }

    /**
     * Checks that {@link JaxbService} performs unmarshalling operation correctly.
     *
     * @throws JAXBException if XML code isn't valid
     */
    @Test
    void testConvertToPOJO() throws JAXBException {
        Library library = jaxbService.convertToPOJO("src/test/resources/test_library.xml");
        assertEquals("The British Library", library.getName());
        assertEquals(library.getBooks().size(), 6);
        assertEquals(library.getLoans().size(), 3);
    }

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