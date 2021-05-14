package ru.geekbrains.AMorozov.security.configs;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.geekbrains.AMorozov.security.services.UserService;


@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin_panel").hasAuthority("ADMIN_PANEL")
                .antMatchers("/add_user").hasAuthority("ADD_USER")
                .antMatchers("/users").hasAuthority("VIEW_USER")
                .antMatchers("/products/add").hasAuthority("ADD_PRODUCT")
                .antMatchers("/products/delete").hasAuthority("DELETE_PRODUCT")
                .antMatchers("/change_authorities").hasAuthority("CHANGE_USERS_AUTHORITIES")
                .antMatchers("/manager_panel").hasAuthority("MANAGER_PANEL")
                .antMatchers("/products").hasAuthority("EDIT_PRODUCT_PRICE")
                .antMatchers("/products/edit_price").hasAuthority("PRODUCT_LIST")
                .antMatchers("/profile/**").authenticated()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .and()
                .logout().logoutSuccessUrl("/");
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userService);
        return authenticationProvider;
    }

}
