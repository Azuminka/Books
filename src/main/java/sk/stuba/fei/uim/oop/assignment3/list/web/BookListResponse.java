package sk.stuba.fei.uim.oop.assignment3.list.web;

import lombok.Getter;
import sk.stuba.fei.uim.oop.assignment3.book.web.BookResponse;
import sk.stuba.fei.uim.oop.assignment3.list.data.BookList;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class BookListResponse {
    private Long id;
    private List<BookResponse> lendingList;
    private boolean lended;

    public BookListResponse(BookList bookList){
        this.id=bookList.getId();
        this.lendingList=bookList.getLendingList().stream()
                                                    .map(BookResponse::new)
                                                    .collect(Collectors.toList());
        this.lended=bookList.isLended();
    }

}
