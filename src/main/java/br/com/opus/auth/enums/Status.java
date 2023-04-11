package br.com.opus.auth.enums;

import lombok.Getter;

@Getter
public enum Status {

    ATIVO(1),

    DESATIVADO(0);

    private Integer value;

    Status(Integer value) {
        this.value = value;
    }
}
