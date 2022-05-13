package sk.stuba.fei.uim.oop.assignment3.list.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.exceptions.BadRequestException;
import sk.stuba.fei.uim.oop.assignment3.exceptions.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.list.logic.IBookListService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/list")
public class BookListController {

    @Autowired
    private IBookListService bookListService;

    @GetMapping
    public List<BookListResponse> getAllLists(){
        return this.bookListService.getAllLists().stream().map(BookListResponse::new).collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<BookListResponse> createList(){
        return new ResponseEntity<>(new BookListResponse(this.bookListService.createList()), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookListResponse> getListById(@PathVariable Long id) throws NotFoundException{
        return new ResponseEntity<>(new BookListResponse(bookListService.findById(id)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BookListResponse> deleteList(@PathVariable Long id) throws NotFoundException{
        bookListService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{id}/add")
    public ResponseEntity<BookListResponse> addBooks(@PathVariable Long id, @RequestBody BookListRequest request) throws NotFoundException, BadRequestException {
        return new ResponseEntity<>(new BookListResponse(bookListService.addBookToList(id, request.getId())), HttpStatus.OK);
    }

    @DeleteMapping("/{id}/remove")
    public ResponseEntity<BookListResponse> deleteBookFromLandingList(@PathVariable Long id, @RequestBody BookListRequest request) throws NotFoundException{
        bookListService.deleteBookFromLedingList(id, request.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}/lend")
    public ResponseEntity<BookListResponse> lendBook(@PathVariable Long id) throws NotFoundException, BadRequestException{
        bookListService.lendBooks(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
