package kz.aslan.springMvc.dao;

import kz.aslan.springMvc.models.Book;
import kz.aslan.springMvc.models.Person;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {
    JdbcTemplate jdbcTemplate;

    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return  jdbcTemplate.query("SELECT * FROM Book",new BeanPropertyRowMapper<>(Book.class));
    }

    public Book show(int id) {
        return  jdbcTemplate.query("SELECT * FROM book WHERE book_id=?",new Object[]{id},new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);
    }


    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO Book(title, author, published) VALUES (?, ?, ?)",book.getTitle(),book.getAuthor(),book.getPublished());
    }

    public void update(int id, Book book) {
        jdbcTemplate.update("UPDATE Book SET title=?, author=?, published=? WHERE book_id=?",book.getTitle(),book.getAuthor(),book.getPublished(),id);
    }

    public void delete(int id){
        jdbcTemplate.update("DELETE  FROM Book WHERE book_id=?",id);


    }

    public void addPerson(int id, int personId) {
        jdbcTemplate.update("UPDATE Book SET person_id=?, instock=? WHERE book_id=?",personId,false,id);
    }

    public void releaseBook(int id) {
        jdbcTemplate.update("UPDATE Book SET person_id=?, instock=?  WHERE book_id=?",null,true,id);
    }
    public Optional<Person> getOwner(int id){
        return jdbcTemplate.query("SELECT * FROM Person WHERE person_id=(SELECT person_id FROM book WHERE book_id=?)"
                ,new Object[]{id},new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }
}
