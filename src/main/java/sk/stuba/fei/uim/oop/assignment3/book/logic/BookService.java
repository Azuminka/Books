package sk.stuba.fei.uim.oop.assignment3.book.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.author.logic.IAuthorService;
import sk.stuba.fei.uim.oop.assignment3.book.data.BookEntity;
import sk.stuba.fei.uim.oop.assignment3.book.data.IBookRepository;
import sk.stuba.fei.uim.oop.assignment3.book.web.BookRequest;
import sk.stuba.fei.uim.oop.assignment3.exceptions.NotFoundException;


import java.util.List;
import java.util.Optional;

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
        book.setAuthor(authorService.findById(bookRequest.getAuthor()));
        book.setPages(bookRequest.getPages());
        book.setAmount(bookRequest.getAmount());
        book.setLendCount(bookRequest.getLendCount());
        authorService.findById(book.getAuthor().getId()).getBooks().add(book);
        return this.bookRepository.save(book);
    }

    @Override
    public BookEntity findById(Long id) throws NotFoundException {
        BookEntity book = bookRepository.findBookEntityById(id);
        if (book == null) throw new NotFoundException();
        return book;
    }

    @Override
    public void deleteById(Long id) throws NotFoundException {
        BookEntity book = bookRepository.findBookEntityById(id);
        if (book==null) throw  new NotFoundException();
        authorService.findById(book.getAuthor().getId()).getBooks().remove(book);
        this.bookRepository.deleteById(id);
    }

    @Override
    public BookEntity updateBook(Long id, BookRequest bookRequest) throws NotFoundException {
        BookEntity bookToFind= this.bookRepository.findBookEntityById(id);
        if (bookToFind==null) throw new NotFoundException();
        if (bookRequest.getName() != null){
            bookToFind.setName(bookRequest.getName());
        }
        if (bookRequest.getDescription()!= null){
            bookToFind.setDescription(bookRequest.getDescription());
        }
        if (bookRequest.getAuthor()!= 0){
            bookToFind.setAuthor(authorService.findById(bookRequest.getAuthor()));
        }
        if (bookRequest.getPages()!=0){
            bookToFind.setPages(bookRequest.getPages());
        }
        return this.bookRepository.save(bookToFind);
    }

    @Override
    public Integer getBookAmount(Long id) {
        Optional<BookEntity> book = this.bookRepository.findById(id);
        BookEntity newBook= new BookEntity();
        if (book.isPresent()){
            newBook= book.get();
        }
        return newBook.getAmount();
    }

    @Override
    public Integer incrementAmount(Long id, BookEntity book) {
        Optional<BookEntity> b = this.bookRepository.findById(id);
        BookEntity oldBook= new BookEntity();
        if (b.isPresent()){
            oldBook= b.get();
        }
        Integer old= oldBook.getAmount();
        oldBook.setAmount(old+book.getAmount());
        this.bookRepository.save(oldBook);
        return oldBook.getAmount();
    }
}

