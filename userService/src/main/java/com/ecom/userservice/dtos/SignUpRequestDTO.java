package com.ecom.userservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequestDTO {

    private String userName;
    private String userEmail;
    private String password;

}
