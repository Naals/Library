package kz.aslan.springMvc.repositories;

import kz.aslan.springMvc.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BooksRepository extends JpaRepository<Book,Integer> {
    List<Book> findByTitleIsLikeIgnoreCase(String name);
}
