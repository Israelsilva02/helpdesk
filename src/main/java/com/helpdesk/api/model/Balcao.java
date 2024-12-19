package com.helpdesk.api.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_balcao")
public class Balcao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "atendente_id")
    private AtendenteBalcao atendente;
    private static List<Chamado> chamados = new ArrayList<>();

    public void addChamado(Chamado chamado) {
        chamados.add(chamado);
        chamado.setBalcao(this);

    }

    public void setAtendente(AtendenteBalcao atendente) {
        this.atendente = atendente;
        if (atendente != null) {
            atendente.addBalcao(this, atendente);
        }
    }

    public void removeAtendente(AtendenteBalcao atendenteBalcao) {
        this.atendente = atendenteBalcao;
        if (atendenteBalcao != null) {
            atendenteBalcao.removeAtendente(this);
        }
    }
}
