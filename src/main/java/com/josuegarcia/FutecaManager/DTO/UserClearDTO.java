package com.josuegarcia.futecaManager.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserClearDTO {
    private String name;
    private String surname;
    private String username;
}
