package sk.stuba.fei.uim.oop.assignment3.book.data;

import lombok.Data;
import sk.stuba.fei.uim.oop.assignment3.author.data.AuthorEntity;

import javax.persistence.*;

@Entity
@Data
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    @ManyToOne
    private AuthorEntity author;
    private int pages;
    private int amount;
    private int lendCount;
}
