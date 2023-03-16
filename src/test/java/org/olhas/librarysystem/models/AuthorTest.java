package org.olhas.librarysystem.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthorTest {

    private Author author1 = new Author(1, "James", "Clear");
    private Author author2 = new Author(2, "Key", "Horstmann");
    private Author author3 = new Author(3, "Herbert", "Schildt");
    private Author author4 = new Author(4, "Herbert", "Schildt"); // the same as author3

    /**
     * Checks that equals() method works properly for {@link Author} class.
     * equals() should return true if two instances are the same semantically. Otherwise, false.
     * Notice, that the id field isn't involved in this kind of check since all {@link Author} objects
     * have different identifiers.
     */
    @Test
    void testEquals() {
        assertFalse(author1.equals(author2));
        assertFalse(author1.equals(author3));
        assertFalse(author2.equals(author3));
        assertTrue(author3.equals(author4));
    }

    /**
     * Checks that hashCode() method works properly for {@link Author} class.
     * Since instances of {@link Author} have unique values which are represented by their IDs,
     * the result of hashcode() for all separate objects will be different.
     */
    @Test
    void testHashCode() {
        assertTrue(author1.hashCode() != author2.hashCode());
        assertTrue(author1.hashCode() != author3.hashCode());
        assertTrue(author1.hashCode() != author4.hashCode());
        assertFalse(author3.hashCode() == author4.hashCode());
    }

    /**
     * Tests that {@link Author} is properly converted to its string representation.
     */
    @Test
    void testToString() {
        assertTrue(author1.toString().contains("firstName='James'"));
        assertFalse(author1.toString().contains("lastName='Horstmann'"));
        assertTrue(author4.toString().contains("id=4"));
        assertTrue(author4.toString().contains("firstName='Herbert'"));
    }
}