package sk.stuba.fei.uim.oop.assignment3.book.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.book.data.BookEntity;
import sk.stuba.fei.uim.oop.assignment3.book.logic.IBookService;
import sk.stuba.fei.uim.oop.assignment3.exceptions.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private IBookService bookService;

    @GetMapping
    public List<BookResponse> getAllBooks() {
        return this.bookService.getAllBooks().stream().map(BookResponse::new).collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<BookResponse> create(@RequestBody BookRequest BookRequest) throws NotFoundException{
        return new ResponseEntity<>(new BookResponse(this.bookService.create(BookRequest)), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable Long id) throws NotFoundException{
        return new ResponseEntity<>(new BookResponse(bookService.findById(id)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BookResponse> deleteBookById(@PathVariable Long id) throws NotFoundException {
        bookService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> updateBook(@PathVariable Long id, @RequestBody BookRequest bookRequest) throws NotFoundException{
        return new ResponseEntity<>(new BookResponse(this.bookService.updateBook(id, bookRequest)), HttpStatus.OK);
    }

    @GetMapping("/{id}/amount")
    public BookAmountResponse getBookAmount(@PathVariable Long id) throws NotFoundException {
        BookAmountResponse bookAmountResponse= new BookAmountResponse();
        getBookById(id);
        Integer bookAmount= this.bookService.getBookAmount(id);
        bookAmountResponse.setAmount(bookAmount);
        return  bookAmountResponse;
    }

    @PostMapping("/{id}/amount")
    public BookAmountResponse increment(@PathVariable Long id, @RequestBody BookEntity newBook) throws NotFoundException{
        BookAmountResponse amount= new BookAmountResponse();
        getBookById(id);
        Integer bookAmount=this.bookService.incrementAmount(id, newBook);
        amount.setAmount(bookAmount);
        return amount;
    }

    @GetMapping("/{id}/lendCount")
    public BookAmountResponse getLendCount(@PathVariable Long id) throws NotFoundException {
        return new BookAmountResponse(bookService.getBookLendCount(id));
    }

}
