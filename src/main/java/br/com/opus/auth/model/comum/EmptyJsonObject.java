package br.com.opus.auth.model.comum;

import br.com.opus.auth.model.EntidadeAPI;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@JsonSerialize
public class EmptyJsonObject extends EntidadeAPI {
}
