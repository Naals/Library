package kz.aslan.springMvc.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.Cascade;

import java.util.Date;

@Entity
public class Book {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
//    @Column(name="person_id")
//    private int person_id;

    @NotEmpty(message = "Should be not empty")
    @Column(name="title")
    private String title;

    @NotEmpty(message = "Should be not empty")
    @Column(name="author")
    private String author;

    @Min(value = 1, message = "Published year should be greater than 0")
    @Column(name="published")
    private int published;

    @ManyToOne
    @JoinColumn(name="person_id", referencedColumnName = "id")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Person owner;

    @Column(name="date")
    private Date date;

    private boolean inStock=true;

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }

    public Book(int id, String title, String author, int published) {
        this.id=id;
        this.title = title;
        this.author = author;
        this.published = published;
    }

    public Book(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    public int getPerson_id() {
//        return person_id;
//    }
//
//    public void setPerson_id(int person_id) {
//        this.person_id = person_id;
//    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublished() {
        return published;
    }

    public void setPublished(int published) {
        this.published = published;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    public boolean getDelay(){
        return (new Date()).before(this.getDate());
    }
}
