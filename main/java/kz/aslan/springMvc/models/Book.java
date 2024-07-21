package kz.aslan.springMvc.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Book {
    private int book_id;

    private int person_id;

    @NotEmpty(message = "Should be not empty")
    private String title;

    @NotEmpty(message = "Should be not empty")
    private String author;

    @Min(value = 1, message = "Published year should be greater than 0")
    private int published;
    private boolean inStock=true;

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }

    public Book(int book_id, String title, String author, int published) {
        this.book_id=book_id;
        this.title = title;
        this.author = author;
        this.published = published;
    }

    public Book(){

    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int id) {
        this.book_id = id;
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
}
