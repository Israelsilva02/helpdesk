package com.helpdesk.api.model.dto;

import java.time.LocalDateTime;

public record HorariosDisponiveisDTO(Long idAtendente,LocalDateTime horariosDisponiveis,boolean status) {


}
