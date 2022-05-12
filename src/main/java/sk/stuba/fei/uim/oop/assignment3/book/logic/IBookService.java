package sk.stuba.fei.uim.oop.assignment3.book.logic;


import sk.stuba.fei.uim.oop.assignment3.author.data.AuthorEntity;
import sk.stuba.fei.uim.oop.assignment3.book.data.BookEntity;
import sk.stuba.fei.uim.oop.assignment3.book.web.BookRequest;
import sk.stuba.fei.uim.oop.assignment3.exceptions.NotFoundException;

import java.util.List;

public interface IBookService {
    List<BookEntity> getAllBooks();
    BookEntity create(BookRequest bookRequest) throws NotFoundException;
    BookEntity findById(Long id) throws NotFoundException;
}
