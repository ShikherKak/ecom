package com.ecom.userservice.repositories;

import com.ecom.userservice.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {

    Optional<Token> findTokenByValueAndIsDeletedEquals(String tokenValue, Boolean isDeleted);

}
