package sk.stuba.fei.uim.oop.assignment3.author.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.author.data.AuthorEntity;
import sk.stuba.fei.uim.oop.assignment3.author.data.IAuthorRepository;
import sk.stuba.fei.uim.oop.assignment3.author.web.AuthorRequest;
import sk.stuba.fei.uim.oop.assignment3.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService implements IAuthorService{
    @Autowired
    private IAuthorRepository authorRepository;

    @Override
    public List<AuthorEntity> getAllAuthors() {
        return this.authorRepository.findAll();
    }

    @Override
    public AuthorEntity create(AuthorRequest authorRequest) {
        AuthorEntity author= new AuthorEntity();
        author.setName(authorRequest.getName());
        author.setSurname(authorRequest.getSurname());
        author.setBooks(new ArrayList<>());
        return this.authorRepository.save(author);
    }

    @Override
    public AuthorEntity findById(Long id) throws NotFoundException {
        AuthorEntity author = authorRepository.findAuthorEntityById(id);
        if (author == null) throw new NotFoundException();
        return author;
    }

}
