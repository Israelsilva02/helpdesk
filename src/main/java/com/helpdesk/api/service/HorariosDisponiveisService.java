package com.helpdesk.api.service;

import com.helpdesk.api.model.HorariosDisponiveis;

import java.util.List;

public interface HorariosDisponiveisService {
    List<HorariosDisponiveis> findAll();
    HorariosDisponiveis createHorario(HorariosDisponiveis horariosDisponiveis);
}
