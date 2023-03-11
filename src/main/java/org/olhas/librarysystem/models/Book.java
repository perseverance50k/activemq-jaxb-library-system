package org.olhas.librarysystem.models;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.olhas.librarysystem.server.jaxb.adapters.DateAdapter;

import java.time.LocalDate;

/**
 * Class that represents the real-world book entity.
 * Contains information like a book ID, author ID, title, edition, and a release date.
 *
 * @author Olha
 * @version 1.0
 */
@XmlRootElement(name = "Book")
@XmlType(propOrder = { "id", "authorID", "title", "edition", "releaseDate" })
public class Book {
    private int id;
    private int authorID;
    private String title;
    private int edition;
    private LocalDate releaseDate;

    /**
     * Class constructor that creates a new {@link Book} entity.
     *
     * @param id the ID of the book
     * @param authorID the author ID
     * @param title the title of the book
     * @param edition the edition of the book
     * @param releaseDate the date when the book was released
     */
    public Book(int id, int authorID, String title, int edition, LocalDate releaseDate) {
        this.id = id;
        this.authorID = authorID;
        this.title = title;
        this.edition = edition;
        this.releaseDate = releaseDate;
    }

    /**
     * No-arg default constructor.
     */
    public Book() {
    }

    /**
     * Returns an ID of this book.
     *
     * @return book ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets a new ID for this book.
     *
     * @param id a new book ID
     */
    @XmlElement(name = "Book_ID")
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns an author ID of this book.
     *
     * @return the author ID
     */
    public int getAuthorID() {
        return authorID;
    }

    /**
     * Sets a new author ID for this book.
     *
     * @param authorID a new author ID
     */
    @XmlElement(name = "Book_Author_ID")
    public void setAuthorID(int authorID) {
        this.authorID = authorID;
    }

    /**
     * Returns a title of this book.
     *
     * @return the book title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets a new title for this book.
     *
     * @param title a new book title
     */
    @XmlElement(name = "Book_Title")
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns an edition of this book.
     *
     * @return the book edition
     */
    public int getEdition() {
        return edition;
    }

    /**
     * Sets a new edition for this book.
     *
     * @param edition a new edition number
     */
    @XmlElement(name = "Book_Edition")
    public void setEdition(int edition) {
        this.edition = edition;
    }

    /**
     * Returns a release date for this book.
     *
     * @return the book release date
     */
    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    /**
     * Sets a new release date for this book.
     *
     * @param releaseDate a new release date
     */
    @XmlElement(name = "Book_Release_Date")
    @XmlJavaTypeAdapter(value = DateAdapter.class)
    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book book)) return false;

        if (getId() != book.getId()) return false;
        if (getAuthorID() != book.getAuthorID()) return false;
        if (getEdition() != book.getEdition()) return false;
        if (!getTitle().equals(book.getTitle())) return false;
        return getReleaseDate().equals(book.getReleaseDate());
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getAuthorID();
        result = 31 * result + getTitle().hashCode();
        result = 31 * result + getEdition();
        result = 31 * result + getReleaseDate().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", authorID=" + authorID +
                ", title='" + title + '\'' +
                ", edition='" + edition + '\'' +
                ", releaseDate=" + releaseDate +
                '}';
    }
}
