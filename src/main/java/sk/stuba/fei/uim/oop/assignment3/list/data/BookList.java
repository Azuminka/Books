package sk.stuba.fei.uim.oop.assignment3.list.data;

import lombok.Data;
import sk.stuba.fei.uim.oop.assignment3.book.data.BookEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class BookList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany
    private List<BookEntity> lendingList= new ArrayList<>();
    private boolean lended=false;
}
