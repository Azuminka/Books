package sk.stuba.fei.uim.oop.assignment3.author.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.author.logic.IAuthorService;
import sk.stuba.fei.uim.oop.assignment3.exceptions.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    private IAuthorService authorService;

    @GetMapping
    public List<AuthorResponse> getAllAuthors() {
        return this.authorService.getAllAuthors().stream().map(AuthorResponse::new).collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<AuthorResponse> create(@RequestBody AuthorRequest authorRequest){
        return new ResponseEntity<>(new AuthorResponse(this.authorService.create(authorRequest)), HttpStatus.CREATED);
    };

    @GetMapping("/{id}")
    public AuthorResponse getAuthorById(@PathVariable Long id) throws NotFoundException {
        return new AuthorResponse(authorService.findById(id));
    }
}
