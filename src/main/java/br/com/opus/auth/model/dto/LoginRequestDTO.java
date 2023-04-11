package br.com.opus.auth.model.dto;

import br.com.opus.auth.model.EntidadeAPI;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDTO extends EntidadeAPI {

    private String username;
    private String password;
    @JsonProperty("grant_type")
    private String grantType;
}
