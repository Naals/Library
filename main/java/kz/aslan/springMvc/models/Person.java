package kz.aslan.springMvc.models;

import jakarta.validation.constraints.*;

public class Person {
    private int person_id;
    @NotNull(message = "The name should not be empty.")
    @Size(min=2, max=30, message = "The size of name should be between 2 and 30.")
    private String name;

    @NotNull(message = "The surname should not be empty.")
    @Size(min=2, max=30, message = "The size of name should be between 2 and 30.")
    private String surname;
    @Min(value = 0, message = "Age should be greater than 0")
    private int age;
    @NotEmpty(message = "Should be not empty")
    @Email(message = "Should be the valid")
    private String email;

    @Pattern(regexp = "[A-Z]\\w+, [A-Z]\\w+, \\d{6}",message = "Your address should be in this format: Country, City, Postal Code (6 digits)")
    private String address;
    public Person(){
    }

    public Person(int person_id, String name, String surname, int age, String email, String address) {
        this.person_id = person_id;
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

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int id) {
        this.person_id = id;
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
}
