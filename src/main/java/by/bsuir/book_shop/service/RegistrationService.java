package by.bsuir.book_shop.service;

import by.bsuir.book_shop.entity.reg.RegisterRequest;
import by.bsuir.book_shop.entity.reg.ApplicationUser;
import by.bsuir.book_shop.entity.reg.UserRole;
import by.bsuir.book_shop.entity.reg.VerificationToken;
import by.bsuir.book_shop.util.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class RegistrationService {

    private final UserService userService;
    private final EmailValidator emailValidator;
    private final TokenService tokenService;

    @Autowired
    public RegistrationService(UserService userService, EmailValidator emailValidator, TokenService tokenService) {
        this.userService = userService;
        this.emailValidator = emailValidator;
        this.tokenService = tokenService;
    }

    public void register(RegisterRequest registerRequest){

        boolean isValid = emailValidator.test(registerRequest.getEmail());
        if (!isValid){
            throw new IllegalStateException("email not valid");
        }

        userService.signIn(new ApplicationUser(
                registerRequest.getName(), registerRequest.getPassword(),
                registerRequest.getEmail(), UserRole.USER
        ));
    }

    public void registerAdmin(RegisterRequest registerRequest){

        boolean isValid = emailValidator.test(registerRequest.getEmail());
        if (!isValid){
            throw new IllegalStateException("email not valid");
        }

        userService.signIn(new ApplicationUser(
                registerRequest.getName(), registerRequest.getPassword(),
                registerRequest.getEmail(), UserRole.ADMIN
        ));
    }

    @Transactional
    public String confirmToken(String token){

        VerificationToken verificationToken = tokenService.getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("token not found"));

        if (verificationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = verificationToken.getExpired();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        tokenService.setConfirmedAt(token);
        userService.enableAppUser(verificationToken.getApplicationUser().getEmail());
        return "confirmed";
    }
}
