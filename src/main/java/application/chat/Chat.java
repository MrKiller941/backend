package application.chat;

import application.chat.message.Message;

public class Chat implements IChat {

    private ISender chatService;

    @Override
    public void setChatService(ISender chatService) {
        this.chatService = chatService;
    }

    @Override
    public void sendAll(Message message) {
        chatService.sendAll(message);
    }
}
