package br.com.opus.auth.repository;

import br.com.opus.auth.model.Operacao;
import br.com.opus.auth.model.Operacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuloRepository extends JpaRepository<Operacao, Long> {
}
