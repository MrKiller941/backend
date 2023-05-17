package application.chat;

import application.chat.message.Message;

public interface ISender {
    void sendAll(Message message);
}
