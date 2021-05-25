package by.bsuir.book_shop.entity.reg;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "VerificationToken")
@Table(name = "verification_token")
public class VerificationToken {

    @Id
    @SequenceGenerator(name = "token_sequence",sequenceName = "token_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "token_sequence")
    @Column(name = "id",updatable = false)
    private Long id;

    @Column(name = "token",nullable = false)
    private String token;

    @Column(name = "created",nullable = false)
    private LocalDateTime created;

    @Column(name = "expired",nullable = false)
    private LocalDateTime expired;

    @Column(name = "confirmed_at")
    private LocalDateTime confirmedAt;

    @ManyToOne()
    @JoinColumn(name = "user_id",foreignKey = @ForeignKey(name = "application_user_fk"))
    private ApplicationUser applicationUser;

    public VerificationToken(String token, LocalDateTime created,
                             LocalDateTime expired, ApplicationUser applicationUser) {
        this.token = token;
        this.created = created;
        this.expired = expired;
        this.applicationUser = applicationUser;
    }

    public VerificationToken() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getExpired() {
        return expired;
    }

    public void setExpired(LocalDateTime expired) {
        this.expired = expired;
    }

    public LocalDateTime getConfirmedAt() {
        return confirmedAt;
    }

    public void setConfirmedAt(LocalDateTime confirmedAt) {
        this.confirmedAt = confirmedAt;
    }

    public ApplicationUser getApplicationUser() {
        return applicationUser;
    }

    public void setApplicationUser(ApplicationUser applicationUser) {
        this.applicationUser = applicationUser;
    }
}
