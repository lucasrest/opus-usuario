package br.com.opus.auth.controller;

import br.com.opus.auth.logger.LoggerStepEnum;
import br.com.opus.auth.logger.LoggerUtil;
import br.com.opus.auth.model.Operacao;
import br.com.opus.auth.model.comum.ApiResponse;
import br.com.opus.auth.model.constants.API;
import br.com.opus.auth.model.dto.OperacaoDTO;
import br.com.opus.auth.service.impl.OperacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = API.VERSAO + "/cadastro/operacoes", produces = MediaType.APPLICATION_JSON_VALUE)
public class OperacaoController extends BaseController {

    @Autowired
    private OperacaoService service;

    @PreAuthorize("#oauth2.hasScope('usuario') || hasAuthority('operacao-buscartodos')")
    @PostMapping("/filtro")
    public ApiResponse buscarTodos(Pageable pageable) {
        LoggerUtil.logger(LoggerStepEnum.OPE00001, pageable);

        ApiResponse apiResponse = preencherApiResponseComPaginacao(pageable, service.buscarTodos(pageable));

        LoggerUtil.logger(LoggerStepEnum.OPE00001, pageable, apiResponse);

        return apiResponse;
    }

    @GetMapping("/{id}")
    public ApiResponse buscarPorId(@PathVariable Long id) {
        LoggerUtil.logger(LoggerStepEnum.OPE00002, id);

        Operacao operacao = service.buscarPorId(id);

        LoggerUtil.logger(LoggerStepEnum.OPE00002, id, operacao);

        return ApiResponse.returnOk(operacao);
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping
    public ApiResponse incluir(@RequestBody @Valid OperacaoDTO dto) {
        LoggerUtil.logger(LoggerStepEnum.OPE00003, dto);

        Operacao operacao = service.incluir(service.converterDTOParaEntidade(dto));

        LoggerUtil.logger(LoggerStepEnum.OPE00003, dto, operacao);

        return ApiResponse.returnOk(service.converterEntidadeParaDTO(operacao));
    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @PutMapping
    public ApiResponse atualizar(@RequestBody @Valid OperacaoDTO dto) {
        LoggerUtil.logger(LoggerStepEnum.OPE00004, dto);

        Operacao operacao = service.atualizar(service.converterDTOParaEntidade(dto));

        LoggerUtil.logger(LoggerStepEnum.OPE00004, operacao);

        return ApiResponse.returnOk();
    }

    @DeleteMapping("/{id}")
    public ApiResponse excluir(@PathVariable Long id) {
        LoggerUtil.logger(LoggerStepEnum.OPE00005, id);

        service.excluir(id);

        LoggerUtil.logger(LoggerStepEnum.OPE00005, id);

        return ApiResponse.returnOk();
    }
}