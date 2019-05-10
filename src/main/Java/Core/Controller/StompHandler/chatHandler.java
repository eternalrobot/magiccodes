package Core.Controller.StompHandler;

import Core.data.user;
import Core.db.Service.chatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class chatHandler {
    private static final Logger logger = LoggerFactory
            .getLogger(pythoneditHandler.class);

    @Autowired
    chatService chatService;

    @Scheduled(fixedRate = 1000)
    public void sendChatMessageToUser(){
        chatService.sendMessageToUser();
    }

    @Scheduled(fixedRate = 2000)
    public void sendUserFriendMessageToUser(){
        chatService.sendUserFriendMessageToUser();
    }

    @MessageMapping("/addfriend")
    @SendToUser("/queue/chatfriend")
    public Shout addfriend(Principal principal, Shout incoming){
        logger.info("Received message: " + incoming.getMessage());
        Shout outgoing = new Shout();
        user user=chatService.findUserByUsername(incoming.getMessage());
        if(user==null){
            outgoing.setMessage("addfriend_false");
        }else{
            if(chatService.addUser_User(principal.getName(),user.getUsername())){
                outgoing.setMessage("addfriend_true");
            }
            else{
                outgoing.setMessage("addfriend_false");
            }
        }
        return outgoing;
    }

    @MessageMapping("/sendchat")
    public void sendchat(Principal principal, Shout incoming){
        logger.info("Received message: " + incoming.getMessage());
        String receiveuser=incoming.getMessage().split("\\|")[0];
        String sendcontent=incoming.getMessage().split("\\|")[1];
        logger.info("receive:"+receiveuser);
        chatService.addChatInfo(principal.getName(),receiveuser,sendcontent);
    }
}
