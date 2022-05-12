package sk.stuba.fei.uim.oop.assignment3.book.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.author.data.AuthorEntity;
import sk.stuba.fei.uim.oop.assignment3.author.logic.IAuthorService;
import sk.stuba.fei.uim.oop.assignment3.book.data.BookEntity;
import sk.stuba.fei.uim.oop.assignment3.book.data.IBookRepository;
import sk.stuba.fei.uim.oop.assignment3.book.web.BookRequest;
import sk.stuba.fei.uim.oop.assignment3.exceptions.NotFoundException;


import java.util.List;

@Service
public class BookService implements IBookService{
    @Autowired
    private IBookRepository bookRepository;

    @Autowired
    private IAuthorService authorService;
    @Override
    public List<BookEntity> getAllBooks() {
        return this.bookRepository.findAll();
    }

    @Override
    public BookEntity create(BookRequest bookRequest) throws NotFoundException {
        BookEntity book=new BookEntity();
        book.setName(bookRequest.getName());
        book.setDescription(bookRequest.getDescription());
        book.setAuthor(authorService.findById(book.getAuthor().getId()));
        book.setPages(bookRequest.getPages());
        book.setAmount(bookRequest.getAmount());
        book.setLendCount(bookRequest.getLendCount());
        authorService.findById(book.getAuthor().getId()).getBooks().add(book);
        return bookRepository.save(book);
    }

    @Override
    public BookEntity findById(Long id) throws NotFoundException {
        BookEntity book = bookRepository.findBookEntityById(id);
        if (book == null) throw new NotFoundException();
        return book;
    }
}
