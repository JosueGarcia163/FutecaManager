package com.josuegarcia.futecaManager.service.IService;

import java.util.List;

import com.josuegarcia.futecaManager.model.User;

//Contrato para implementar la interfaz | Intermediario entre los datos y la lógica
public interface IUserService {
    List<User> listUsers();

    User getUser(Long id);

    User register(User user);

    boolean login(String username, String password);
}
