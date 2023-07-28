package com.mekstart.service;

import com.mekstart.domain.Role;
import com.mekstart.domain.User;
import com.mekstart.domain.enums.UserRole;
import com.mekstart.dto.request.RegisterRequest;
import com.mekstart.exception.ConflictException;
import com.mekstart.exception.ResourceNotFoundException;
import com.mekstart.exception.message.ErrorMessage;
import com.mekstart.repository.RoleRepository;
import com.mekstart.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registerUser(RegisterRequest registerRequest) {

        if(userRepository.existsByUserName(registerRequest.getUserName())){


            throw new ConflictException("Kayıtlı kullanıcı bulunamadı");

        }

        Role role = roleRepository.findByName(UserRole.ROLE_ADMIN).orElseThrow(
                ()-> new ResourceNotFoundException("Role bilgisi bulunamadı")
        );

        Set<Role> roles = new HashSet<>();
        roles.add(role);
        User user = new User();
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setUserName(registerRequest.getUserName());
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        userRepository.save(user);

    }


}
