package com.josuegarcia.futecaManager.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.josuegarcia.futecaManager.DTO.ReservationResponseDTO;
import com.josuegarcia.futecaManager.DTO.ReservationSaveDTO;
import com.josuegarcia.futecaManager.DTO.UserClearDTO;
import com.josuegarcia.futecaManager.model.Reservation;
import com.josuegarcia.futecaManager.model.SoccerField;
import com.josuegarcia.futecaManager.model.User;
import com.josuegarcia.futecaManager.repository.ReservationRepository;
import com.josuegarcia.futecaManager.service.IService.IReservationService;

@Service
public class ReservationService implements IReservationService {

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    UserService userService;

    @Autowired
    SoccerFieldService soccerFieldService;

    @Override
    public List<ReservationResponseDTO> myReservations(Long userId) {
        //Mandamos a buscar a los usuarios que tengan el id usuario en especifico.
        User user = userService.getUser(userId);
        List<Reservation> reservations = reservationRepository.findByUser(user);
        //Llamamos las reservaciones con los filtros para que liste lo necesario.(responseDTO)
        return reservations
                .stream()
                .map(reservation -> responseDTO(reservation))
                .collect(Collectors.toList());
    }

    @Override
    public Reservation findByIdReservation(Long id) {
        return reservationRepository.findById(id).orElse(null);
    }

    @Override
    public Reservation save(ReservationSaveDTO reservationDTO) {
        try {
            //Convertir la fecha que llega en STRING (LocalDateTime) a TIMESTAMP
            Timestamp startDate = Timestamp.valueOf(reservationDTO.getStart());
            //Convertir la fecha que llega en STRING (LocalDateTime) a TIMESTAMP
            Timestamp endDate = Timestamp.valueOf(reservationDTO.getEnd());
            //Obtenemos el usuario completo que viene en la solicitud (userId)
            User user = userService.getUser(reservationDTO.getUserId());
            //Obtenemos la cancha completa que viene en la solicitud (soccerFieldId)
            SoccerField soccerField = soccerFieldService.findFieldById(reservationDTO.getSoccerFieldId());
            //Despues de convertirlo lo pasa a reservacion.
            Reservation reservation = new Reservation(
                    null,
                    startDate,
                    endDate,
                    reservationDTO.getPayment(),//no hubo necesidad de parsearlo ni de filtrarlo
                    reservationDTO.getStatus(),//no hubo necesidad de parsearlo ni de filtrarlo
                    user,// el user que se filtro para obtener el user con ese numero de reservacion.
                    soccerField//la cancha que se filtro para obtener la cancha con ese numero de reservacion.
            );

            //Se guarda en el repositorio la reservacion que acabamos de crear.
            return reservationRepository.save(reservation);
        } catch (Exception err) {
            throw new IllegalArgumentException("Error al parsear las fechas", err);
        }
    }

    
    /*Metodo que sirve para listar solamente los datos de las reservas y los datos especificos del usuario.
    para que se mande a llamar en la funcion de listar. */
    private ReservationResponseDTO responseDTO(Reservation reservation) {
        User user = reservation.getUser();
        
        UserClearDTO userDTO = new UserClearDTO(
                user.getName(),
                user.getSurname(),
                user.getSurname()
        );

        ReservationResponseDTO dto = new ReservationResponseDTO(
                reservation.getId(),
                reservation.getStart(),
                reservation.getEnd(),
                reservation.getPayment(),
                reservation.getStatus(),
                userDTO,
                reservation.getSoccerField()
        );

        return dto;
    }

}
