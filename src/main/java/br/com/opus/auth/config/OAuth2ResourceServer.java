package br.com.opus.auth.config;

import br.com.opus.auth.filter.MyAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.expression.OAuth2MethodSecurityExpressionHandler;

/**
 * Classe de configuração das regras de acesso ao endpoint
 */
@Configuration
@EnableWebSecurity
@EnableResourceServer
public class OAuth2ResourceServer extends ResourceServerConfigurerAdapter {

    private String ENDPOINT = "/v1/usuarios";

    @Qualifier("userServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * configuração do servico de consulta de usuário
     * configuração do encodamento de senha
     *
     * @param auth
     * @throws Exception
     */
    @Autowired
    public void configureGlobal(final AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    /**
     * configuração do regra de acesso aos endpoinst
     *
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.exceptionHandling()
                .authenticationEntryPoint(new MyAuthenticationEntryPoint())
                .and()
                .authorizeRequests()
                .antMatchers("/oauth/token").permitAll()
                .antMatchers("/actuator/health",
                        ENDPOINT + "{email}/recuperar",
                        ENDPOINT + "{email}/resetar"
                ).permitAll()
                .antMatchers(HttpMethod.POST, ENDPOINT + "/login").permitAll()
                .antMatchers(HttpMethod.POST, ENDPOINT).permitAll()
                .antMatchers(HttpMethod.PUT, ENDPOINT).permitAll()
                .antMatchers("/swagger-ui.html", "/swagger-resources/**", "/webjars/**", "/v2/api-docs").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    public MethodSecurityExpressionHandler createExpressionHandler() {
        return new OAuth2MethodSecurityExpressionHandler();
    }

}
