package ru.learnup.socialnetwork.security.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import ru.learnup.socialnetwork.security.AuthProvider;
import ru.learnup.socialnetwork.security.AuthService;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final AuthService personDetailsService;
    private final AuthenticationConfiguration configuration;
    private final AuthProvider authProvider;


    @Autowired
    public SecurityConfig(AuthService personDetailsService,
                          AuthenticationConfiguration configuration,
                          AuthProvider authProvider) {
        this.personDetailsService = personDetailsService;
        this.configuration = configuration;
        this.authProvider = authProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http

                .csrf(csrf ->  csrf.disable())

                .authorizeRequests(auth -> {
                    auth.antMatchers("/auth/registration" , "/auth/login", "/error").permitAll();
                    auth.antMatchers("/user/userid", "/error" ).permitAll();

                    auth.anyRequest().authenticated();

                })

                .formLogin(formLogin -> {
                    formLogin.loginPage("/auth/login");
                    formLogin.loginProcessingUrl("/process_login");
                    formLogin.defaultSuccessUrl("/auth/welcome", true);
                    formLogin.failureUrl("/auth/login?error");
                })

                .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login")

                .and()

                .httpBasic(Customizer.withDefaults())

                .build();
    }

//    @Bean
//    public UserDetailsService users (DataSource dataSource) {
//        return new JdbcUserDetailsManager(dataSource);
//    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(authProvider);
        return authenticationManagerBuilder.build();
    }

    @Bean
    public DaoAuthenticationProvider customAuthProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(personDetailsService);
        authProvider.setPasswordEncoder(getPasswordEncoder());
        return authProvider;
    }


    @Bean
    AuthenticationManager authenticationManager() throws Exception {
        return configuration.getAuthenticationManager();
    }

}
