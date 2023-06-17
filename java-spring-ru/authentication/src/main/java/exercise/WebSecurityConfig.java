package exercise;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    final UserDetailsServiceImpl userDetailsService;

    @Bean
    // Переопределяет схему аутентификации
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // BEGIN
        http.csrf().disable();

        http
                // По умолчанию spring boot даёт доступ к любому url любому аутентифицированному пользователю
                // Мы должны переопределить это повдение
                // Определяем доступы к url
                .authorizeRequests().requestMatchers(HttpMethod.GET, "/users").authenticated()
                .requestMatchers(HttpMethod.POST, "/users").permitAll()
                .requestMatchers("/").permitAll()
                // Доступ к корню сайта доступен всем пользователям
                // Доступ ко всем осталным url даётся только аутентифицированным пользователям
                .anyRequest().authenticated()
                // Используем базовую аутентификацию
                // Имя пользователя и пароль передаются в заголовке в зашифрованном виде
                .and().httpBasic();
        return http.build();
        // END
    }

    // Указываем, что для сравнения хешей паролей
    // будет использоваться кодировщик BCrypt
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}



