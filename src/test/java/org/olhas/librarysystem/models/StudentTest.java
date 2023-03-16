package org.olhas.librarysystem.models;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    private final Student student1 = new Student(1, "John", "Doe",
                LocalDate.of(2000, 2, 10), "Street str.", 2);
    private final Student student2 = new Student(2, "Alex", "Branson",
                LocalDate.of(1996, 7, 27), "Hlinki 2 str.", 4);
    private final Student student3 = new Student(3, "Alex", "Branson",
            LocalDate.of(1996, 7, 27), "Hlinki 2 str.", 4); // the same as student2

    /**
     * Checks that equals() method works properly for {@link Student} class.
     * equals() should return true if two instances are the same semantically. Otherwise, false.
     * Notice, that the id field isn't involved in this kind of check since all {@link Student} objects
     * have different identifiers.
     */
    @Test
    void testEquals() {
        assertFalse(student1.equals(student2));
        assertTrue(student2.equals(student3));
    }

    /**
     * Checks that hashCode() method works properly for {@link Student} class.
     * Since instances of {@link Student} have unique values which are represented by their IDs,
     * the result of hashcode() for all separate objects will be different.
     */
    @Test
    void testHashCode() {
        assertTrue(student1.hashCode() != student2.hashCode());
        assertTrue(student1.hashCode() != student3.hashCode());
        assertTrue(student2.hashCode() != student3.hashCode());
    }

    @Test
    void hashCodeIsTheSameIfTwoVarsPointToTheSameObject() {
        Student someStudent = student1;
        assertTrue(someStudent.hashCode() == student1.hashCode());
    }

    /**
     * Tests that {@link Student} is properly converted to its string representation.
     */
    @Test
    void testToString() {
        assertTrue(student1.toString().contains("id=1"));
        assertTrue(student1.toString().contains("lastName='Doe'"));

        assertTrue(student2.toString().contains("id=2"));
        assertTrue(student2.toString().contains("address='Hlinki 2 str.'"));

        assertFalse(student3.toString().contains("course=2"));
    }
}