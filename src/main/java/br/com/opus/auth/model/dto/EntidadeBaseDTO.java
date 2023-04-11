package br.com.opus.auth.model.dto;

import br.com.opus.auth.model.EntidadeAPI;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class EntidadeBaseDTO extends EntidadeAPI {

    private Long id;
}