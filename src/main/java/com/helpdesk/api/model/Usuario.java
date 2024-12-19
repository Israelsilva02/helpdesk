package com.helpdesk.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tb_usuario")
public class Usuario  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    private String nome;
    private String email;

    private static List<Chamado> chamados = new ArrayList<>();

    public void addChamado(Chamado chamado) {
        chamados.add(chamado);
        chamado.setUsuario(this);
    }

    public void removeChamado(Chamado chamado) {
        chamados.remove(chamado);
        chamado.setUsuario(null);
    }
}
