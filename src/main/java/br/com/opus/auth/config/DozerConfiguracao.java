package br.com.opus.auth.config;

import br.com.opus.auth.model.EntidadeBase;
import org.dozer.DozerBeanMapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DozerConfiguracao {

    @Bean
    public DozerBeanMapper mapper() {
        DozerBeanMapper mapper = new DozerBeanMapper();
        mapper.addMapping(objectMappingBuilder);
        return mapper;
    }

    BeanMappingBuilder objectMappingBuilder = new BeanMappingBuilder() {
        @Override
        protected void configure() {
            mapping(EntidadeBase.class, EntidadeBase.class)
                    .exclude("inclusao")
                    .exclude("alteracao");
        }
    };

}