package by.bsuir.book_shop.dao;

import by.bsuir.book_shop.entity.reg.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface TokenRepository extends JpaRepository<VerificationToken,Long> {

    Optional<VerificationToken> findByToken(String token);

    @Transactional
    @Modifying
    @Query("update VerificationToken t set t.confirmedAt = ?2 where t.token = ?1")
    int updateConfirmedAt(String token, LocalDateTime confirmedAt);
}
