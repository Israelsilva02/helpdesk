package com.helpdesk.api.service;

import com.helpdesk.api.model.Chamado;
import com.helpdesk.api.enums.EstadoChamado;
import com.helpdesk.api.model.dto.ChamadoDTO;
import com.helpdesk.api.mapper.ChamadoMapper;
import com.helpdesk.api.repository.ChamadoRepository;

import com.helpdesk.api.util.MessageConstants;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.helpdesk.api.mapper.ChamadoMapper.*;

@Service
@RequiredArgsConstructor
@Transactional
public class ChamadoServiceImpl {

    private final ChamadoRepository chamadoRepository;

    public ChamadoDTO createChamado(ChamadoDTO chamadoDTO) {
        Chamado chamado = toEntityChamado(chamadoDTO);
        Chamado savedChamado = chamadoRepository.save(chamado);
        return toDtoChamadoDto(savedChamado);
    }

    public List<ChamadoDTO> getAllChamados() {
        List<Chamado> chamados = chamadoRepository.findAll();
        return toDtoChamado(chamados);
    }

    public Optional<ChamadoDTO> getChamadoById(Long id) {
        return chamadoRepository.findById(id)
                .map(ChamadoMapper::toDtoChamadoDto);
    }

    public List<ChamadoDTO> getChamadosPorEstado(EstadoChamado estado) {
        List<Chamado> chamados = chamadoRepository.findByEstadoChamado(estado);
        return toDtoChamado(chamados);
    }

    public Optional<ChamadoDTO> updateChamado(Long id, ChamadoDTO chamadoDTO) {
        return chamadoRepository.findById(id)
                .map(existingChamado -> {
                    Chamado updatedChamado = toEntityChamado(chamadoDTO);
                    updatedChamado.setId(existingChamado.getId());
                    Chamado savedChamado = chamadoRepository.save(updatedChamado);
                    return toDtoChamadoDto(savedChamado);
                });
    }

    public void deleteChamado(Long id) {
        if (chamadoRepository.existsById(id)) {
            chamadoRepository.deleteById(id);
        } else {
            throw new RuntimeException(MessageConstants.CHAMADO_NAO_ENCONTRADO_COM_ID + id);
        }
    }
}
