package sk.stuba.fei.uim.oop.assignment3.list.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.book.data.BookEntity;
import sk.stuba.fei.uim.oop.assignment3.book.logic.IBookService;
import sk.stuba.fei.uim.oop.assignment3.exceptions.BadRequestException;
import sk.stuba.fei.uim.oop.assignment3.exceptions.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.list.data.BookList;
import sk.stuba.fei.uim.oop.assignment3.list.data.IBookListRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BookListService implements IBookListService{

    @Autowired
    private IBookListRepository bookListRepository;

    @Autowired
    private IBookService bookService;

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
        BookList list=bookListRepository.findById(id).orElseThrow(NotFoundException::new);
        if (list.isLended()){
            list.getLendingList().forEach(book->book.setLendCount(book.getLendCount()-1));
        }
        this.bookListRepository.deleteById(id);
    }

    @Override
    public BookList addBookToList(Long listId, Long bookId) throws NotFoundException, BadRequestException {
        BookEntity book= bookService.findById(bookId);
        if (book.getAmount()<= book.getLendCount()) throw  new BadRequestException();
        BookList list = findById(listId);
        if (list == null) {
            throw new NotFoundException();
        }
        if (list.isLended()) throw new BadRequestException();
        Optional<BookEntity> any = list.getLendingList().stream().filter(b -> Objects.equals(book.getId(), b.getId())).findAny();
        if (any.isPresent()) throw new BadRequestException();
        list.getLendingList().add(book);
        bookListRepository.save(list);
        return list;
    }

    @Override
    public void lendBooks(Long id) throws NotFoundException, BadRequestException {
        BookList list= findById(id);
        if (list == null) {
            throw new NotFoundException();
        }
        if (list.isLended()){
            throw new BadRequestException();
        }
        list.getLendingList().forEach(book ->
                book.setLendCount(book.getLendCount()+1)
        );
        list.setLended(true);
    }

    @Override
    public void deleteBookFromLedingList(Long listId, Long bookId) throws NotFoundException {
        BookEntity book= bookService.findById(bookId);
        if (book == null) throw new NotFoundException();
        BookList list = findById(listId);
        if (list == null) throw new NotFoundException();
        if (!(list.getLendingList().remove(book))) throw new NotFoundException();
        if (list.isLended()){
            book.setLendCount(book.getLendCount()-1);
        }
    }
}
