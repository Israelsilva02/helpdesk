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
@Table(name="tb_balcao")
public class Balcao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeAtendente;

    @OneToMany(mappedBy = "balcao")
    private List<Chamado> chamados = new ArrayList<>();


}