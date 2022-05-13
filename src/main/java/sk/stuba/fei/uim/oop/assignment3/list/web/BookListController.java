package sk.stuba.fei.uim.oop.assignment3.list.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
