package application.auth;

import application.auth.user.IUserRepository;
import application.auth.user.User;

public interface IAuth {
    boolean checkUserData(User user);
	boolean registrateNewUser(User user);
    String createToken(String login);
    boolean checkToken(String login, String token);
    void setUserRepository(IUserRepository repository);
    void setTokenService(IToken tokenService);
}
