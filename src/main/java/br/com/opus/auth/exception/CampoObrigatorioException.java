package br.com.opus.auth.exception;

public class CampoObrigatorioException extends RuntimeException {

    public CampoObrigatorioException(String message) {
        super(message);
    }
}
