package com.wmazoni.desafiojava.services;

import com.wmazoni.desafiojava.dto.TelephoneDTO;
import com.wmazoni.desafiojava.dto.TelephoneDTO;
import com.wmazoni.desafiojava.entities.Telephone;
import com.wmazoni.desafiojava.repositories.TelephoneRepository;
import com.wmazoni.desafiojava.services.exceptions.DatabaseException;
import com.wmazoni.desafiojava.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class TelephoneServices {

    @Autowired
    private TelephoneRepository repository;

    @Transactional(readOnly = true)
    public Page<TelephoneDTO> findAllPaged(PageRequest pageRequest) {
        Page<Telephone> list = repository.findAll(pageRequest);
        return list.map(TelephoneDTO::new);
    }

    @Transactional(readOnly = true)
    public TelephoneDTO findById(Long id) {
        Optional<Telephone> obj = repository.findById(id);
        Telephone entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new TelephoneDTO(entity);
    }

    @Transactional
    public TelephoneDTO insert(TelephoneDTO dto) {
        Telephone entity = new Telephone();
        entity.setNumber(dto.getNumber());
        entity.setDdd(dto.getDdd());
        entity = repository.save(entity);
        return new TelephoneDTO(entity);
    }

    @Transactional
    public TelephoneDTO update(Long id, TelephoneDTO dto) {
        try {
            Telephone entity = repository.getOne(id);
            entity.setNumber(dto.getNumber());
            entity.setDdd(dto.getDdd());
            entity = repository.save(entity);
            return new TelephoneDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found" + id);
        }
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id " + id + " not found ");
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation");
        }
    }
}
