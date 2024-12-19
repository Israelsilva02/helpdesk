package com.helpdesk.api.service;

import com.helpdesk.api.exception.ChamadoException;
import com.helpdesk.api.mapper.ChamadoMapper;
import com.helpdesk.api.model.Chamado;
import com.helpdesk.api.model.enums.EstadoChamado;
import com.helpdesk.api.model.dto.ChamadoDTO;
import com.helpdesk.api.repository.ChamadoRepository;
import com.helpdesk.api.util.MessageConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChamadoServiceImpl {

    private final ChamadoRepository chamadoRepository;
    private final ChamadoMapper chamadoMapper;

    public ChamadoDTO createChamado(ChamadoDTO chamadoDTO) {
        Chamado chamado = chamadoMapper.chamadoDTOToChamado(chamadoDTO);
        Chamado savedChamado = chamadoRepository.save(chamado);
        return chamadoMapper.chamadoToChamadoDTO(savedChamado);
    }

    public List<ChamadoDTO> getAllChamados() {
        return chamadoRepository.findAll().stream()
                .map(chamadoMapper::chamadoToChamadoDTO)
                .collect(Collectors.toList());
    }

    public Optional<ChamadoDTO> getChamadoById(Long id) {
        return chamadoRepository.findById(id)
                .map(chamadoMapper::chamadoToChamadoDTO);
    }

    public List<ChamadoDTO> getChamadosPorEstado(EstadoChamado estado) {
        return chamadoRepository.findByEstadoChamado(estado).stream()
                .map(chamadoMapper::chamadoToChamadoDTO)
                .collect(Collectors.toList());
    }

    public Optional<ChamadoDTO> updateChamado(Long id, ChamadoDTO chamadoDTO) {
        return chamadoRepository.findById(id)
                .map(existingChamado -> {
                    Chamado updatedChamado = chamadoMapper.chamadoDTOToChamado(chamadoDTO);
                    updatedChamado.setId(existingChamado.getId());
                    Chamado savedChamado = chamadoRepository.save(updatedChamado);
                    return chamadoMapper.chamadoToChamadoDTO(savedChamado);
                });
    }

    public void deleteChamado(Long id) {
        if (chamadoRepository.existsById(id)) {
            chamadoRepository.deleteById(id);
        }
        throw new ChamadoException(MessageConstants.CHAMADO_NAO_ENCONTRADO_COM_ID + id);

    }
}
