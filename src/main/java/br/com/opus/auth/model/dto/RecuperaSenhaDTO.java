package br.com.opus.auth.model.dto;

import br.com.opus.auth.anotation.ValidPassword;
import br.com.opus.auth.anotation.ValidPassword;
import br.com.opus.auth.anotation.ValidPassword;
import lombok.*;

import javax.validation.constraints.NotEmpty;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class RecuperaSenhaDTO extends EntidadeBaseDTO {

    @ValidPassword
    @NotEmpty
    private String senha;
    @NotEmpty
    private String confirmarSenha;
    @NotEmpty
    private String codigoRecuperacao;

}