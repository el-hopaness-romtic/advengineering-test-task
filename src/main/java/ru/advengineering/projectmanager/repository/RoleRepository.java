package ru.advengineering.projectmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.advengineering.projectmanager.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
