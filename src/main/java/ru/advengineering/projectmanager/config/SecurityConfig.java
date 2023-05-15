package ru.advengineering.projectmanager.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.advengineering.projectmanager.domain.User;
import ru.advengineering.projectmanager.repository.UserRepository;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepo) {
        return username -> {
            User user = userRepo.findByUsername(username);
            if (user != null) return user;

            throw new UsernameNotFoundException("User '" + username + "' not found");
        };
    }

    @Bean
    @Profile("dev")
    @Primary
    public SecurityFilterChain filterChainWithH2Console(HttpSecurity http) throws Exception {
        http = http.authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(PathRequest.toH2Console()).permitAll()
                )
                .csrf().ignoringRequestMatchers(PathRequest.toH2Console())
                .and().headers().frameOptions().sameOrigin()
                .and();
        return filterChain(http);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/", "/login").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin().defaultSuccessUrl("/project", true)
                .and().logout().logoutSuccessUrl("/")
                .and().build();
    }
}


