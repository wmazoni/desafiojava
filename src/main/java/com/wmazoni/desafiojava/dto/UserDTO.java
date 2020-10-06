package com.wmazoni.desafiojava.dto;

import com.wmazoni.desafiojava.entities.Telephone;
import com.wmazoni.desafiojava.entities.User;

import java.io.Serializable;
import java.time.Instant;
import java.util.*;

public class UserDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private UUID id;
    private String name;
    private String email;
    private String password;
    private Instant created;
    private Instant modified;

    private List<TelephoneDTO> phones = new ArrayList<>();

    public UserDTO() {
    }

    public UserDTO(UUID id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;

    }

    public UserDTO(User entity, List<Telephone> phones) {
        this(entity);
        phones.forEach(pn -> this.phones.add(new TelephoneDTO(pn)));
    }

    public UserDTO(User entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.password = entity.getPassword();
        this.created = entity.getCreated();
        this.modified = entity.getModified();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<TelephoneDTO> getPhones() {
        return phones;
    }

    public void setPhones(List<TelephoneDTO> phones) {
        this.phones = phones;
    }

    public Instant getCreated() {
        return created;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public Instant getModified() {
        return modified;
    }

    public void setModified(Instant modified) {
        this.modified = modified;
    }
}
