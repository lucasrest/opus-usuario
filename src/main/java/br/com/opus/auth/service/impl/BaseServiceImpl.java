package br.com.opus.auth.service.impl;

import br.com.opus.auth.service.BaseCRUDService;
import org.dozer.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class BaseServiceImpl<E, D> implements BaseCRUDService<E, D> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    protected Mapper mapper;

    @Override
    public E converterDTOParaEntidade(D dto) {
        Class<?> classeDoTipoParametrizado = getClasseDoTipoParametrizado(0);
        return modelMapper.map(dto, (Type) classeDoTipoParametrizado);
    }

    @Override
    public D converterEntidadeParaDTO(E entidade) {
        Class<?> classeDoTipoParametrizado = getClasseDoTipoParametrizado(1);
        return modelMapper.map(entidade, (Type) classeDoTipoParametrizado);
    }

    private Class<?> getClasseDoTipoParametrizado(Integer posicao) {
        return (Class<?>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[posicao];
    }

}
