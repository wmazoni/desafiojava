package com.wmazoni.desafiojava.repositories;

import com.wmazoni.desafiojava.entities.Telephone;
import com.wmazoni.desafiojava.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelephoneRepository extends JpaRepository<Telephone, Long> {

}
