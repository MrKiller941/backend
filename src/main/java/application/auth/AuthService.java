package application.auth;

import application.auth.user.IUserRepository;
import application.auth.user.User;

public class AuthService implements IAuth {

    private IUserRepository repository;
    private IToken tokenService;

    @Override
    public boolean checkUserData(User user) {
        return repository.check(user);
    }

    @Override
    public boolean registrateNewUser(User user) {
        return repository.add(user);
    }

    @Override
    public void setUserRepository(IUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public String createToken(String login) {
        return tokenService.createToken(login);
    }

    @Override
    public boolean checkToken(String login, String token) {
        return tokenService.checkToken(login, token);
    }

    @Override
    public void setTokenService(IToken tokenService) {
        this.tokenService = tokenService;
    }
    
}
