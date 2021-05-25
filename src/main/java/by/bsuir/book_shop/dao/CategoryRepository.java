package by.bsuir.book_shop.dao;

import by.bsuir.book_shop.entity.Book;
import by.bsuir.book_shop.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

    @Transactional
    @Modifying
    @Query("update Category c set c.name = ?1 where c.id = ?2")
    void updateCategory(String name, Long id);
}
