package pl.beganov.myuni.service.core.impl;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.*;
import com.github.scribejava.core.oauth.OAuth10aService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import pl.beganov.myuni.api.UsosApi;
import pl.beganov.myuni.config.UsosSecretsConfig;
import pl.beganov.myuni.constants.UsosEndpointConstants;
import pl.beganov.myuni.exception.LoginFailedException;
import pl.beganov.myuni.service.core.AppUserService;
import pl.beganov.myuni.service.core.AuthService;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthServiceImpl implements AuthService {

    final AppUserService appUserService;
    final OAuth10aService service;
    OAuth1RequestToken requestToken;

    public AuthServiceImpl(UsosSecretsConfig secrets, AppUserService appUserService) {
        this.appUserService = appUserService;
        this.service = new ServiceBuilder(secrets.getKey())
                .apiSecret(secrets.getSecret())
                .callback("http://localhost:8090/api/auth/callback")
                .build(UsosApi.instance());
    }
    @Override
    public String authenticate() {
        try {
            requestToken = service.getRequestToken();
            return service.getAuthorizationUrl(requestToken);
        } catch (IOException | InterruptedException | ExecutionException e) {
            throw new LoginFailedException("Login failed");
        }
    }

    @Override
    public String handleCallback(String oauthToken, String oauthVerifier) {
        try {
            OAuth1AccessToken accessToken = service.getAccessToken(requestToken, oauthVerifier);
            OAuthRequest request = new OAuthRequest(Verb.GET, UsosEndpointConstants.USER_INFO_URL);
            service.signRequest(accessToken, request);
            Response response = service.execute(request);
            if (response.isSuccessful()) {
                appUserService.saveAuthenticatedUser(accessToken.getToken(), accessToken.getTokenSecret(), response.getBody());
            }
            System.out.println(response.getBody());
            return response.getBody();
        }catch (IOException | InterruptedException | ExecutionException e) {
            throw new LoginFailedException("Call Back failed:"+ e.getMessage());
        }
    }
}
