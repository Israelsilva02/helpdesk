package com.helpdesk.api.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "tb_atendente_balcao")
public class AtendenteBalcao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private static List<Balcao> balcoes = new ArrayList<>();

    public void addBalcao(Balcao balcao, AtendenteBalcao atendenteBalcao) {
        if (atendenteBalcao != null) {
            balcoes.add(balcao);
            balcao.setAtendente(this);
        }
    }

    public void removeAtendente(Balcao balcao) {
        balcoes.remove(balcao);
        balcao.setAtendente(null);

    }

    public void addAtendente(AtendenteBalcao atendenteBalcao) {

    }
}
