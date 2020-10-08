package com.wmazoni.desafiojava.services;

import com.wmazoni.desafiojava.entities.Telephone;
import com.wmazoni.desafiojava.entities.User;
import com.wmazoni.desafiojava.entities.enums.Perfil;
import com.wmazoni.desafiojava.repositories.TelephoneRepository;
import com.wmazoni.desafiojava.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private BCryptPasswordEncoder pe;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TelephoneRepository telephoneRepository;

    public void instantiateTestDatabase() throws ParseException {
        User user = new User(null, "Wellington", "wmazoni@protonmail.com", pe.encode("batata"));
        Telephone telephone = new Telephone(null, "999999999", "13");
        user.getPhones().addAll(Arrays.asList(telephone));
        user.addPerfil(Perfil.ADMIN);
        telephone.setUser(user);
        userRepository.saveAll(Arrays.asList(user));
        telephoneRepository.saveAll(Arrays.asList(telephone));
    }
}
