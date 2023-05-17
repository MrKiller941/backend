package infrastructure.controller.websocket;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import application.chat.ISender;
import application.chat.message.IGetMessage;
import application.chat.message.Message;
import jakarta.inject.Inject;
import jakarta.websocket.EncodeException;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/chat/{username}",
                decoders = MessageDecoder.class,
                encoders = MessageEncoder.class)
public class ChatService implements ISender {

    @Inject
    private IGetMessage messageService;

    private Session session;
    private static Set<ChatService> chats = new CopyOnWriteArraySet<>();
    private static HashMap<String, String> users = new HashMap<>();
 
    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username) throws IOException, EncodeException{
        this.session = session;
        chats.add(this);
        users.put(session.getId(), username);
    }

    @OnMessage
    public void onMessage(Session session, String text) throws IOException, EncodeException{
        sendAll(messageService.getMessage(text, users.get(session.getId())));
    }

    @OnClose
    public void onClose(Session session){
        chats.remove(this);
    }   

    @Override
    public void sendAll(Message message) {
        chats.forEach(chat -> {
            synchronized (chat){
                try {
                    chat.session.getBasicRemote().sendObject(message);
                } catch (IOException | EncodeException e) {
                    e.printStackTrace();
                }
            }
        });
    }


}