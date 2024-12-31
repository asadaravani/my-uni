package pl.beganov.myuni.service.usos.impl;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth10aService;
import lombok.AccessLevel;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import pl.beganov.myuni.api.UsosApi;
import pl.beganov.myuni.config.UsosSecretsConfig;
import pl.beganov.myuni.constants.UsosEndpointConstants;
import pl.beganov.myuni.entity.AppUser;
import pl.beganov.myuni.exception.LoginFailedException;
import pl.beganov.myuni.service.core.ActivityService;
import pl.beganov.myuni.service.core.AppUserService;
import pl.beganov.myuni.service.core.CourseService;
import pl.beganov.myuni.service.usos.UsosUpdateService;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UsosUpdateServiceImpl implements UsosUpdateService {
    OAuth10aService service;
    AppUserService appUserService;
    CourseService courseService;
    ActivityService activityService;

    public UsosUpdateServiceImpl(AppUserService appUserService, UsosSecretsConfig secrets, CourseService courseService, ActivityService activityService) {
        this.appUserService = appUserService;
        service = new ServiceBuilder(secrets.getKey())
                .apiSecret(secrets.getSecret())
                .build(UsosApi.instance());
        this.courseService = courseService;
        this.activityService = activityService;
    }

    @SneakyThrows
    @Override
    public String updateCoursesByUserId(Long userId) {
        AppUser appUser = appUserService.findById(userId);
        OAuth1AccessToken accessToken = new OAuth1AccessToken(appUser.getAccessToken(), appUser.getTokenSecret());
        OAuthRequest request = new OAuthRequest(Verb.GET, UsosEndpointConstants.USER_COURSES_URL);
        service.signRequest(accessToken, request);
        Response response = service.execute(request);
        if (!response.isSuccessful()) {
            throw new LoginFailedException("Update failed");
        }
        courseService.save(response.getBody(), appUser);
        return response.getBody();
    }

    @SneakyThrows
    @Override
    public String updateActivitiesByUserId(Long userId) {
        AppUser appUser = appUserService.findById(userId);
        OAuth1AccessToken accessToken = new OAuth1AccessToken(appUser.getAccessToken(), appUser.getTokenSecret());
        OAuthRequest request = new OAuthRequest(Verb.GET, UsosEndpointConstants.TIME_TABLE);
        service.signRequest(accessToken, request);
        Response response = service.execute(request);
        if (!response.isSuccessful()) {
            throw new LoginFailedException("Update failed");
        }
        activityService.save(response.getBody());
        return response.getBody();
    }
}
