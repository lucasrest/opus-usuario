package br.com.opus.auth.model.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OperacaoDTO extends EntidadeBaseDTO {

    @NotEmpty
    private String codigo;

    @NotNull
    @NotEmpty
    private String descricao;
}
