package ru.geekbrains.AMorozov.security.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.geekbrains.AMorozov.security.entities.Authority;
import ru.geekbrains.AMorozov.security.entities.Role;
import ru.geekbrains.AMorozov.security.entities.User;
import ru.geekbrains.AMorozov.security.repositories.UserRepository;


import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findUsersByName(s);
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found "+s));
        Collection<SimpleGrantedAuthority> rolesAndAuthorities = mappedRoles(user.getRoles());
        rolesAndAuthorities.addAll(mappedAuthorities(user.getAuthorities()));
        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), rolesAndAuthorities);
    }

    private Collection<SimpleGrantedAuthority> mappedRoles(Collection<Role> roles) {
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.addAll(role.getAuthorities().stream().map(a -> new SimpleGrantedAuthority(a.getName())).collect(Collectors.toList()));
        }
        return authorities;
    }

    private Collection<SimpleGrantedAuthority> mappedAuthorities(Collection<Authority> authorities) {
        return authorities.stream().map(a -> new SimpleGrantedAuthority(a.getName())).collect(Collectors.toList());
    }
}
