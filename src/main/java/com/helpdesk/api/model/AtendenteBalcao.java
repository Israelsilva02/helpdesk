package com.helpdesk.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_atendente_balcao")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AtendenteBalcao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @OneToMany(mappedBy = "atendente", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Balcao> balcaoList = new ArrayList<>();
}
