package br.com.opus.auth.controller;

import br.com.opus.auth.model.comum.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public abstract class BaseController {

    protected ApiResponse preencherApiResponseComPaginacao(Pageable pageable, Page page) {
        ApiResponse response = ApiResponse.returnOk(page.getContent());
        response.setPageNumber(pageable.getPageNumber());
        response.setTotalPages(page.getTotalPages());
        response.setPageSize(pageable.getPageSize());
        response.setTotalElements(page.getTotalElements());
        return response;
    }

}