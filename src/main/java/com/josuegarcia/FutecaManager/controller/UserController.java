package com.josuegarcia.FutecaManager.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.josuegarcia.FutecaManager.DTO.UserLoginDTO;
import com.josuegarcia.FutecaManager.DTO.UserRegisterDTO;
import com.josuegarcia.FutecaManager.model.User;
import com.josuegarcia.FutecaManager.service.CloudinaryService;
import com.josuegarcia.FutecaManager.service.UserService;

import jakarta.validation.Valid;


@RestController // Implementa @Controller @ResponseBody
@RequestMapping("/futecaManager/v1/auth") // Ruta general
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    CloudinaryService cloudinaryService;

    

    // Rutas especificas

    /*
     * Metodo para listar usuarios
     * @return ResponseEntity con las diferentes 
     */
    @GetMapping()
    public ResponseEntity<?> getMethodUser() {
        Map<String, Object> res = new HashMap<>();

        // Inyeccion de dependencia del servicio
        try {

            return ResponseEntity.ok().body(userService.listUsers());

        } catch (CannotCreateTransactionException err) {
            res.put("Message", "Error al momento de conectarse a la db");
            res.put("Error", err.getMessage().concat(err.getMostSpecificCause().getMessage()));
            return ResponseEntity.status(503).body(res);

        } catch (DataAccessException err) {
            res.put("Message", "Error al momento de consultar a la db");
            res.put("Error", err.getMessage().concat(err.getMostSpecificCause().getMessage()));
            return ResponseEntity.status(503).body(res);

        } catch (Exception err) {
            res.put("Message", "Error general al obtener datos.");
            res.put("Error", err.getMessage());
            return ResponseEntity.internalServerError().body(res);

        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getMethodUserId(@PathVariable long id) {
        Map<String, Object> res = new HashMap<>();

        // Inyeccion de dependencia del servicio
        try {
            User user = userService.getUser(id);
            return ResponseEntity.ok().body(user);

        } catch (CannotCreateTransactionException err) {
            res.put("Message", "Error al momento de conectarse a la db");
            res.put("Error", err.getMessage().concat(err.getMostSpecificCause().getMessage()));
            return ResponseEntity.status(503).body(res);

        } catch (DataAccessException err) {
            res.put("Message", "Error al momento de consultar a la db");
            res.put("Error", err.getMessage().concat(err.getMostSpecificCause().getMessage()));
            return ResponseEntity.status(503).body(res);

        } catch (Exception err) {
            res.put("Message", "Error general al obtener datos.");
            res.put("Error", err.getMessage());
            return ResponseEntity.internalServerError().body(res);

        }
    }




      /*  @PostMapping()
    public ResponseEntity<Map<String, String>> postMethodUser(@RequestBody User user) {
        Map<String, String> res = new HashMap<>();
        try {
            userService.registrer(user);
            // responseEntity = 200, 204,301,303,400,401,403,404,500,504
            res.put("message", "Todo Guardado exitosamente");
            return ResponseEntity.ok(res);
        } catch (CannotCreateTransactionException err) {
            res.put("Message", "Error al momento de conectarse a la db");
            res.put("Error", err.getMessage().concat(err.getMostSpecificCause().getMessage()));
            return ResponseEntity.status(503).body(res);

        } catch (DataAccessException err) {
            res.put("Message", "Error al momento de consultar a la db");
            res.put("Error", err.getMessage().concat(err.getMostSpecificCause().getMessage()));
            return ResponseEntity.status(503).body(res);

        } catch (Exception err) {
            res.put("Message", "Error general al obtener datos.");
            res.put("Error", err.getMessage());
            return ResponseEntity.internalServerError().body(res);

        }
    }*/
     

 /*
     * Metodo para postear.
     * Data transfer object / patron de diseno que sirve para transportar datos entre diferentes capas.
     */

   
    @PostMapping("/register")
    public ResponseEntity<?> registrer(@RequestPart("profilePicture") MultipartFile profilePicture,
            // Multipart-formadata
            //@Valid ejecuta todas las validaciones del modelo DTO
            //RequestBody para el JSON / ModelAttribute para archivos tambien.

              @Valid @ModelAttribute UserRegisterDTO user,
            //BindingResult captura los errores si en tal caso no pasa las validaciones.
            BindingResult result
            ) {

               
        Map<String, Object> res = new HashMap<>();
                if(result.hasErrors()){
                    List<String> errors = result.getFieldErrors().
                    stream().map(error -> error.getDefaultMessage()).
                    collect(Collectors.toList());
                    res.put("message","Error en las validaciones porfavor ingresa en todos los campos.");
                    res.put("Errors", errors);
                    return ResponseEntity.badRequest().body(res);
                }

        try {
            Map<String, Object> uploadResult = cloudinaryService.uploadProfilePicture(profilePicture
            , "profilesFuteca");

            String urlProfilePicture = uploadResult.get("url").toString();
            String img = uploadResult.get("url").toString();
            Long id = null;
            User newUser = new User(id, user.getName(),user.getSurname(),user.getUsername(),user.getEmail(),
            user.getPassword(),img);
            //Utiliza servicios de cloudinary para subir la imagen que manda el usuario.
            userService.register(newUser);
            res.put("message", "usuario recibido correctamente.");
            return ResponseEntity.ok(res);

        } catch (Exception err) {
            res.put("Message", "Error general al obtener datos.");
            res.put("Error", err.getMessage());
            return ResponseEntity.internalServerError().body(res);
            
        }
    }

    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDTO user) {
        Map<String, Object> res =  new HashMap<>();
        try{

            if(userService.login(user.getUsername(), user.getPassword())){
            res.put("Message", "Usuario Logeado satisfactoriamente");
            return ResponseEntity.ok(res);
            
                

            }else{
                res.put("Message", "Credenciales invalidas");
                return ResponseEntity.status(401).body(res);


            }
        }catch(Exception err){
                res.put("Messagge", "Error general al iniciar sesion");
                res.put("error",err);
                return ResponseEntity.internalServerError().body(res);
                


        }
    }
    
}
