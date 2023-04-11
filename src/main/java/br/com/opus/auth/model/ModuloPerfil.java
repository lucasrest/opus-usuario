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
@Table(name = "tb_modulo_perfil", schema = "opus")
@SequenceGenerator(sequenceName = "opus.seq_tb_modulo_perfil", name = "id", schema = "opus", allocationSize = 1)
public class ModuloPerfil extends EntidadeBase {

    @ManyToOne
    @JoinColumn(name = "id_modulo", referencedColumnName = "id")
    private Modulo modulo;

    @ManyToOne
    @JsonBackReference("perfil-modulos")
    @JoinColumn(name = "id_perfil", referencedColumnName = "id")
    private Perfil perfil;
}
