package com.wmazoni.desafiojava.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wmazoni.desafiojava.entities.enums.Perfil;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "tb_user")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid", updatable = false)
    private UUID id;

    private String name;


    @Column(unique = true)
    private String email;

    @JsonIgnore
    private String password;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant created;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant modified;


    @OneToMany(mappedBy = "user")
    List<Telephone> phones = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "PERFIS")
    private Set<Integer> perfis = new HashSet<>();

    public User() {
        addPerfil(Perfil.USERS);
    }

    public User(UUID id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        addPerfil(Perfil.USERS);

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

    public List<Telephone> getPhones() {
        return phones;
    }

    public void setPhones(List<Telephone> phones) {
        this.phones = phones;
    }

    public Instant getCreated() {
        return created;
    }

    public Instant getModified() {
        return modified;
    }

    public Set<Perfil> getPerfis() {
        return perfis.stream().map(Perfil::toEnum).collect(Collectors.toSet());
    }

    public void addPerfil(Perfil perfil) {
        perfis.add(perfil.getCod());
    }

    @PrePersist
    public void prePersist() {
        created = Instant.now();
    }

    @PreUpdate
    public void preUpdate() {
        modified = Instant.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getId().equals(user.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
