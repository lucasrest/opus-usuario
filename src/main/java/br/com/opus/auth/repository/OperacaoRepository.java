package br.com.opus.auth.repository;

import br.com.opus.auth.model.Operacao;
import br.com.opus.auth.model.Operacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OperacaoRepository extends JpaRepository<Operacao, Long> {

    Optional<Operacao> findByIdAndStatus(Long id, Integer status);

    Page<Operacao> findAllByStatus(Pageable pageable, Integer status);
}
