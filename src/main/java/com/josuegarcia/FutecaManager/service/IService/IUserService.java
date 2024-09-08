package com.josuegarcia.FutecaManager.service.IService;

import java.util.List;

import com.josuegarcia.FutecaManager.model.User;

//Contrato para implementar la interfaz / intermediario entre hibernate 
public interface IUserService {
    List<User> listUsers();

    User getUser(Long id);

    User register(User user);

    boolean login(String username, String password);
    
}
