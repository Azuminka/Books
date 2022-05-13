package sk.stuba.fei.uim.oop.assignment3.list.logic;

import sk.stuba.fei.uim.oop.assignment3.exceptions.BadRequestException;
import sk.stuba.fei.uim.oop.assignment3.exceptions.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.list.data.BookList;

import java.util.List;

public interface IBookListService {
    List<BookList> getAllLists();
    BookList createList();
    BookList findById(Long id) throws NotFoundException;
    void deleteById(Long id) throws NotFoundException;
    BookList addBookToList(Long listId, Long bookId) throws NotFoundException, BadRequestException;
    void lendBooks(Long id) throws NotFoundException, BadRequestException;
    void deleteBookFromLedingList(Long listId, Long bookId) throws NotFoundException;
}
