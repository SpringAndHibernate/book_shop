package by.bsuir.book_shop.dao;

import by.bsuir.book_shop.entity.Availability;
import by.bsuir.book_shop.entity.Book;
import by.bsuir.book_shop.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

    List<Book> findBookByTitle(String title);

    Page<Book> findAllByCategory(Pageable pageable,Category category);

    @Transactional
    @Modifying
    @Query("update Book b set b.availability = ?1, b.rating = ?2," +
            "b.price = ?3,b.title = ?4 where b.id = ?5")
    void updateBook(Availability availability, Integer rating,
                    BigDecimal price,String title,Long id);

    @Transactional
    @Modifying
    @Query("delete from Book b where b.id =?1")
    void deleteBookById(Long id);

}
