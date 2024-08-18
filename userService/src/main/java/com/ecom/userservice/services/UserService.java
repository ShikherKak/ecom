package com.ecom.userservice.services;

import com.ecom.userservice.controllerAdvice.EmailAlreadyInUseException;
import com.ecom.userservice.controllerAdvice.InvalidCredentialsException;
import com.ecom.userservice.controllerAdvice.UserNotFoundException;
import com.ecom.userservice.dtos.SendEmailMessageDto;
import com.ecom.userservice.dtos.SignUpRequestDTO;
import com.ecom.userservice.models.Token;
import com.ecom.userservice.models.User;
import com.ecom.userservice.repositories.TokenRepository;
import com.ecom.userservice.repositories.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.net.UnknownServiceException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Transactional
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final TokenRepository tokenRepository;
    private final KafkaTemplate<String,String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, TokenRepository tokenRepository, KafkaTemplate<String,String> kafkaTemplate, ObjectMapper objectMapper)
    {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.tokenRepository = tokenRepository;
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }


    public User signUp(String userName, String email, String password) throws EmailAlreadyInUseException{
        Optional<User> optionalUser = userRepository.getUserByEmail(email);

        if(optionalUser.isPresent()) {
            throw new EmailAlreadyInUseException("The Email provided is already in use");
        }
        User newUser = new User();
        newUser.setUserName(userName);
        newUser.setEmail(email);
        newUser.setHashPassword(bCryptPasswordEncoder.encode(password));
        User user = userRepository.save(newUser);

        //sending Email via kafka
        SendEmailMessageDto messageDto = new SendEmailMessageDto();
        messageDto.setFrom("shikherkak@gmail.com");
        messageDto.setTo(user.getEmail());
        messageDto.setSubject("You've signed up");
        messageDto.setBody("Welcome to the party");

        try {
            kafkaTemplate.send(
                    "sendEmail",
                    objectMapper.writeValueAsString(messageDto)
            );
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return user;
    }

    public void deleteUser(Long userId) {

        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty())
        {
            throw new UserNotFoundException("No existing user with ID: "+userId);
        }

        userRepository.deleteById(userId);
    }


    public Token login(String userEmail, String password)
    {
        Optional<User> user = userRepository.getUserByEmail(userEmail);

        if(user.isEmpty()) {
            throw new InvalidCredentialsException("Invalid Credentials");
        }

        String tok1 = user.get().getHashPassword();
        String tok2 = bCryptPasswordEncoder.encode((password));

        //if (!bCryptPasswordEncoder.matches(password, user.get().getHashPassword()))
        if(!bCryptPasswordEncoder.matches(password,user.get().getHashPassword()))
        {
            throw new InvalidCredentialsException("Invalid Credentials");
        }

        Token token = new Token();
        token.setUser(user.get());
        token.setValue(UUID.randomUUID().toString());
        token.setExpieryDate(get30DaysLaterDate());
        token.setIsDeleted(false);
        return tokenRepository.save(token);
    }

    private Date get30DaysLaterDate()
    {
        LocalDate expDate = LocalDate.now().plusDays(30);
        return Date.from(expDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public void logout(String tokenValue)
    {
    Optional<Token> optionalToken = tokenRepository.findTokenByValueAndIsDeletedEquals(tokenValue,false);
    if(optionalToken.isEmpty()) {
        return;
    }
    optionalToken.get().setIsDeleted(true);
    tokenRepository.save(optionalToken.get());
    }

}
