package sk.stuba.fei.uim.oop.assignment3.author.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.author.data.AuthorEntity;
import sk.stuba.fei.uim.oop.assignment3.author.data.IAuthorRepository;
import sk.stuba.fei.uim.oop.assignment3.author.web.AuthorRequest;
import sk.stuba.fei.uim.oop.assignment3.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Override
    public void deleteById(Long id) throws NotFoundException {
        Optional<AuthorEntity> author= authorRepository.findById(id);
        if (author.isEmpty()) throw new NotFoundException();
        this.authorRepository.deleteById(id);
    }

    @Override
    public AuthorEntity updateAuthor(Long id, AuthorRequest authorRequest) throws NotFoundException {
        AuthorEntity authorToFind=this.authorRepository.findAuthorEntityById(id);
        if (authorToFind==null) throw new NotFoundException();
        if (authorRequest.getName()!= null){
            authorToFind.setName(authorRequest.getName());
        }
        if (authorRequest.getSurname()!= null){
            authorToFind.setSurname(authorRequest.getSurname());
        }
        return this.authorRepository.save(authorToFind);
    }
}
