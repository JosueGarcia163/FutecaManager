package com.josuegarcia.futecaManager.DTO;

import java.sql.Timestamp;

import com.josuegarcia.futecaManager.model.SoccerField;
import com.josuegarcia.futecaManager.utils.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationResponseDTO {
    private Long id;
    private Timestamp start;
    private Timestamp end;
    private String payment;
    private Status status;
    private UserClearDTO user;
    private SoccerField soccerFieldId;
}
