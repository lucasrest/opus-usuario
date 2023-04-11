package br.com.opus.auth.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_usuario", schema = "opus")
@SequenceGenerator(sequenceName = "opus.seq_tb_usuario", name = "id", schema = "opus", allocationSize = 1)
public class Usuario extends EntidadeBase {


    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String nome;

    @NotEmpty
    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String senha;

    @JsonBackReference("perfis")
    @OneToMany(mappedBy = "usuario", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<PerfilUsuario> perfis = new ArrayList<>();

    public List<String> getPerfisUsuario() {
        return perfis.stream().map(perfUsuario -> perfUsuario.getPerfil().getDescricao()).collect(Collectors.toList());
    }

    public List<String> getModulos() {
        List<String> modulos = new ArrayList<>();
        perfis.forEach(perfilUsuario ->
                modulos.addAll(perfilUsuario.getPerfil().getModulos().stream().map(
                        moduloPerfil -> moduloPerfil.getModulo().getCodigo()
                ).collect(Collectors.toList())));
        return modulos;
    }
}