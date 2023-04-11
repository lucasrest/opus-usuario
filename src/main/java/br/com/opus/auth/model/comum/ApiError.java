package br.com.opus.auth.model.comum;

public class ApiError {

    private String userMessage;

    private String developerMessage;

    public ApiError(String userMessage, String developerMessage) {
        super();
        this.userMessage = userMessage;
        this.developerMessage = developerMessage;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

}
