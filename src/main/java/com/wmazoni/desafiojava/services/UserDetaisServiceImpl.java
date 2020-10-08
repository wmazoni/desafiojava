package com.wmazoni.desafiojava.services;

import com.wmazoni.desafiojava.entities.User;
import com.wmazoni.desafiojava.repositories.UserRepository;
import com.wmazoni.desafiojava.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetaisServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repo.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(email);
        }
        return new UserSS(user.getId(), user.getEmail(), user.getPassword(), user.getPerfis());
    }
}
