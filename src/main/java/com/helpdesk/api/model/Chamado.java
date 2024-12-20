package com.helpdesk.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.helpdesk.api.model.enums.EstadoChamado;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import java.time.LocalDateTime;

@Entity
@Table(name = "tb_chamado")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Chamado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @UpdateTimestamp
    @Column(name = "data_chamado", nullable = false)
    private LocalDateTime dataChamado;
    @UpdateTimestamp
    @Column(name = "data_resolucao", nullable = false)
    private LocalDateTime dataResolucao;

    @NotBlank
    @Column(name = "motivo_chamado")
    private String motivoChamado;

    @Enumerated(EnumType.STRING)
    @Column(name = "estadoChamado")
    private EstadoChamado estadoChamado;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "balcao_id", nullable = false)
    @JsonIgnoreProperties("chamados")
    private Balcao balcao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_customerId", nullable = false)
    @JsonIgnoreProperties("chamados")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipamento_deviceId", nullable = false)
    @JsonIgnoreProperties("chamados")
    private Equipamento equipamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "horarios_disponiveis_id", nullable = false)
    @JsonIgnoreProperties("chamados")
    private HorariosDisponiveis horariosDisponiveis;
}
