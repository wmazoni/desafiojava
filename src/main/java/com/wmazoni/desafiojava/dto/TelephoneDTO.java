package com.wmazoni.desafiojava.dto;

import com.wmazoni.desafiojava.entities.Telephone;

import java.io.Serializable;
import java.util.UUID;

public class TelephoneDTO implements Serializable {
    private static final long serialVersionUID = 1L;


    private Long id;
    private String number;
    private String ddd;

    public TelephoneDTO() {
    }

    public TelephoneDTO(Long id, String number, String ddd) {
        this.id = id;
        this.number = number;
        this.ddd = ddd;
    }
    public TelephoneDTO(Telephone entity) {
        this.id = entity.getId();
        this.number = entity.getNumber();
        this.ddd = entity.getDdd();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }
}
