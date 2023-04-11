package br.com.opus.auth.model.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class PerfilDTO extends EntidadeBaseDTO {

    private String codigo;
    private String descricao;
    @JsonManagedReference(value = "perfil-operacoes")
    private List<PerfilOperacaoDTO> perfilOperacoes;
}
