package timer;

import java.util.Date;

import builder.Build.Built;
import chat.IChat;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.ejb.Timeout;
import jakarta.ejb.TimerConfig;
import jakarta.ejb.TimerService;
import jakarta.inject.Inject;
import modelLayer.IModel;
import websocket.Chat;

@Singleton
@Startup
public class Timer {
    
    @Resource
    TimerService tservice; 

    @Inject
	IModel model;

    @Inject
    IChat chat;
    
    @PostConstruct    
    public void start() {
        tservice.createIntervalTimer(new Date(), 300000, new TimerConfig()); 
    }
  
    @Timeout
    public void timeout() {
        Chat.broadcast(chat.getAdvertisement());
    }
}
