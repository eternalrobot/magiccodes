package Core.Controller.StompHandler;

import Core.data.python_on_edit;
import Core.data.user;
import Core.db.Service.pythoneditService;
import Core.db.Service.utilsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class pythoneditHandler {
    private static final Logger logger = LoggerFactory
            .getLogger(pythoneditHandler.class);

    @Autowired
    Core.db.Service.utilsService utilsService;

    @Autowired
    pythoneditService pythoneditService;

    @MessageMapping("/pythongoon")
    @SendToUser("/queue/pythonoutinfo")
    public Shout pythongoon(Principal principal, Shout incoming) {
        logger.info("Received message: " + incoming.getMessage());
        user user=utilsService.finduserbyname(principal.getName());
        python_on_edit python_on_edit=utilsService.findpython_on_editbyuserid(user.getId());
        pythoneditService.runpythonfile(user.getUsername(),python_on_edit.getContent());
        Shout outgoing = new Shout();
        outgoing.setMessage("@GoOn@");
        return outgoing;
    }

    @MessageMapping("/pythonstop")
    @SendToUser("/queue/pythonoutinfo")
    public Shout pythonstop(Principal principal,Shout incoming){
        logger.info("Received message: " + incoming.getMessage());
        Shout outgoing = new Shout();
        if(pythoneditService.stopRunning(principal.getName())){
            outgoing.setMessage("@StopSuccess@");
        }else{
            outgoing.setMessage("@StopFail@");
        }
        return outgoing;
    }

    @Scheduled(fixedRate = 1000)
    public void sendPythonMessageToUser(){
        pythoneditService.sendMessageToUser();
    }

    @RequestMapping(value = "/utils/savepythondata",method = POST)
    @ResponseBody
    public Map<String,Object> savepythondata(@RequestParam(value = "savedata") String savedata){
        UserDetails userDetails= (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();//获得当前用户的信息
        user user=utilsService.finduserbyname(userDetails.getUsername());
        python_on_edit python_on_edit=utilsService.findpython_on_editbyuserid(user.getId());
        if(python_on_edit==null){
            python_on_edit=new python_on_edit(user.getId(),savedata);
            utilsService.savepython_on_edit(python_on_edit);
        }else{
            python_on_edit.setContent(savedata);
            utilsService.updatepython_on_edit(python_on_edit);
        }
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("success",true);
        return map;
    }

    @RequestMapping(value = "/utils/getpythondata",method = GET)
    @ResponseBody
    public Map<String,Object> getpythondata(){
        UserDetails userDetails= (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();//获得当前用户的信息
        user user=utilsService.finduserbyname(userDetails.getUsername());
        python_on_edit python_on_edit=utilsService.findpython_on_editbyuserid(user.getId());
        Map<String,Object> map=new HashMap<String, Object>();
        if(python_on_edit!=null){
            map.put("pythondata",python_on_edit.getContent());
            map.put("success",true);
        }else{
            map.put("success",false);
        }
        return map;
    }
}
