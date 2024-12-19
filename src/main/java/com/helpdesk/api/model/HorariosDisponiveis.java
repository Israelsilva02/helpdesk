package com.helpdesk.api.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "tb_horariosDisponiveis")
public class HorariosDisponiveis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "atendente_id")
    private AtendenteBalcao atendenteBalcao;

    @UpdateTimestamp
    private LocalDateTime horariosDisponiveis;

    private boolean status;

    private static List<AtendenteBalcao> atendenteBalcaos = new ArrayList<>();

    public void addAtendente(AtendenteBalcao atendenteBalcao) {
        atendenteBalcaos.add(atendenteBalcao);
        atendenteBalcao.setNome(atendenteBalcao.getNome());
    }

}
