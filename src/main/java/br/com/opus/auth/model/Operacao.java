package br.com.opus.auth.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_operacao", schema = "opus")
@SequenceGenerator(sequenceName = "opus.seq_tb_operacao", name = "id", schema = "opus", allocationSize = 1)
public class Operacao extends EntidadeBase {

    @NotEmpty
    @Column(nullable = false, unique = true)
    private String codigo;

    @NotEmpty
    @Column(nullable = false)
    private String descricao;

}
