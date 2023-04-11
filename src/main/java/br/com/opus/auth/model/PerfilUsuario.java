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
@Table(name = "tb_perfil_usuario", schema = "opus")
@SequenceGenerator(sequenceName = "opus.seq_tb_perfil_usuario", name = "id", schema = "opus", allocationSize = 1)
public class PerfilUsuario extends EntidadeBase {

    @ManyToOne
    @JsonBackReference("perfil-usuarios")
    @JoinColumn(name = "id_perfil", referencedColumnName = "id")
    private Perfil perfil;

    @ManyToOne
    @JsonBackReference("perfis")
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    private Usuario usuario;
}
