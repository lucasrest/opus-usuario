package br.com.opus.auth.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_operacao_modulo", schema = "opus")
@SequenceGenerator(sequenceName = "opus.seq_operacao_modulo", name = "id", schema = "opus", allocationSize = 1)
public class OperacaoModulo extends EntidadeBase {

    @ManyToOne
    @JoinColumn(name = "id_operacao", referencedColumnName = "id")
    private Operacao operacao;

    @ManyToOne
    @JsonBackReference("modulo-operacoes")
    @JoinColumn(name = "id_modulo", referencedColumnName = "id")
    private Modulo modulo;
}
