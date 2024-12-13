package com.helpdesk.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @ManyToOne
    @JoinColumn(name = "atendente_id")
    private AtendenteBalcao atendente;

    @OneToMany(mappedBy = "balcao")
    @JsonIgnoreProperties("balcao")
    private List<Chamado> chamados = new ArrayList<>();


}
