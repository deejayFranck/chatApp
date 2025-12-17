package com.project.chatapp.repository;

import com.project.chatapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    List<User> findByIsOnlineTrue();

    boolean existsByUsername(String username);

}
