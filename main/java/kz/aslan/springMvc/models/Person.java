package kz.aslan.springMvc.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.Cascade;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name="Person")
public class Person {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull(message = "The name should not be empty.")
    @Size(min=2, max=30, message = "The size of name should be between 2 and 30.")
    @Column(name="name")
    private String name;

    @NotNull(message = "The surname should not be empty.")
    @Size(min=2, max=30, message = "The size of name should be between 2 and 30.")
    @Column(name="surname")
    private String surname;
    @Min(value = 0, message = "Age should be greater than 0")
    @Column(name="age")
    private int age;
    @NotEmpty(message = "Should be not empty")
    @Email(message = "Should be the valid")
    @Column(name="email")
    private String email;

    @Pattern(regexp = "[A-Z]\\w+, [A-Z]\\w+, \\d{6}",message = "Your address should be in this format: Country, City, Postal Code (6 digits)")
    @Column(name="address")
    private String address;

    @OneToMany(mappedBy = "owner",fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private List<Book> books;
    public Person(){
    }

    public Person(int id, String name, String surname, int age, String email, String address) {
        this.id = id;
        this.name = name;
        this.surname=surname;
        this.age = age;
        this.email = email;
        this.address=address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id && age == person.age && Objects.equals(name, person.name) && Objects.equals(surname, person.surname) && Objects.equals(email, person.email) && Objects.equals(address, person.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, age, email, address);
    }
}
