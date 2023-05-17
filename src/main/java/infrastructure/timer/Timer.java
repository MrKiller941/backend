package infrastructure.timer;

import java.util.Date;
import application.chat.IChat;
import application.chat.message.IGetMessage;
import infrastructure.builder.Build.Built;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.ejb.Timeout;
import jakarta.ejb.TimerConfig;
import jakarta.ejb.TimerService;
import jakarta.inject.Inject;

@Singleton
@Startup
public class Timer {
    
    @Resource
    TimerService tservice; 

    @Inject @Built
    IChat chat;

    @Inject
    IGetMessage messageService;
    
    @PostConstruct    
    public void start() {
        tservice.createIntervalTimer(new Date(), 300000, new TimerConfig()); 
    }
  
    @Timeout
    public void timeout() {
        chat.sendAll(messageService.getAdvertisement());
    }
}
