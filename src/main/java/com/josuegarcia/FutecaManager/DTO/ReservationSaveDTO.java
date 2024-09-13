package com.josuegarcia.futecaManager.DTO;

import java.time.LocalDateTime;

import com.josuegarcia.futecaManager.utils.Status;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


/*La clase ReservationSaveDTO es un Data Transfer Object (DTO), 
utilizada para encapsular los datos que el cliente (frontend o alguna otra parte del sistema) envía al servidor 
al realizar una reserva en el sistema. 
La clase está diseñada para facilitar la creación de nuevas reservas con información validada. */
@Data
public class ReservationSaveDTO {

    //Parametros que van a ver el front 
    @NotNull(message = "La fecha de inicio no puede ir vacía")
    @FutureOrPresent
    private LocalDateTime start;
    @NotNull(message = "La fecha de finalización no puede ir vacía")
    @FutureOrPresent
    private LocalDateTime end;
    private String payment;
    @Enumerated(EnumType.STRING)
    private Status status;
    @NotNull(message = "No hay un usuario para reservar")
    private Long userId;
    @NotNull(message = "No se seleccionó una cancha a reservar")
    private Long soccerFieldId;
}
