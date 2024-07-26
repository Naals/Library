package kz.aslan.springMvc.services;

import kz.aslan.springMvc.models.Book;
import kz.aslan.springMvc.models.Person;
import kz.aslan.springMvc.repositories.BooksRepository;
import kz.aslan.springMvc.repositories.PeopleRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
@Service
public class BookService {

     private final BooksRepository booksRepository;

     private final PeopleRepository peopleRepository;

    public BookService(BooksRepository booksRepository, PeopleRepository peopleRepository) {
        this.booksRepository = booksRepository;
        this.peopleRepository = peopleRepository;
    }

    public List<Book> findAll() {
        return  booksRepository.findAll(Sort.by("published"));
    }

    public Book findOne(int id) {
        return  booksRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Book book) {
        booksRepository.save(book);
    }

    @Transactional
    public void update(int id, Book book) {
        Book bookToBeUpdated = booksRepository.findById(id).get();

        book.setId(id);
        book.setOwner(bookToBeUpdated.getOwner());

        booksRepository.save(book);
    }

    @Transactional
    public void delete(int id){
        booksRepository.deleteById(id);
    }

    @Transactional
    public void addPerson(int id, int personId) {
        Book book = booksRepository.findById(id).orElse(null);
        Person person = peopleRepository.findById(personId).orElse(null);

        person.getBooks().add(book);
        book.setOwner(person);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 10);
        book.setDate(calendar.getTime());

        book.setInStock(false);

        booksRepository.save(book);
    }

    @Transactional
    public void releaseBook(int id) {

        Book book = booksRepository.findById(id).orElse(null);
        assert book != null;

        Person person = book.getOwner();
        person.getBooks().remove(book);

        book.setInStock(true);
        book.setOwner(null);
        book.setDate(null);

        booksRepository.save(book);
    }

    public Person getOwner(int id){
        return booksRepository.findById(id).map(Book::getOwner).orElse(null);
    }

    public List<Book> findBooksByName(String name){
        return booksRepository.findByTitleIsLikeIgnoreCase(name+"%");
    }
}
