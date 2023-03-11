package org.olhas.librarysystem.models;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.olhas.librarysystem.server.jaxb.adapters.DateAdapter;

import java.time.LocalDate;

/**
 * Class that represents real-world loans in {@link Library}.
 * Contains information like a loan ID, student ID, book ID,starting date of the loan,
 * and the ending date of the loan.
 *
 * @author Olha
 * @version 1.0
 */
@XmlRootElement(name = "Loan")
@XmlType(propOrder = { "id", "studentID", "bookID", "loanFrom", "loanTo"})
public class Loan {
    private int id;
    private int studentID;
    private int bookID;
    private LocalDate loanFrom;
    private LocalDate loanTo;

    /**
     * Class constructor that creates a new {@link Loan} instance.
     *
     * @param id loan ID
     * @param studentID student ID
     * @param bookID book ID
     * @param loanFrom starting date
     * @param loanTo ending date
     */
    public Loan(int id, int studentID, int bookID, LocalDate loanFrom, LocalDate loanTo) {
        this.id = id;
        this.studentID = studentID;
        this.bookID = bookID;
        this.loanFrom = loanFrom;
        this.loanTo = loanTo;
    }

    /**
     * No-arg default constructor.
     */
    public Loan() {
    }

    /**
     * Returns an ID for this loan.
     *
     * @return loan ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets a new ID for this loan.
     *
     * @param id loan ID
     */
    @XmlElement(name = "Loan_ID")
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns a student ID the loan was issued upon.
     *
     * @return student ID
     */
    public int getStudentID() {
        return studentID;
    }

    /**
     * Sets a new ID for the student the loan was issued upon.
     *
     * @param studentID new student ID
     */
    @XmlElement(name = "Loan_Student_ID")
    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    /**
     * Returns a book ID that was loaned by a {@link Student}.
     *
     * @return book ID
     */
    public int getBookID() {
        return bookID;
    }

    /**
     * Sets a new book ID for this loan.
     *
     * @param bookID new book ID
     */
    @XmlElement(name = "Loan_Book_ID")
    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    /**
     * Returns the starting date of this loan.
     *
     * @return starting date of the loan
     */
    public LocalDate getLoanFrom() {
        return loanFrom;
    }

    /**
     * Sets a new starting date for this loan.
     *
     * @param loanFrom a new starting date
     */
    @XmlElement(name = "Loan_From")
    @XmlJavaTypeAdapter(value = DateAdapter.class)
    public void setLoanFrom(LocalDate loanFrom) {
        this.loanFrom = loanFrom;
    }

    /**
     * Returns the ending date of this loan.
     *
     * @return ending date of the loan
     */
    public LocalDate getLoanTo() {
        return loanTo;
    }

    /**
     * Sets a new ending date for this loan.
     *
     * @param loanTo a new ending date
     */
    @XmlElement(name = "Loan_To")
    @XmlJavaTypeAdapter(value = DateAdapter.class)
    public void setLoanTo(LocalDate loanTo) {
        this.loanTo = loanTo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Loan loan)) return false;

        if (getId() != loan.getId()) return false;
        if (getStudentID() != loan.getStudentID()) return false;
        if (getBookID() != loan.getBookID()) return false;
        if (!getLoanFrom().equals(loan.getLoanFrom())) return false;
        return getLoanTo().equals(loan.getLoanTo());
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getStudentID();
        result = 31 * result + getBookID();
        result = 31 * result + getLoanFrom().hashCode();
        result = 31 * result + getLoanTo().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "id=" + id +
                ", studentID=" + studentID +
                ", bookID=" + bookID +
                ", loanFrom=" + loanFrom +
                ", loanTo=" + loanTo +
                '}';
    }
}
