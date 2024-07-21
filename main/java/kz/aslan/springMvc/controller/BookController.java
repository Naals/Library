package kz.aslan.springMvc.controller;

import jakarta.validation.Valid;
import kz.aslan.springMvc.dao.BookDAO;
import kz.aslan.springMvc.dao.PersonDAO;
import kz.aslan.springMvc.models.Book;
import kz.aslan.springMvc.models.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {
    private BookDAO bookDAO;
    private PersonDAO personDAO;

    public BookController(BookDAO bookDAO,PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO=personDAO;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("books", bookDAO.index());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model,
                       @ModelAttribute("person") Person person){
        if(!bookDAO.show(id).isInStock()) {
            Optional<Person> personOptional = bookDAO.getOwner(id);
            model.addAttribute("owner",personOptional.get());
        }
        model.addAttribute("book",bookDAO.show(id));
        model.addAttribute("people",personDAO.index());
        return "books/show";
    }

    @PatchMapping("/{id}/person")
    public String addPerson(@PathVariable("id") int id, @ModelAttribute("person") Person person){
        bookDAO.addPerson(id,person.getPerson_id());
        return "redirect:/books";
    }
    @PatchMapping("/{id}/release")
    public String releaseBook(@PathVariable("id") int id){
        bookDAO.releaseBook(id);
        return "redirect:/books";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book){
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "books/new";
        }
        bookDAO.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model){
        model.addAttribute("book",bookDAO.show(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("book") @Valid Book book, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors()){
            return "books/edit";
        }
        bookDAO.update(id,book);
        return "redirect:/books";
    }


    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        bookDAO.delete(id);
        return "redirect:/books";
    }
}
