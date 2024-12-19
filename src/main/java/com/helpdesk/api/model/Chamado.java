package com.helpdesk.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.helpdesk.api.enums.EstadoChamado;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "tb_chamado")
public class Chamado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @UpdateTimestamp
    private LocalDateTime dataChamado;

    @UpdateTimestamp
    private LocalDateTime dataResolucao;

    private String motivoChamado;

    @Enumerated(EnumType.STRING)
    @Column(name = "estadoChamado")
    private EstadoChamado estadoChamado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "balcao_id")
    @JsonIgnoreProperties("chamado")
    private Balcao balcao;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @JsonIgnoreProperties("chamado")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipamento_id")
    @JsonIgnoreProperties("chamado")
    private Equipamento equipamento;

    public void setBalcao(Balcao balcao) {
        this.balcao = balcao;
        if (balcao != null) balcao.addChamado(this);
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
        if (usuario != null) usuario.addChamado(this);
    }
    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
        if (equipamento != null) equipamento.addChamado(this);
    }

}
