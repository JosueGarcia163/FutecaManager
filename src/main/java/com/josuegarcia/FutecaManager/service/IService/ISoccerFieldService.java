package com.josuegarcia.futecaManager.service.IService;

import java.util.List;

import com.josuegarcia.futecaManager.model.SoccerField;


public interface ISoccerFieldService {
    public List<SoccerField> listFields();

    public SoccerField findFieldById(Long id);

    public SoccerField save (SoccerField soccerFields);
}
