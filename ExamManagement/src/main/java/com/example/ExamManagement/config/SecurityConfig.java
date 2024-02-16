package com.example.ExamManagement.config;

import com.example.ExamManagement.auth.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;

import static com.example.ExamManagement.user.Permission.ADMIN_CREATE;
import static com.example.ExamManagement.user.Permission.APPLICANT_CREATE;
import static com.example.ExamManagement.user.Role.ADMIN;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@Configuration
@EnableWebSecurity
@EnableWebMvc
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;


    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);

    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(configurer -> configurer
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("api/auth/logout").authenticated()
                        .requestMatchers("api/auth/changePw").authenticated()
                        .requestMatchers(GET, "/api/applicant/**").hasAnyRole(ADMIN.name())
                        .requestMatchers(POST, "/api/applicant/add").hasAnyRole(ADMIN_CREATE.name())
                        .requestMatchers(POST, "/api/applicant/addMany").hasAnyRole(ADMIN_CREATE.name())
                        .requestMatchers(POST, "api/applicant/add").hasAuthority(APPLICANT_CREATE.name())
                        .anyRequest()
                        .authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy((SessionCreationPolicy.STATELESS)))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout -> logout.logoutUrl("/api/auth/logout").
                        addLogoutHandler(logoutHandler)
                        .logoutSuccessHandler(((request, response, authentication) -> SecurityContextHolder.clearContext()))
                );


//                .requestMatchers("/about", "signup").permitAll()
//                .requestMatchers(HttpMethod.POST, "api/applicant/add").hasRole("APPLICANT")
//                .requestMatchers(HttpMethod.PUT, "api/applicant/**").hasRole("APPLICANT")
//                .requestMatchers( "/api/applicant/**").hasRole("ADMIN")
//               // .requestMatchers(HttpMethod.DELETE, "api/applicant/**").hasRole("ADMIN")
//                .requestMatchers( "/api/applicant/all").hasRole("ADMIN"))

        //   .requestMatchers(HttpMethod.GET, "/api/applicant/**").hasRole("ADMIN"));

        return http.httpBasic(Customizer.withDefaults()).build();
    }


}


