package com.feature.repositories;


import com.feature.models.User;
import jakarta.annotation.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    @Nullable
    User findByEmail(String email);
}
