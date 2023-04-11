package br.com.opus.auth.controller;


import br.com.opus.auth.model.comum.ApiResponse;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Controller
@RequestMapping(path = "/error", produces = MediaType.APPLICATION_JSON_VALUE)
@ResponseBody
public class ErrorController extends AbstractErrorController {

    public ErrorController(ErrorAttributes errorAttributes,
                           ObjectProvider<ErrorViewResolver> errorViewResolvers) {
        super(errorAttributes, errorViewResolvers.orderedStream().collect(Collectors.toList()));
    }

    @GetMapping
    public ResponseEntity error(HttpServletRequest request) {
        return getResponseEntity(request);
    }

    @PostMapping
    public ResponseEntity errorPost(HttpServletRequest request) {
        return getResponseEntity(request);
    }

    @PutMapping
    public ResponseEntity errorPut(HttpServletRequest request) {
        return getResponseEntity(request);
    }

    @DeleteMapping
    public ResponseEntity errorDelete(HttpServletRequest request) {
        return getResponseEntity(request);
    }

    @PatchMapping
    public ResponseEntity errorPatch(HttpServletRequest request) {
        return getResponseEntity(request);
    }

    private ResponseEntity getResponseEntity(HttpServletRequest request) {
        HttpStatus status;
        try {
            status = getStatus(request);
            if (status == HttpStatus.NO_CONTENT) {
                return new ResponseEntity<>(status);
            } else if (status == NOT_FOUND) {
                return getErrorResponseEntity(status, "Recurso não encontrado");
            } else if (status == INTERNAL_SERVER_ERROR) {
                return getErrorResponseEntity(status, "Erro interno");
            }
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(getErrorAttributes(request, ErrorAttributeOptions.defaults()), status);
    }

    private ResponseEntity getErrorResponseEntity(HttpStatus httpStatus, String s) {
        return new ResponseEntity<>(ApiResponse.returnError(new RuntimeException(s)), httpStatus);
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}