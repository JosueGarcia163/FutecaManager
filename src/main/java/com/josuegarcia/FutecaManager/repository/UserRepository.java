package com.josuegarcia.FutecaManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.josuegarcia.FutecaManager.model.User;
import java.util.List;


//Extender las entidades y el tipo de dato de PK
//Evitar el uso de querys en el codigo
public interface UserRepository extends JpaRepository<User, Long>{
    public User findByUsername(String username);
    
}
