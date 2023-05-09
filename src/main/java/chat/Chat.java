package chat;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Chat implements IChat {

    @Override
    public Message getMessage(String username, String text) {
        Message message = new Message();
        message.setUsername(username);
        message.setText(text);
        message.setTime(getTime());
        return message;
    }

    private String getTime(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime localTime = LocalTime.now();
        return dtf.format(localTime).toString();
    }

    @Override
    public Message getAdvertisement() {
        Message message = new Message();
        message.setUsername("Аптека");

        String text = "Подпишитесь на наш тг канал:";
        String link = "https://t.me/killerteka";
        message.setText(text +  link);
        message.setTime(getTime());
        return message;
    }
    
}
