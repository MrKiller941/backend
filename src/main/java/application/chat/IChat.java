package application.chat;

import application.chat.message.Message;

public interface IChat {
    void setChatService(ISender chatService);
    void sendAll(Message message);
}
