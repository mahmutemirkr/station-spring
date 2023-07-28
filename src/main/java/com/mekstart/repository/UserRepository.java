package com.mekstart.repository;

import com.mekstart.domain.User;
import com.mekstart.exception.ResourceNotFoundException;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserName(String userName) throws ResourceNotFoundException;

    boolean existsByUserName(String userName);


}
