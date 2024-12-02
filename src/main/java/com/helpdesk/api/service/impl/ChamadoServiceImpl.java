package com.helpdesk.api.service.impl;

import com.helpdesk.api.exception.ChamadoException;
import com.helpdesk.api.model.Chamado;
import com.helpdesk.api.model.EstadoChamado;
import com.helpdesk.api.repositorio.BalcaoRepository;
import com.helpdesk.api.repositorio.ChamadoRepository;
import com.helpdesk.api.service.ChamadoService;
import com.helpdesk.api.util.MessageConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ChamadoServiceImpl implements ChamadoService {

    private final ChamadoRepository chamadoRepository;
    private final BalcaoRepository balcaoRepository;

    @Autowired
    public ChamadoServiceImpl(ChamadoRepository chamadoRepository, BalcaoRepository balcaoRepository) {
        this.chamadoRepository = chamadoRepository;
        this.balcaoRepository = balcaoRepository;
    }

    @Override
    @Transactional
    public Chamado createChamado(Chamado chamado) throws ChamadoException {
        try {
            List<Chamado> chamadosConflitantes = chamadoRepository.findByCustomerIdAndSerialNumberAndEstadoChamadoNot(
                    chamado.getCustomerId(), chamado.getSerialNumber(), EstadoChamado.CONCLUIDO);

            if (!chamadosConflitantes.isEmpty()) {
                throw new ChamadoException(MessageConstants.UM_CHAMADO_JA_ESTA_ABERTO_PARA_ESTE_DISPOSIVO);
            }

            if (chamado.getBalcao() == null) {
                throw new IllegalArgumentException(MessageConstants.BALCAO_NAO_PODE_SER_NULO);
            }

            List<Chamado> chamadosDoBalcao = chamadoRepository.findByBalcaoAndEstadoChamadoNot(
                    chamado.getBalcao(), EstadoChamado.CONCLUIDO);

            if (chamadosDoBalcao.size() >= 5) {
                chamado.setEstadoChamado(EstadoChamado.EM_ESPERA);
            } else {
                chamado.setEstadoChamado(EstadoChamado.ABERTO);
            }

            chamado.setDataChamado(LocalDate.now());
            return chamadoRepository.save(chamado);
        } catch (Exception e) {
            throw new ChamadoException(MessageConstants.OCORREU_UM_ERRO_AO_CRIAR_O_CHAMADO, e);
        }
    }

    @Override
    public Page<Chamado> getChamadosByCustomerId(Long customerId, Pageable pageable) throws ChamadoException {
        try {
            return chamadoRepository.findAllByCustomerId(customerId, pageable);
        } catch (Exception e) {
            throw new ChamadoException(MessageConstants.OCORREU_UM_ERRO_AO_OBTER_CHAMADOS_POR_CUSTOMER_ID + customerId, e);
        }
    }

    @Override
    public List<Chamado> getChamadosPorEstado(EstadoChamado estado) throws ChamadoException {
        try {
            return chamadoRepository.findByEstadoChamado(estado);
        } catch (Exception e) {
            throw new ChamadoException(MessageConstants.OCORREU_UM_ERRO_AO_OBTER_CHAMADOS_POR_ESTADO + estado, e);
        }
    }

    @Override
    public Optional<Chamado> getChamadoById(Long id) throws ChamadoException {
        try {
            Optional<Chamado> optionalChamado = chamadoRepository.findById(id);

            if (optionalChamado.isPresent()) {
                return optionalChamado;
            } else {
                throw new ChamadoException(MessageConstants.CHAMADO_NAO_ENCONTRADO_C0M_ID + id);
            }
        } catch (Exception e) {
            throw new ChamadoException(MessageConstants.OCORREU_UM_ERRO_AO_OBTER_O_CHAMADO_C0M_ID + id, e);
        }
    }

    @Override
    @Transactional
    public Chamado updateEstadoChamado(Long id, EstadoChamado novoEstado) throws ChamadoException {
        try {
            Optional<Chamado> optionalChamado = chamadoRepository.findById(id);

            if (optionalChamado.isPresent()) {
                Chamado chamadoExistente = optionalChamado.get();

                chamadoExistente.setEstadoChamado(novoEstado);

                return chamadoRepository.save(chamadoExistente);
            } else {
                throw new ChamadoException(MessageConstants.CHAMADO_NAO_ENCONTRADO_C0M_ID + id);
            }
        } catch (Exception e) {
            throw new ChamadoException(MessageConstants.OCORREU_UM_ERRO_AO_ATUALIZAR_O_ESTADO_DO_CHAMADO_C0M_ID + id, e);
        }
    }
}