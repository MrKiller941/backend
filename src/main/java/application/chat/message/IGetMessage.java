package application.chat.message;

public interface IGetMessage {
    Message getMessage(String text, String username);
    Message getAdvertisement();
    String getTime();
}
