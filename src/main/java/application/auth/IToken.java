package application.auth;

public interface IToken {
    String createToken(String login);
    boolean checkToken(String login, String token);
}
