package pl.beganov.myuni.service.core;

public interface AuthService {
    String authenticate();

    String handleCallback(String oauthToken, String oauthVerifier);
}
