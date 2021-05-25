package by.bsuir.book_shop.service;

import by.bsuir.book_shop.dao.AuthorRepository;
import by.bsuir.book_shop.entity.Author;
import by.bsuir.book_shop.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> getAllAuthors(){

        return authorRepository.findAll();
    }

    public Optional<Author> getAuthor(Long id){

        return authorRepository.findById(id);
    }

    public void addAuthor(Author author){

        authorRepository.save(author);
    }

    public void updateAuthor(Author author){

        authorRepository.updateAuthor(author.getName(),author.getSurname(),author.getBiography(),author.getId());
    }

    public void deleteAuthor(Long id){

        authorRepository.deleteById(id);
    }
}
