package com.josuegarcia.futecaManager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.josuegarcia.futecaManager.model.User;
import com.josuegarcia.futecaManager.repository.UserRepository;
import com.josuegarcia.futecaManager.service.IService.IUserService;
import com.josuegarcia.futecaManager.utils.BCryptSecurity;

@Service
//Lógica de negocio
public class UserService implements IUserService {

    //Inyección de dependencia (Repositorio)
    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptSecurity bCryptSecurity;

    @Override
    public List<User> listUsers(){
        return userRepository.findAll();
    }
    
    @Override
    public User getUser(Long id){
        return userRepository.findById(id).orElse(null);
    }

    //Método con la lógica para registrar un usuario
    @Override
    public User register(User user){
        //Más lógica
        if(user.getPassword() != null){
            user.setPassword(bCryptSecurity.encodePassword(user.getPassword()));
        }
        return userRepository.save(user);
    }

    @Override
    public boolean login(String username, String password){
        //Más lógica
        User user = userRepository.findByUsername(username);
        //Validar que exista el usuario con ese username
        if(user == null || !bCryptSecurity.checkPassword(password, user.getPassword())){
            return false;
        }
        //Validar que ese usuario tenga la contraseña que envió el usuario.
        return true;
    }
}
