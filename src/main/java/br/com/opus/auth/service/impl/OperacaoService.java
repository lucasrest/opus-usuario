package br.com.opus.auth.service.impl;

import br.com.opus.auth.enums.Status;
import br.com.opus.auth.exception.EntidadeNaoEncontradaException;
import br.com.opus.auth.model.Operacao;
import br.com.opus.auth.model.dto.OperacaoDTO;
import br.com.opus.auth.repository.OperacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static br.com.opus.auth.model.constants.EXCEPTION.OPERACAO_NAO_ENCONTRADA;

@Service
public class OperacaoService extends BaseServiceImpl<Operacao, OperacaoDTO> {

    @Autowired
    private OperacaoRepository repository;

    @Override
    public Page<Operacao> buscarTodos(Pageable pageable) {
        return repository.findAllByStatus(pageable, Status.ATIVO.getValue());
    }

    @Override
    public Operacao buscarPorId(Long id) {
        Optional<Operacao> operacaoOpt = repository.findByIdAndStatus(id, Status.ATIVO.getValue());
        if (!operacaoOpt.isPresent())
            throw new EntidadeNaoEncontradaException(OPERACAO_NAO_ENCONTRADA);
        return operacaoOpt.get();
    }

    @Override
    public Operacao incluir(Operacao entidade) {
        return repository.save(entidade);
    }

    @Override
    public Operacao atualizar(Operacao entidade) {
        Operacao operacaoDb = buscarPorId(entidade.getId());
        mapper.map(entidade, operacaoDb);
        return repository.save(operacaoDb);
    }

    @Override
    public void excluir(Long id) {
        Operacao operacao = buscarPorId(id);
        operacao.setStatus(Status.DESATIVADO.getValue());
        repository.save(operacao);
    }
}
