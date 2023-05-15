package ru.advengineering.projectmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.advengineering.projectmanager.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

}
