package br.com.opus.auth.model;

import br.com.opus.auth.anotation.DataAPI;
import br.com.opus.auth.anotation.DataAPI;
import br.com.opus.auth.anotation.DataAPI;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "tb_recuperar_conta", schema = "opus")
@SequenceGenerator(sequenceName = "opus.seq_tb_recuperar_conta", name = "id", schema = "opus", allocationSize = 1)
public class RecuperarConta extends EntidadeBase {

    @Column(name = "codigo_recuperacao", nullable = false, unique = true)
    private String codigoRecuperacao;

    @DataAPI
    @Column(nullable = false)
    private LocalDateTime expiracao;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;
}
