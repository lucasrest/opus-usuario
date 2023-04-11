package br.com.opus.auth.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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
@Table(name = "tb_perfil", schema = "opus")
@SequenceGenerator(sequenceName = "opus.seq_tb_perfil", name = "id", schema = "opus", allocationSize = 1)
public class Perfil extends EntidadeBase {

    @NotEmpty
    @Column(nullable = false, unique = true)
    private String codigo;

    @NotEmpty
    @Column(nullable = false, unique = true)
    private String descricao;

    @Fetch(FetchMode.SUBSELECT)
    @JsonManagedReference("perfil-modulos")
    @OneToMany(mappedBy = "perfil", cascade = {CascadeType.ALL}, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<ModuloPerfil> modulos = new ArrayList<>();

    @Fetch(FetchMode.SUBSELECT)
    @JsonManagedReference("perfil-usuarios")
    @OneToMany(mappedBy = "perfil", cascade = {CascadeType.ALL}, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<PerfilUsuario> perfilUsuarios = new ArrayList<>();

}
