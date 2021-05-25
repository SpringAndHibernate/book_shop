package by.bsuir.book_shop.dao;

import by.bsuir.book_shop.entity.reg.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<ApplicationUser,Long> {

    @Transactional(readOnly = true)
    Optional<ApplicationUser> getUserByEmail(String email);

    @Transactional
    @Modifying
    @Query("update ApplicationUser  au set au.enabled = true where au.email =?1")
    void enableAppUser(String email);
}
