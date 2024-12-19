package com.helpdesk.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_balcao")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Balcao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "atendente_id", nullable = false)
    private AtendenteBalcao atendente;

    @OneToMany(mappedBy = "balcao", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("balcao")
    private List<Chamado> chamados = new ArrayList<>();
}
