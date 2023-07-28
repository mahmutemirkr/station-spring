package com.mekstart.dto;

import com.mekstart.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    // !!! bu class repodan gelen pojo yu DTO ya çevirmek için kullanılacak

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Set<String> roles;

}