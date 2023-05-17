package application.auth.user;

public interface IUserRepository {
    boolean check(User user);
	boolean add(User user);
}
