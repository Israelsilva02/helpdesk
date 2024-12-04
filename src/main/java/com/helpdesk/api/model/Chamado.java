package com.helpdesk.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tb_chamado")
public class Chamado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long customerId;

    private String serialNumber;
    private LocalDateTime dataChamado;

    private LocalDateTime dataResolucao;

    private String motivoChamado;

    @Enumerated(EnumType.STRING)
    private EstadoChamado estadoChamado;

    @ManyToOne
    @JoinColumn(name = "balcao_id")
    private Balcao balcao;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;


}