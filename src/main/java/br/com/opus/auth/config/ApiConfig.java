package br.com.opus.auth.config;

import br.com.opus.auth.security.CryptoUtil;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class ApiConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CustomPasswordEncoder customPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder, CryptoUtil cryptoUtil) {
        return new CustomPasswordEncoder(bCryptPasswordEncoder, cryptoUtil);
    }
}
