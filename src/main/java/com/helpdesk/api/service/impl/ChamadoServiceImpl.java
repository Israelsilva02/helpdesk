package com.helpdesk.api.service.impl;


import com.helpdesk.api.exception.ChamadoException;
import com.helpdesk.api.model.*;
import com.helpdesk.api.repository.ChamadoRepository;
import com.helpdesk.api.service.ChamadoService;
import com.helpdesk.api.util.MessageConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ChamadoServiceImpl implements ChamadoService {

    private final ChamadoRepository chamadoRepository;

    @Override
    public Chamado createChamado(Chamado chamado) {
        return chamadoRepository.save(chamado);
    }

    @Override
    public List<Chamado> getAllChamados() {
        return chamadoRepository.findAll();
    }

    @Override
    public List<Chamado> getChamadosPorEstado(EstadoChamado estado) {
        return chamadoRepository.findByEstadoChamado(estado);
    }

    @Override
    public Optional<Chamado> getChamadoById(Long id) {
        return chamadoRepository.findById(id);
    }

    @Override
    public Optional<Chamado> updateChamado(final Long id, final Chamado chamadoAtualizado) {
        Balcao balcao = Balcao.builder().id(chamadoAtualizado.getBalcao().getId()).build();
        Usuario usuario = Usuario.builder().customerId(chamadoAtualizado.getUsuario().getCustomerId()).build();
        Equipamento equipamento = Equipamento.builder().deviceId(chamadoAtualizado.getEquipamento().getDeviceId()).build();
        Optional<Chamado> chamado = chamadoRepository.findById(id);
        if (chamado.isPresent()) {
            Chamado chamadoEncontrado = chamado.get();
            chamadoEncontrado.setUsuario(usuario);
            chamadoEncontrado.setEquipamento(equipamento);
            chamadoEncontrado.setBalcao(balcao);
            chamadoEncontrado.setMotivoChamado(chamadoAtualizado.getMotivoChamado());
            chamadoEncontrado.setEstadoChamado(chamadoAtualizado.getEstadoChamado());
            chamadoEncontrado.setDataChamado(chamadoAtualizado.getDataChamado());
            chamadoEncontrado.setDataResolucao(chamadoAtualizado.getDataResolucao());

            return Optional.of(chamadoRepository.save(chamadoEncontrado));
        }
        return chamado;
    }

    @Override
    public void deleteChamado(Long id) {
        Optional<Chamado> chamado = chamadoRepository.findById(id);
        if (chamado.isEmpty()) {
            throw new ChamadoException(MessageConstants.CHAMADO_NAO_ENCONTRADO_C0M_ID + id);
        }
        chamadoRepository.deleteById(id);
    }
}
