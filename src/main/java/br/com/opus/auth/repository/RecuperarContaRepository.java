package br.com.opus.auth.repository;

import br.com.opus.auth.model.RecuperarConta;
import br.com.opus.auth.model.RecuperarConta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecuperarContaRepository extends JpaRepository<RecuperarConta, Long> {

    List<RecuperarConta> findByUsuario_id(Long usuario);

    Optional<RecuperarConta> findByCodigoRecuperacaoAndUsuario_IdEquals(String codigoRecuperacao, Long idUsuario);
}
