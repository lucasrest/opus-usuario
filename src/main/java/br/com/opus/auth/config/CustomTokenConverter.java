package br.com.opus.auth.config;

import br.com.opus.auth.model.CustomUserDetails;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.HashMap;
import java.util.Map;

public class CustomTokenConverter extends JwtAccessTokenConverter {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        if (authentication.getOAuth2Request().getGrantType().equalsIgnoreCase("password")) {
            final Map<String, Object> additionalInfo = new HashMap<>();
            CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
            additionalInfo.put("username", principal.getUsername());
            additionalInfo.put("name", principal.getName());
            additionalInfo.put("profiles", principal.getProfiles());
            additionalInfo.put("modulos", principal.getModulos());
            ((DefaultOAuth2AccessToken) accessToken)
                    .setAdditionalInformation(additionalInfo);
        }
        accessToken = super.enhance(accessToken, authentication);
        return accessToken;
    }
}
