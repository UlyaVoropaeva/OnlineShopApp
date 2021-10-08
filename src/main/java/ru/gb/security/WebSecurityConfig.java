package ru.gb.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;

    public WebSecurityConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

     // Аутентификация при помощи БД
     protected void configure(AuthenticationManagerBuilder auth) throws Exception {

         auth.jdbcAuthentication()
                 .dataSource(dataSource)
                 .usersByUsernameQuery(
                         "select login, password, 'true' from users " +
                                 "where login=?")
                 .authoritiesByUsernameQuery(
                         "select login, authority from users " +
                                 "where login=?");
     }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/products/**").hasAnyRole("MANAGER",  "ADMIN")
                .antMatchers("/users").hasRole("ADMIN")
                .antMatchers("/**").hasRole("SUPERADMIN")
                .antMatchers("/products").permitAll()
                .and().formLogin();
    }


}
