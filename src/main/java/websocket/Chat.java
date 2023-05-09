package websocket;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import chat.IChat;
import chat.Message;
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
public class Chat {

    private Session session;
    private static Set<Chat> chats = new CopyOnWriteArraySet<>();
    private static HashMap<String, String> users = new HashMap<>();

    @Inject
    IChat chatModel;
 
    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username) throws IOException, EncodeException{
        this.session = session;
        chats.add(this);
        users.put(session.getId(), username);
    }

    @OnMessage
    public void onMessage(Session session, String text) throws IOException, EncodeException{
        broadcast(chatModel.getMessage(users.get(session.getId()), text));
    }

    @OnClose
    public void onClose(Session session){
        chats.remove(this);
    }   

    public static void broadcast(Message message){
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