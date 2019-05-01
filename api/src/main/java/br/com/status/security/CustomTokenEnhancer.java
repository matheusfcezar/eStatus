package br.com.status.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import br.com.status.domain.Usuario;
import br.com.status.repository.UsuarioRepository;

public class CustomTokenEnhancer implements TokenEnhancer {
	
	@Autowired
	UsuarioRepository usuarioRepository;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		User principal = (User) authentication.getPrincipal();
		
		Usuario usuario = usuarioRepository.findByEmail(principal.getUsername());

		Map<String, Object> addInfo = new HashMap<>();
		addInfo.put("nome", usuario.getNome());
		addInfo.put("advogado", usuario.isAdvogado());

		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(addInfo);

		return accessToken;
	}
	
}