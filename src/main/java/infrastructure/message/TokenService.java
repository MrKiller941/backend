package infrastructure.message;

import application.auth.IToken;
import jakarta.annotation.Resource;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.JMSConsumer;
import jakarta.jms.JMSContext;
import jakarta.jms.JMSProducer;
import jakarta.jms.Message;
import jakarta.jms.Queue;

public class TokenService implements IToken {

    @Resource(mappedName = "jms/ConnectionFactory")    
    private ConnectionFactory connectionFactory;
  
    @Resource(mappedName = "jms/MedicQueue") 
    private Queue queueMedic;
  
    @Resource(mappedName = "jms/TokenQueue")
    private Queue queueToken;

    @Override
    public String createToken(String login) {
        try {
            JMSContext context = connectionFactory.createContext();
            JMSProducer producer = context.createProducer();     
            JMSConsumer consumer = context.createConsumer(queueMedic);       
            Message message = context.createMessage();
            message.setJMSType("getToken");
            message.setStringProperty("login", login);
            producer.send(queueToken, message);
            Message newMessage = consumer.receive(); 
            return newMessage.getStringProperty("token");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean checkToken(String login, String token) {
        try {
            JMSContext context = connectionFactory.createContext();
            JMSProducer producer = context.createProducer();     
            JMSConsumer consumer = context.createConsumer(queueMedic);       
            Message message = context.createMessage();
            message.setJMSType("checkToken");
            message.setStringProperty("login", login);
            message.setStringProperty("token", token);
            producer.send(queueToken, message);
            Message newMessage = consumer.receive(); 
            return newMessage.getBooleanProperty("check");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
}
