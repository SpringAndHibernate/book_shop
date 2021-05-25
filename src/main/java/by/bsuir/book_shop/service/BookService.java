package by.bsuir.book_shop.service;

import by.bsuir.book_shop.dao.BookRepository;
import by.bsuir.book_shop.dao.CategoryRepository;
import by.bsuir.book_shop.entity.Book;
import by.bsuir.book_shop.entity.Category;
import by.bsuir.book_shop.exception.BookNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public BookService(BookRepository bookRepository, CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
    }

    public Page<Book> getAllBooks(int pageNo,int pageSize){

        PageRequest pageRequest = PageRequest.of(pageNo-1,pageSize);
        return bookRepository.findAll(pageRequest);
    }

    public Page<Book> getBooksByCategory(int pageNo,int pageSize,Long id){

        Category category = categoryRepository.findById(id).get();
        PageRequest pageRequest = PageRequest.of(pageNo-1,pageSize);
        return bookRepository.findAllByCategory(pageRequest,category);
    }

    public List<Book> getBooks(){
        return bookRepository.findAll();
    }

    public Optional<Book> getBook(Long id){

        Optional<Book> bookById = bookRepository.findById(id);
        if (bookById.isEmpty()){
            throw new BookNotFoundException(String.format("book with id %s doesn't exist",id));
        }
        return bookById;
    }

    public List<Book> findBooks(String title){

        return bookRepository.findBookByTitle(title);
    }

    public void addBook(Book book){

        bookRepository.save(book);
    }

    public void updateBook(Book book) {

        Long id = book.getId();
        Optional<Book> bookById = bookRepository.findById(id);
        if (bookById.isEmpty()){
            throw new BookNotFoundException(String.format("book with id %s doesn't exist",id));
        }
        bookRepository.updateBook(book.getAvailability(),book.getRating(),book.getPrice(),book.getTitle(),book.getId());
    }

    public void deleteBook(Long id){

        Optional<Book> bookById = bookRepository.findById(id);
        if (bookById.isEmpty()){
            throw new BookNotFoundException(String.format("book with id %s doesn't exist",id));
        }
        bookRepository.deleteBookById(id);
    }
}
