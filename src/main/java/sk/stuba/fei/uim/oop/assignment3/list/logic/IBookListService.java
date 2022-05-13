package sk.stuba.fei.uim.oop.assignment3.list.logic;

import sk.stuba.fei.uim.oop.assignment3.list.data.BookList;

import java.util.List;

public interface IBookListService {
    List<BookList> getAllLists();
    BookList createList();
}
