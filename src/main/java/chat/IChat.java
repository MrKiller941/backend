package chat;

public interface IChat {
    Message getMessage(String username, String text);
    Message getAdvertisement();
}
