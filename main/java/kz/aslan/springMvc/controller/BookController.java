package kz.aslan.springMvc.controller;

import jakarta.validation.Valid;
import kz.aslan.springMvc.models.Book;
import kz.aslan.springMvc.models.Person;
import kz.aslan.springMvc.services.BookService;
import kz.aslan.springMvc.services.PeopleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {
    private  BookService bookService;

    private  PeopleService peopleService;

    public BookController(BookService bookService, PeopleService peopleService) {
        this.bookService = bookService;
        this.peopleService=peopleService;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("books", bookService.findAll());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model,
                       @ModelAttribute("person") Person person){
        if(!bookService.findOne(id).isInStock()) {
            Person owner = bookService.getOwner(id);
            model.addAttribute("owner",owner);
        }
        model.addAttribute("book",bookService.findOne(id));
        model.addAttribute("people",peopleService.findAll());
        return "books/show";
    }

    @PatchMapping("/{id}/person")
    public String addPerson(@PathVariable("id") int id, @ModelAttribute("person") Person person){
        bookService.addPerson(id,person.getId());
        return "redirect:/books";
    }
    @PatchMapping("/{id}/release")
    public String releaseBook(@PathVariable("id") int id){
        bookService.releaseBook(id);
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
        bookService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model){
        model.addAttribute("book",bookService.findOne(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("book") @Valid Book book, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors()){
            return "books/edit";
        }
        bookService.update(id,book);
        return "redirect:/books";
    }


    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        bookService.delete(id);
        return "redirect:/books";
    }

    @GetMapping("/search")
    public String search(Model model){
        model.addAttribute("book",new Book());
        return "books/search";
    }

    @GetMapping("/search/name")
    public String findBook(@RequestParam("name") String name,Model model){
        model.addAttribute("books",bookService.findBooksByName(name));
        return "books/findings";
    }
}
