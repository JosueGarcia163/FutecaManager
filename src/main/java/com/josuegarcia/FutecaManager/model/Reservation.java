package com.josuegarcia.FutecaManager.model;

import java.sql.Date;

import com.josuegarcia.FutecaManager.utils.Status;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @FutureOrPresent
    private Date start; // UTC tiempo universal central. -> GMT-6 gracias a nuestras propiedades
    @NotBlank
    @FutureOrPresent
    private Date end;
    //Se almacena comprobante de pago
    private String payment;
    @Enumerated(EnumType.STRING)
    private Status status;
    @NotBlank
    @ManyToOne // por defecto tiene un Eager osea una poblacion de datos(fetch = FetchType.EAGER)
    private User user;
    @NotBlank
    @ManyToOne
    private SoccerField soccerField;

}
