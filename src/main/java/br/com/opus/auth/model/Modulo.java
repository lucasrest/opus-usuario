package br.com.opus.auth.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_modulo", schema = "opus")
@SequenceGenerator(sequenceName = "opus.seq_tb_modulo", name = "id", schema = "opus", allocationSize = 1)
public class Modulo extends EntidadeBase {

    @NotEmpty
    @Column(nullable = false, unique = true)
    private String codigo;

    @NotEmpty
    @Column
    private String descricao;

    @JsonManagedReference("modulo-operacoes")
    @OneToMany(mappedBy = "modulo", cascade = {CascadeType.ALL}, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<OperacaoModulo> operacoes = new ArrayList<>();

}
