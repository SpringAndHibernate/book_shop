package by.bsuir.book_shop.service;

import by.bsuir.book_shop.config.PasswordEncoder;
import by.bsuir.book_shop.dao.UserRepository;
import by.bsuir.book_shop.entity.reg.ApplicationUser;
import by.bsuir.book_shop.entity.reg.VerificationToken;
import by.bsuir.book_shop.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final MailSender mailSender;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, TokenService tokenService, MailSender mailSender) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
        this.mailSender = mailSender;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.getUserByEmail(email)
                .orElseThrow( () ->
                        new UsernameNotFoundException(String.format("User with email %s not found",email)));
    }

    public String signIn(ApplicationUser user){
        if (userRepository.getUserByEmail(user.getEmail()).isPresent()){
            throw new IllegalStateException("This email taken");
        }

        String encode = passwordEncoder.bCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encode);
        userRepository.save(user);

        String randomToken = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken(randomToken, LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(20L),user);
        tokenService.addToken(verificationToken);

        String message = String.format("Hello, %s. Please, click on this link: " +
                "http://localhost:8080/reg/confirm?token=%s to confirm registration.",user.getName(),randomToken);

        mailSender.send(user.getEmail(),"Activation Code",message);

        return randomToken;
    }

    public void enableAppUser(String email) {
        userRepository.enableAppUser(email);
    }

    public List<ApplicationUser> getAllUsers(){

        return userRepository.findAll();
    }

    public Optional<ApplicationUser> getApplicationUser(Long id){

        Optional<ApplicationUser> userById = userRepository.findById(id);
        if (userById.isEmpty()){
            throw new UserNotFoundException(String.format("user with id %s doesn't exist",id));
        }
        return userById;
    }

    public void deleteUser(Long id){
        tokenService.deleteToken(id);
        userRepository.deleteById(id);
    }

}
