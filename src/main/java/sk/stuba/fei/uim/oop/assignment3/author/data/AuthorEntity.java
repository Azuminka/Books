package sk.stuba.fei.uim.oop.assignment3.author.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import sk.stuba.fei.uim.oop.assignment3.book.data.BookEntity;

@Entity
@Getter
@Setter
public class AuthorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    @OneToMany (orphanRemoval = true)
    private List<BookEntity> books;
}
