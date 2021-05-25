package by.bsuir.book_shop.dao;

import by.bsuir.book_shop.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {

    @Transactional
    @Modifying
    @Query("update Author a set a.name = ?1, a.surname =?2, a.biography = ?3 where a.id = ?4")
    void updateAuthor(String name, String surname,String biography,Long id);
}
