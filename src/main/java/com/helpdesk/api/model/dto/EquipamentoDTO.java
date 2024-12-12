package com.helpdesk.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EquipamentoDTO {
    private Long deviceId;
    private String serialNumber;
}
