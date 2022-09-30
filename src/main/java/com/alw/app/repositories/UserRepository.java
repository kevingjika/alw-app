package com.alw.app.repositories;

import com.alw.app.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Table
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByUsername (String message);
    Optional<User> findUserByEmail (String message);

}