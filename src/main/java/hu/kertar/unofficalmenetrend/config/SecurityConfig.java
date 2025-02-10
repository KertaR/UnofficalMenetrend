package hu.kertar.unofficalmenetrend.config;

import hu.kertar.unofficalmenetrend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/js/**", "/css/**", "/").permitAll()
                        .requestMatchers("/registration").permitAll()
                        .requestMatchers(HttpMethod.POST,"/**").permitAll()
                        .requestMatchers("/todo").authenticated()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login") // Beállítja a bejelentkezési oldalt
                        .permitAll()) // Mindenki számára engedélyezi a hozzáférést a bejelentkezési oldalhoz
                .logout(logout -> logout
                        .permitAll() // Mindenki számára engedélyezzük a kijelentkezést
                        .logoutSuccessUrl("/login?logout") // Sikeres kijelentkezés után ide irányít
                );
        return http.build();

    }
}