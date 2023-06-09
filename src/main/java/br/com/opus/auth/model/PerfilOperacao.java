package br.com.opus.auth.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "tb_perfil_ope", schema = "opus")
@SequenceGenerator(sequenceName = "opus.seq_tb_perfil_ope", name = "id", schema = "opus", allocationSize = 1)
public class PerfilOperacao extends EntidadeBase {

    @ManyToOne
    @JsonBackReference("perfil-operacao")
    @JoinColumn(name = "id_perfil", referencedColumnName = "id")
    private Perfil perfil;

    @ManyToOne
    @JoinColumn(name = "id_operacao", referencedColumnName = "id")
    private Operacao operacao;
}
