package sk.stuba.fei.uim.oop.assignment3.book.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sk.stuba.fei.uim.oop.assignment3.author.data.AuthorEntity;

@Repository
public interface IBookRepository extends JpaRepository<BookEntity, Long> {
    BookEntity findBookEntityById(Long id);
}
