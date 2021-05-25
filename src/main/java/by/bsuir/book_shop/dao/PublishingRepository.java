package by.bsuir.book_shop.dao;

import by.bsuir.book_shop.entity.Publishing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Repository
public interface PublishingRepository extends JpaRepository<Publishing,Long> {

    @Transactional
    @Modifying
    @Query("update Publishing p set p.name =?1,p.website =?2,p.phone = ?3,p.address.city =?4," +
            "p.address.street = ?5,p.address.building = ?6,p.address.postalCode =?7 where p.id =?8")
    void updatePublishing(String name,String website,String phone,String city,
                                 String street,String building,String postalCode,Long id);
}
