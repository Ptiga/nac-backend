package com.socgen.nac.configuration;

import com.socgen.nac.entity.user.User;
import com.socgen.nac.repository.database.UserRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    UserRepositoryInterface userRepository;

    //Voir si l'utilisateur existe en BDD
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findById(login);
        User userLoaded = user.get();
        final List<GrantedAuthority> authorities = new ArrayList<>();
        //TODO
        //authorities.add(new SimpleGrantedAuthority(userLoaded.getRole()));//-> A tester si avec le rôle utilisateur, on peut gérer ('ROLE_'+userLoaded.getRole())
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return new org.springframework.security.core.userdetails.User(userLoaded.getLogin(), userLoaded.getPassword(), authorities);
    }
}
