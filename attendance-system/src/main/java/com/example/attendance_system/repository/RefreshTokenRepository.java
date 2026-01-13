package com.example.attendance_system.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying; // Requerido para DELETE
import org.springframework.transaction.annotation.Transactional; // Requerido para DELETE
import com.example.attendance_system.entity.RefreshToken;
import com.example.attendance_system.entity.User;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByUser(User user);

    Optional<RefreshToken> findByToken(String token);

    // Estas dos anotaciones son el "seguro" para que no falle el borrado
    @Modifying
    @Transactional
    void deleteByUser(User user);
}