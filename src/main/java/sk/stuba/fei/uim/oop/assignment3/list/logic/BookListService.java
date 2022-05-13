package sk.stuba.fei.uim.oop.assignment3.list.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.exceptions.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.list.data.BookList;
import sk.stuba.fei.uim.oop.assignment3.list.data.IBookListRepository;

import java.util.List;
import java.util.Optional;

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

    @Override
    public BookList findById(Long id) throws NotFoundException {
        Optional<BookList> list= bookListRepository.findById(id);
        if (list.isEmpty()) throw new NotFoundException();
        return list.get();
    }

    @Override
    public void deleteById(Long id) throws NotFoundException {
        Optional<BookList> list=bookListRepository.findById(id);
        if (list.isEmpty()) throw new NotFoundException();
        this.bookListRepository.deleteById(id);
    }
}
