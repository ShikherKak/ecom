package com.ecom.userservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User extends BaseModel{

    private String userName;
    private String password;
    private String email;
    @ManyToOne
    private Role role;
}
