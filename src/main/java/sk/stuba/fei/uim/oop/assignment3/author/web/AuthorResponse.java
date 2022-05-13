package sk.stuba.fei.uim.oop.assignment3.author.web;

import lombok.Getter;
import lombok.NoArgsConstructor;
import sk.stuba.fei.uim.oop.assignment3.author.data.AuthorEntity;

import java.util.ArrayList;
import java.util.List;

@Getter
public class AuthorResponse {
    private Long id;
    private String name;
    private String surname;
    private List<Long> books;

    public AuthorResponse(AuthorEntity author){
        this.id=author.getId();
        this.name=author.getName();
        this.surname=author.getSurname();
        this.books= new ArrayList<>();
        author.getBooks().forEach(book -> this.books.add(book.getId()));
    }

}
