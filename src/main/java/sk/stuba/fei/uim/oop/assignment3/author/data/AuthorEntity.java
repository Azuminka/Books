package sk.stuba.fei.uim.oop.assignment3.author.data;

import lombok.Data;
import sk.stuba.fei.uim.oop.assignment3.book.data.BookEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class AuthorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    @OneToMany (orphanRemoval = true)
    private List<BookEntity> books;
}
