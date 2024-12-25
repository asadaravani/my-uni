package pl.beganov.myuni.service.usos.impl;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth10aService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import pl.beganov.myuni.api.UsosApi;
import pl.beganov.myuni.config.UsosSecretsConfig;
import pl.beganov.myuni.constants.UsosEndpointConstants;
import pl.beganov.myuni.entity.AppUser;
import pl.beganov.myuni.service.core.AppUserService;
import pl.beganov.myuni.service.core.LessonService;
import pl.beganov.myuni.service.usos.UsosUpdateService;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UsosUpdateServiceImpl implements UsosUpdateService {
    OAuth10aService service;
    AppUserService appUserService;
    LessonService lessonService;

    public UsosUpdateServiceImpl(AppUserService appUserService, UsosSecretsConfig secrets, LessonService lessonService) {
        this.appUserService = appUserService;
        service = new ServiceBuilder(secrets.getKey())
                .apiSecret(secrets.getSecret())
                .build(UsosApi.instance());
        this.lessonService = lessonService;
    }

    @Override
    public String getScheduleByUserId(Long userId) {
        try{
            AppUser appUser = appUserService.findById(userId);
            OAuth1AccessToken accessToken = new OAuth1AccessToken(appUser.getAccessToken(), appUser.getTokenSecret());
            OAuthRequest request = new OAuthRequest(Verb.GET, UsosEndpointConstants.TIME_TABLE);
            service.signRequest(accessToken, request);
            Response response = service.execute(request);
            if (response.isSuccessful()) {
                lessonService.save(response.getBody(), appUser);
            }
            return response.getBody();
        }catch (InterruptedException | ExecutionException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
