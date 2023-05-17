package application.chat.message;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class GetMessage implements IGetMessage {
    
    @Override
    public Message getAdvertisement() {
        return getMessage("Аптека", "Подпишитесь на наш тг канал: https://t.me/killerteka");
    }

    @Override
    public Message getMessage(String text, String username) {
        Message message = new Message();
        message.setUsername(username);
        message.setText(text);
        message.setTime(getTime());
        return message;
    }

    @Override
    public String getTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime localTime = LocalTime.now();
        return dtf.format(localTime).toString();
    }
}
