package com.helpdesk.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_equipamento")
public class Equipamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deviceId;
    private String serialNumber;
    private static List<Chamado> chamados = new ArrayList<>();

    public void addChamado(Chamado chamado) {
        chamados.add(chamado);
        chamado.setEquipamento(this);
    }

    public void removeChamado(Chamado chamado) {
        chamados.remove(chamado);
        chamado.setEquipamento(null);
    }

}
