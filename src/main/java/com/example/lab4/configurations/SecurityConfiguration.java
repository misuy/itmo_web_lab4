package com.example.lab4.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests().requestMatchers("/api/**", "/main.html").authenticated()
            .anyRequest().permitAll().and()
            .formLogin().loginPage("/login").permitAll().loginProcessingUrl("/login").defaultSuccessUrl("/main.html", true).and()
            .httpBasic().and()
            .csrf().disable();

        return http.build();
    }

    @Bean
    public JdbcUserDetailsManager users(@Autowired DataSource dataSource) {
        UserDetails user = User.builder().username("misuy").password("{bcrypt}$2a$12$p.SBhnoIsLaCijU3vjHlXuODQA016SZhfZGC6fpTanJKRQxoO95QO").roles("USER").build();
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        if (!jdbcUserDetailsManager.userExists(user.getUsername())) jdbcUserDetailsManager.createUser(user);
        return jdbcUserDetailsManager;
        DaoSupp
    }

}
