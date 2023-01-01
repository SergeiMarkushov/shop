package ru.gb.shop.auth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.shop.auth.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String name);
}
