package kz.aslan.springMvc.repositories;

import kz.aslan.springMvc.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PeopleRepository extends JpaRepository<Person ,Integer> {
    Optional<Person> findByEmail(String email);
}
