package ru.advengineering.projectmanager;


import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.advengineering.projectmanager.domain.Role;
import ru.advengineering.projectmanager.domain.User;
import ru.advengineering.projectmanager.repository.RoleRepository;
import ru.advengineering.projectmanager.repository.UserRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserInitRunner implements ApplicationRunner {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) {
        Role roleUser = new Role();
        roleUser.setAuthority("ROLE_USER");
        roleUser = roleRepository.save(roleUser);

        Role roleAdmin = new Role();
        roleAdmin.setAuthority("ROLE_ADMIN");
        roleAdmin = roleRepository.save(roleAdmin);

        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("password"));
        admin.setAuthorities(List.of(roleAdmin, roleUser));
        userRepository.save(admin);

        User user = new User();
        user.setUsername("user");
        user.setPassword(passwordEncoder.encode("password"));
        user.setAuthorities(List.of(roleUser));
        userRepository.save(user);
    }
}
