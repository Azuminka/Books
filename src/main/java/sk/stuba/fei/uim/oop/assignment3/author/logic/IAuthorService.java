package sk.stuba.fei.uim.oop.assignment3.author.logic;

import sk.stuba.fei.uim.oop.assignment3.author.data.AuthorEntity;
import sk.stuba.fei.uim.oop.assignment3.author.web.AuthorRequest;
import sk.stuba.fei.uim.oop.assignment3.exceptions.NotFoundException;

import java.util.List;

public interface IAuthorService {
    List<AuthorEntity> getAllAuthors();
    AuthorEntity create(AuthorRequest authorRequest);
    AuthorEntity findById(Long id) throws NotFoundException;
}
