package de.telekom.sea3.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/books")
    public List<Book> index(){
        return bookRepository.findAll();
    }
    @GetMapping("/books/{id}")
    public Book show(@PathVariable Long id){
        return bookRepository.findById(id).get();
    }
    @PostMapping("/books/search")
    public List<Book> search(@RequestBody Map<String, String> body){
        String searchTerm = body.get("text");
        return bookRepository.findByTitleContainingOrContentContaining(searchTerm, searchTerm);
    }
    @PostMapping("/books")
    public Book create(@RequestBody Book book){
        return bookRepository.save(book);
    }
    @PutMapping("/books/{id}")
    public Book update(@PathVariable Long id, @RequestBody Book book){
        // getting blog
        Book blogToUpdate = bookRepository.findById(id).get();
        blogToUpdate.setTitle(book.getTitle());
        blogToUpdate.setAuthor(book.getAuthor());
        blogToUpdate.setDescription(book.getDescription());
        return bookRepository.save(blogToUpdate);
    }
    @DeleteMapping("books/{id}")
    public boolean delete(@PathVariable Long id){
        bookRepository.deleteById(id);
        return true;
    }

}
