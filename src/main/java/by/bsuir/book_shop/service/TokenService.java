package by.bsuir.book_shop.service;

import by.bsuir.book_shop.dao.TokenRepository;
import by.bsuir.book_shop.entity.reg.VerificationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TokenService {

    private final TokenRepository tokenRepository;

    @Autowired
    public TokenService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public void addToken(VerificationToken token){
        tokenRepository.save(token);
    }

    public Optional<VerificationToken> getToken(String token) {
        return tokenRepository.findByToken(token);
    }

    public void setConfirmedAt(String token) {
        tokenRepository.updateConfirmedAt(token, LocalDateTime.now());
    }

    public void deleteToken(Long id){
        tokenRepository.deleteById(id);
    }
}
