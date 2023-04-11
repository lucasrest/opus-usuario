package br.com.opus.auth.model.comum;

import br.com.opus.auth.exception.handler.ApiExceptionHandler;
import br.com.opus.auth.model.EntidadeAPI;
import br.com.opus.auth.model.constants.EXCEPTION;
import br.com.opus.auth.exception.handler.ApiExceptionHandler;
import br.com.opus.auth.model.constants.EXCEPTION;
import br.com.opus.auth.exception.handler.ApiExceptionHandler;
import br.com.opus.auth.model.constants.EXCEPTION;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.common.exceptions.UnauthorizedClientException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

public class ApiResponse extends EntidadeAPI {

    public static final String CONTENT_TYPE = "application/json";

    private Integer pageNumber = 1;

    private Integer totalPages = 1;

    private Integer pageSize = 10;

    private Long totalElements;

    private Object data = new EmptyJsonObject();

    private String message = "Solicitação concluída com sucesso.";

    private Set<String> errors = new HashSet<>();

    public HttpStatus statusCode;

    public static Map<String, Object> transformInMap(ApiResponse apiResponse) {
        Map<String, Object> response = new HashMap<>();
        response.put("data", apiResponse.getData());
        response.put("message", apiResponse.getMessage());
        response.put("errors", apiResponse.getErrors());
        return response;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Set<String> getErrors() {
        return errors;
    }

    public void setErrors(Set<String> errors) {
        this.errors = errors;
    }

    public ApiResponse() {
    }

    public ApiResponse(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }

    public static ApiResponse returnOk() {
        return new ApiResponse();
    }

    public static ApiResponse returnOk(Object objeto, String mensagem) {
        ApiResponse response = new ApiResponse();
        response.setData(objeto);
        if (!mensagem.isEmpty())
            response.setMessage(mensagem);

        return response;
    }

    public static Object returnError(List<ApiError> erros, String message) {
        return setError(erros.stream().map(ApiError::getUserMessage).collect(Collectors.toSet()), message);
    }

    public static Object returnError(Exception exception) {
        Set<String> errors = ApiExceptionHandler.fillException(exception);
        String message = getMessage(exception);
        return setError(errors, message);
    }

    private static String getMessage(Exception exception) {
        if (exception instanceof org.springframework.security.core.AuthenticationException ||
                exception instanceof UnauthorizedClientException) {
            return EXCEPTION.FALHA_NA_AUTENTICACAO;
        }
        return EXCEPTION.ERRO_INTERNO;
    }

    public static Object returnError(Set<String> errors, String message) {
        return setError(errors, message);
    }

    public static Object returnError(Set<String> errors) {
        return setError(errors, EXCEPTION.ERRO_INTERNO);
    }

    private static Object setError(Set<String> errors, String message) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage(message);
        if (errors instanceof Set<?>)
            apiResponse.setErrors(errors);
        else
            apiResponse.setErrors(new HashSet(asList(errors)));
        return apiResponse;
    }

    public static ApiResponse returnOk(Object object) {
        ApiResponse response = new ApiResponse();
        response.setData(object);
        return response;
    }

    public static ApiResponse returnOk(Exception erro) {
        ApiResponse response = new ApiResponse();
        response.setMessage(erro.getMessage());
        return response;
    }

    public static ApiResponse returnOk(String mensagem) {
        ApiResponse response = new ApiResponse();
        response.setMessage(mensagem);
        return response;
    }

    public static ApiResponse returnOk(String mensagem, Set<String> erros) {
        ApiResponse response = new ApiResponse();
        response.setMessage(mensagem);
        response.setErrors(erros);
        return response;
    }

    public static ApiResponse returnOk(String mensagem, ConstraintViolationException constraintViolationException) {
        ApiResponse response = new ApiResponse();
        response.setMessage(mensagem);
        response.setErrors(buildErrors(constraintViolationException));
        return response;
    }

    private static Set<String> buildErrors(ConstraintViolationException constraintViolationException) {
        Set<String> errors = new HashSet<>();
        Set<ConstraintViolation<?>> ErrosViolacoes = constraintViolationException.getConstraintViolations();
        for (ConstraintViolation<?> constraintViolation : ErrosViolacoes) {
            errors.add(String.format("%s %s", constraintViolation.getInvalidValue(), constraintViolation.getMessage()));
        }

        return Collections.unmodifiableSet(errors);
    }

}
