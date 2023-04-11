package br.com.opus.auth.exception.handler;


import br.com.opus.auth.exception.EntidadeNaoEncontradaException;
import br.com.opus.auth.exception.NegocioException;
import br.com.opus.auth.exception.comum.ErrorMessage;
import br.com.opus.auth.model.comum.ApiError;
import br.com.opus.auth.model.comum.ApiResponse;
import br.com.opus.auth.model.constants.EXCEPTION;
import org.hibernate.PropertyValueException;
import org.hibernate.exception.ConstraintViolationException;
import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.UnauthorizedClientException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.NestedServletException;

import javax.persistence.EntityNotFoundException;
import javax.persistence.RollbackException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private static final int MAX_RECURSION_CAUSE = 5;

    @Autowired
    private MessageSource messageSource;


    /**
     * Handle MissingServletRequestParameterException. Triggered when a 'required' request parameter is missing.
     *
     * @param ex      MissingServletRequestParameterException
     * @param headers HttpHeaders
     * @param status  HttpStatus
     * @param request WebRequest
     * @return the ApiError object
     */
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        return getObjectResponseEntity(HttpStatus.BAD_REQUEST, ex);
    }


    /**
     * Handle HttpMediaTypeNotSupportedException. This one triggers when JSON is invalid as well.
     *
     * @param ex      HttpMediaTypeNotSupportedException
     * @param headers HttpHeaders
     * @param status  HttpStatus
     * @param request WebRequest
     * @return the ApiError object
     */
    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
            HttpMediaTypeNotSupportedException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        return getObjectResponseEntity(HttpStatus.UNSUPPORTED_MEDIA_TYPE, ex);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(
            EntityNotFoundException ex) {
        return getObjectResponseEntity(HttpStatus.NOT_FOUND, ex);
    }

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    protected ResponseEntity<Object> handleEntidadeNaoEncontradaException(
            EntidadeNaoEncontradaException ex) {
        return getObjectResponseEntity(HttpStatus.NOT_FOUND, ex);
    }

    @ExceptionHandler(NegocioException.class)
    protected ResponseEntity<Object> handleNegocioExceptionException(
            NegocioException ex) {
        return getObjectResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY, ex);
    }

    private ResponseEntity<Object> getObjectResponseEntity(HttpStatus status, Exception ex) {
        return new ResponseEntity<>(ApiResponse.returnError(ex), status);
    }

    /**
     * Handle HttpMessageNotReadableException. Happens when request JSON is malformed.
     *
     * @param ex      HttpMessageNotReadableException
     * @param headers HttpHeaders
     * @param status  HttpStatus
     * @param request WebRequest
     * @return the ApiError object
     */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return getObjectResponseEntity(HttpStatus.BAD_REQUEST, ex);
    }

    /**
     * Handle HttpMessageNotWritableException.
     *
     * @param ex      HttpMessageNotWritableException
     * @param headers HttpHeaders
     * @param status  HttpStatus
     * @param request WebRequest
     * @return the ApiError object
     */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return getObjectResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, ex);
    }

    /**
     * Handle NoHandlerFoundException.
     *
     * @param ex
     * @param headers
     * @param status
     * @param request
     * @return
     */
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
            NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return getObjectResponseEntity(HttpStatus.BAD_REQUEST, ex);
    }

    /**
     * Handle DataIntegrityViolationException, inspects the cause for different DB causes.
     *
     * @param ex the DataIntegrityViolationException
     * @return the ApiError object
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex,
                                                                  WebRequest request) {
        if (ex.getCause() instanceof ConstraintViolationException) {
            String mensagem = "\n";
//            if (ex.getCause().getCause() instanceof PSQLException) {
//                mensagem += " " + ex.getCause().getCause().sqlException;
//            }
            return getObjectResponseEntity(HttpStatus.CONFLICT, ex);
        } else if (ex.getCause() instanceof PropertyValueException) {
            PropertyValueException property = ((PropertyValueException) ex.getCause());
            return getObjectResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY,
                    property);
        }
        return getObjectResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, ex);
    }

    @ExceptionHandler(RollbackException.class)
    protected ResponseEntity<Object> handleRollbackException(RollbackException ex, WebRequest request) {
        if (ex.getCause() instanceof javax.validation.ConstraintViolationException) {
            return getObjectResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY, ex);
        }
        return getObjectResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, ex);
    }

    /**
     * Handle Exception, handle generic Exception.class
     *
     * @param ex the Exception
     * @return the ApiError object
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
                                                                      WebRequest request) {
        return getObjectResponseEntity(HttpStatus.BAD_REQUEST, ex);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<ApiError> erros = createErrorList(ex.getBindingResult());
        return new ResponseEntity<>(ApiResponse.returnError(erros, "Erro na validação dos atributos"), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(UnauthorizedClientException.class)
    public ResponseEntity<Object> handleAutenticacaoException(UnauthorizedClientException e) {
        return new ResponseEntity<>(ApiResponse.returnError(fillException(e)), HttpStatus.UNAUTHORIZED);
    }


    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(ApiResponse.returnError(fillException(ex)), status);
    }

    private List<ApiError> createErrorList(BindingResult bindingResult) {
        List<ApiError> errors = new ArrayList<>();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            String userMessage = fieldError.getField() + " " + messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            String developerMessage = fieldError.toString();
            errors.add(new ApiError(userMessage, developerMessage));
        }
        return errors;
    }

    public static Set<String> fillException(Exception e) {
        Set<String> erros = new HashSet<>();

        javax.validation.ConstraintViolationException constraintViolationException = verifyIfExistConstraintViolationException(e);
        if (constraintViolationException != null) {
            constraintViolationException.getConstraintViolations().stream().forEach(ex ->
                    erros.add(
                            String.format(
                                    ErrorMessage.VIOLATION_ERROR,
                                    String.valueOf(ex.getPropertyPath()),
                                    ex.getMessage()
                            )
                    )
            );
        } else if (e.getCause() != null && e.getCause().getCause() != null &&
                e.getCause().getCause() instanceof PSQLException) {
            erros.add(((PSQLException) e.getCause().getCause()).getServerErrorMessage().getDetail());
        } else {
            erros.add(extractErrorMessage(e));
        }

        return erros;
    }

    private static javax.validation.ConstraintViolationException verifyIfExistConstraintViolationException(Exception e) {
        return getConstraintViolationException(e, MAX_RECURSION_CAUSE);
    }

    private static javax.validation.ConstraintViolationException getConstraintViolationException(Throwable e, Integer maxRecursionCause) {
        if (e instanceof javax.validation.ConstraintViolationException)
            return (javax.validation.ConstraintViolationException) e;
        else if (e.getCause() != null && maxRecursionCause > 0)
            return getConstraintViolationException(e.getCause(), --maxRecursionCause);
        return null;
    }


    private static String extractErrorMessage(Exception exception) {
        if (exception instanceof AuthenticationException)
            return EXCEPTION.FALHA_NA_AUTENTICACAO;
        return exception.getMessage();
    }

    public static int getStatus(Exception e) {
        if (e instanceof AuthenticationException) {
            return HttpStatus.FORBIDDEN.value();
        } else if (e instanceof NestedServletException) {
            return HttpStatus.BAD_REQUEST.value();
        } else if (e instanceof UnauthorizedClientException) {
            return HttpStatus.FORBIDDEN.value();
        }
        return HttpStatus.OK.value();
    }


}

