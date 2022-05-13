package sk.stuba.fei.uim.oop.assignment3.list.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.list.data.BookList;
import sk.stuba.fei.uim.oop.assignment3.list.data.IBookListRepository;

import java.util.List;

@Service
public class BookListService implements IBookListService{

    @Autowired
    private IBookListRepository bookListRepository;

    @Override
    public List<BookList> getAllLists() {
        return bookListRepository.findAll();
    }

    @Override
    public BookList createList() {
        return this.bookListRepository.save(new BookList());
    }
}
