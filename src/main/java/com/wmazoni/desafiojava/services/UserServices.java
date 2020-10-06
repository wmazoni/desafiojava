package com.wmazoni.desafiojava.services;

import com.wmazoni.desafiojava.dto.UserDTO;
import com.wmazoni.desafiojava.entities.Telephone;
import com.wmazoni.desafiojava.entities.User;
import com.wmazoni.desafiojava.repositories.TelephoneRepository;
import com.wmazoni.desafiojava.repositories.UserRepository;
import com.wmazoni.desafiojava.services.exceptions.DatabaseException;
import com.wmazoni.desafiojava.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.dao.EmptyResultDataAccessException;
import javax.persistence.EntityNotFoundException;

import java.util.Optional;
import java.util.UUID;


@Service
public class UserServices {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TelephoneRepository telephoneRepository;

    @Transactional(readOnly = true)
    public Page<UserDTO> findAllPaged(PageRequest pageRequest) {
        Page<User> list = userRepository.findAll(pageRequest);
        return list.map(UserDTO::new);
    }

    @Transactional(readOnly = true)
    public UserDTO findById(UUID id) {
        Optional<User> obj = userRepository.findById(id);
        User entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new UserDTO(entity, entity.getPhones());
    }

    @Transactional
    public UserDTO insert(UserDTO dto) {
        User entity = new User();
        Telephone phone = new Telephone();
        copyDTOToEntity(dto, entity, phone);
        phone.setUser(entity);
        entity.getPhones().add(phone);
        entity = userRepository.save(entity);
        phone = telephoneRepository.save(phone);
        return new UserDTO(entity, entity.getPhones());
    }

    @Transactional
    public UserDTO update(UUID id, UserDTO dto) {
        try {
            User entity = userRepository.getOne(id);
            //copyDTOToEntity(dto, entity, phone);
            entity = userRepository.save(entity);
            return new UserDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found" + id);
        }
    }

    public void delete(UUID id) {
        try {
            userRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id " + id + " not found ");
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation");
        }
    }
    private void copyDTOToEntity(UserDTO dto, User entity, Telephone phone) {
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());

        dto.getPhones().forEach(x -> {
            phone.setDdd(x.getDdd());
            phone.setNumber(x.getNumber());
        });
    }
}
