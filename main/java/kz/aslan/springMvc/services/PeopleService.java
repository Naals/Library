package kz.aslan.springMvc.services;

import kz.aslan.springMvc.models.Book;
import kz.aslan.springMvc.models.Person;
import kz.aslan.springMvc.repositories.PeopleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Transactional(readOnly = true)
@Service
public class PeopleService {

    private final PeopleRepository peopleRepository;

    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Transactional
    public void save(Person person){

        peopleRepository.save(person);
    }

    public Person findOne(int id ){
        return peopleRepository.findById(id).orElse(null);
    }

    public List<Person> findAll(){
        return peopleRepository.findAll();
    }

    public Person findOne(String email ){
        Optional<Person> person = peopleRepository.findByEmail(email);
        return person.orElse(null);
    }

    @Transactional
    public void update(int id, Person person){
        person.setId(id);
        peopleRepository.save(person);
    }

    @Transactional
    public void delete(int id){
        peopleRepository.deleteById(id);
    }

    public List<Book> getBook(int id){
//        return jdbcTemplate.query("select * from Book where person_id=?",
//                new Object[]{id},new BeanPropertyRowMapper<>(Book.class));
        return Objects.requireNonNull(peopleRepository.findById(id).orElse(null)).getBooks();
    }
}
