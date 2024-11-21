/* package com.example.eventoWebUsc.config;

import com.example.eventoWebUsc.security.FirebaseAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session
                .sessionFixation(sessionFixation -> sessionFixation.migrateSession()) // Configuración para migrar la sesión
            )
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/login", "/css/**", "/js/**").permitAll() // Rutas públicas
                .anyRequest().authenticated() // Rutas protegidas
            )
            .addFilterBefore(new FirebaseAuthenticationFilter(),
                UsernamePasswordAuthenticationFilter.class) // Filtro de Firebase
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/eventos", true) // Redirigir a eventos tras login
                .permitAll() // Permitir acceso al formulario de inicio de sesión
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout") // Redirigir tras logout
                .permitAll()); // Permitir logout

        return http.build();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
        builder.inMemoryAuthentication()
                .withUser("admin") // Usuario
                .password(passwordEncoder().encode("1234")) // Contraseña cifrada
                .roles("ADMIN"); // Rol
        return builder.build();
    }

    // Codificador de contraseñas usando BCrypt (seguro y moderno)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
 */

 package com.example.eventoWebUsc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll() // Permitir todas las solicitudes sin autenticación
            )
            .formLogin(form -> form.disable()) // Deshabilitar el formulario de inicio de sesión
            .logout(logout -> logout.disable()); // Deshabilitar la opción de cerrar sesión

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
