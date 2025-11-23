package com.emotions.repository;

import com.emotions.dto.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserDto, Long> {
    Optional<UserDto> findByUsername(String username);
    Optional<UserDto> findByEmail(String email);
}
