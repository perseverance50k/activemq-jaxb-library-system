package org.olhas.librarysystem.models;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.olhas.librarysystem.server.jaxb.adapters.DateAdapter;

import java.time.LocalDate;

/**
 * Class that represents a real-world student.
 * Contains information like a student ID, first name, last name, date of birth, address, and a course number.
 *
 * @author Olha
 * @version 1.0
 */
public class Student {
    private final int id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String address;
    private int course;

    public Student(int id, String firstName, String lastName, LocalDate dateOfBirth, String address, int course) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.course = course;
    }

    /**
     * Returns the ID of this student.
     *
     * @return student ID
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the first name of this student.
     *
     * @return student's first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets a new first name for this student.
     *
     * @param firstName a new first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Returns the first name of this student.
     *
     * @return student's last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets a new last name for this student.
     *
     * @param lastName a new last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Returns the date of birth of this student.
     *
     * @return student's date of birth
     */
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Sets a date of birth for this student.
     *
     * @param dateOfBirth date of birth
     */
    @XmlElement(name = "Student_Birthdate")
    @XmlJavaTypeAdapter(value = DateAdapter.class)
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Returns the address of this student.
     *
     * @return student's address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets a new address for this student.
     *
     * @param address a new address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Returns a course number of this student.
     *
     * @return course number
     */
    public int getCourse() {
        return course;
    }

    /**
     * Sets a new course number for this student.
     *
     * @param course a new course number
     */
    public void setCourse(int course) {
        this.course = course;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student student)) return false;

        if (getCourse() != student.getCourse()) return false;
        if (!getFirstName().equals(student.getFirstName())) return false;
        if (!getLastName().equals(student.getLastName())) return false;
        if (!getDateOfBirth().equals(student.getDateOfBirth())) return false;
        return getAddress().equals(student.getAddress());
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getFirstName().hashCode();
        result = 31 * result + getLastName().hashCode();
        result = 31 * result + getDateOfBirth().hashCode();
        result = 31 * result + getAddress().hashCode();
        result = 31 * result + getCourse();
        return result;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", address='" + address + '\'' +
                ", course=" + course +
                '}';
    }
}
