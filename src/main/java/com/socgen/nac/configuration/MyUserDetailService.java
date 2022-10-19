package com.socgen.nac.configuration;

import com.socgen.nac.entity.user.User;
import com.socgen.nac.repository.database.UserRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MyUserDetailService implements UserDetailsService {

    @Autowired
    UserRepositoryInterface userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findById(login);
        User userLoaded = user.get();
        final List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(userLoaded.getRole()));
        return new org.springframework.security.core.userdetails.User(userLoaded.getLogin(), userLoaded.getPassword(), authorities);
    }
}
