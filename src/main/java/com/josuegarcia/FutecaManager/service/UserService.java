package com.josuegarcia.FutecaManager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.josuegarcia.FutecaManager.model.User;
import com.josuegarcia.FutecaManager.repository.UserRepository;
import com.josuegarcia.FutecaManager.service.IService.IUserService;
import com.josuegarcia.FutecaManager.utils.BCryptSecurity;

@Service
public class UserService implements IUserService{

    @Autowired
    UserRepository userRepository;

    @Autowired 
    BCryptSecurity bCryptSecurity;

    @Override
    public List<User> listUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User register(User user) {
        //Mas logica 
        //Encriptar password 
        if(user.getPassword() != null){
                user.setPassword(bCryptSecurity.encodePassword(user.getPassword()));

        }
        return userRepository.save(user);
    }

    @Override
    public boolean login(String username, String password) {
        // Mas logica
        //Validar que existe el usuario con ese Username
        //Validar que ese usuario tenga la contraseña que envio el usuario.
        User user = userRepository.findByUsername(username);

        if(user == null || !bCryptSecurity.checkPassword(password, user.getPassword())){
            return false;

        }
        return true;
    }

}
