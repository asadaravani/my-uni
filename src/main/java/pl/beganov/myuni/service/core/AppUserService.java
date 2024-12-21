package pl.beganov.myuni.service.core;

import pl.beganov.myuni.entity.AppUser;

public interface AppUserService {

    void save(AppUser appUser);

    void saveAuthenticatedUser(String accessToken, String tokenSecret, String responseBody);

    AppUser findById(Long id);
}
