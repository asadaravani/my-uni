package pl.beganov.myuni.api;

import com.github.scribejava.core.builder.api.DefaultApi10a;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import pl.beganov.myuni.constants.UsosEndpointConstants;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
@NoArgsConstructor
public class UsosApi extends DefaultApi10a {

    private static final UsosApi INSTANCE = new UsosApi();

    public static UsosApi instance() {
        return INSTANCE;
    }

    @Override
    public String getRequestTokenEndpoint() {
        return UsosEndpointConstants.REQUEST_TOKEN_URL;
    }

    @Override
    public String getAccessTokenEndpoint() {
        return UsosEndpointConstants.ACCESS_TOKEN_URL;
    }

    @Override
    protected String getAuthorizationBaseUrl() {
        return UsosEndpointConstants.AUTHORIZATION_URL;
    }
}
