package Core.db.Service;

import Core.Controller.StompHandler.Shout;
import Core.data.chat;
import Core.data.user;
import Core.data.user_user;
import Core.db.Dao.chatDao;
import Core.db.Dao.userDao;
import Core.db.Dao.user_userDao;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service("chatService")
public class chatService {
    private SimpMessagingTemplate messaging;
    @Autowired
    public chatService(SimpMessagingTemplate messaging) {
        this.messaging = messaging;
    }

    private final static Logger logger=LoggerFactory.getLogger(pythoneditService.class);

    @Autowired
    chatDao chatDao;

    @Autowired
    userDao userDao;

    @Autowired
    user_userDao user_userDao;

    public void sendMessageToUser() {
        List<chat> chats=chatDao.findNoPage();
        for(chat chat:chats){
            JSONObject jsonObject=new JSONObject();
            String receiveusername= ((user) userDao.findByID(chat.getReceiveuserid())).getUsername();
            String sendusername=((user)userDao.findByID(chat.getSenduserid())).getUsername();
            jsonObject.put("receiveuser",receiveusername);
            jsonObject.put("senduser",sendusername);
            jsonObject.put("content",chat.getContent());
            jsonObject.put("time",chat.getTime());
            jsonObject.put("isread",chat.isIsread());
            jsonObject.put("ismereceive",1);
            helpSendMessageToUser(receiveusername,jsonObject);
            jsonObject.put("ismereceive",0);
            helpSendMessageToUser(sendusername,jsonObject);
        }
    }

    private void helpSendMessageToUser(String user,JSONObject jsonObject){
        Shout message=new Shout();
        message.setMessage(jsonObject.toString());
        messaging.convertAndSendToUser(user,"/queue/chatinfo",message);
    }

    public void sendUserFriendMessageToUser(){
        List<user_user> user_users=user_userDao.findNoPage();
        for(user_user user_user:user_users){
            Shout message=new Shout();
            message.setMessage(user_user.getUsername2());
            messaging.convertAndSendToUser(user_user.getUsername1(),"/queue/chatfriend",message);
        }
    }

    @Transactional
    public user findUserByUsername(String username){
        try{
            user user =userDao.findByString(username);
            logger.info("找到用户："+user);
            return user;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Transactional
    public boolean addUser_User(String username1,String username2){
        try{
            user_user user_user=new user_user(username1,username2);
            user_user user_user1=new user_user(username2,username1);
            ObjectMapper mapper=new ObjectMapper();
            user_user user_userT=mapper.convertValue(user_userDao.findUser_UserByTwoUsername(username1,username2),user_user.class);
            logger.info("找到关系："+user_userT);
            if(user_userT!=null){
                return false;
            }
            user_userDao.save(user_user);
            user_userDao.save(user_user1);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Transactional
    public boolean addChatInfo(String senduser,String receiveuser,String content){
        try{
            chat chat=new chat(((user)userDao.findByString(senduser)).getId(),((user)userDao.findByString(receiveuser)).getId(),new Date(),false,content);
            chatDao.save(chat);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
