package Core.Controller;

import Core.data.user;
import Core.db.Service.mainPageService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import net.sf.json.JSONArray;

import java.util.HashMap;
import java.util.Map;

@Controller
public class mainPage {
    @Autowired
    mainPageService mainPageService;

    static Logger logger=Logger.getLogger(mainPage.class);

    @RequestMapping(value = "/home",method = RequestMethod.GET)
    public String Home(){
        logger.info("用户登陆");
        return "/mainPage";
    }

    @RequestMapping(value = "/home/user/detials",method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> Detials(){
        Map<String ,Object> map=new HashMap<String, Object>();
        UserDetails userDetails= (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();//获得当前用户的信息
        user user= mainPageService.getUserByName(userDetails.getUsername());
        if(user==null){
            logger.info("没有找到用户信息");
            map.put("success",false);
        }else{
            map.put("success",true);
            String info=JSONArray.fromObject(user).toString();
            logger.info("找到用户信息为"+info);
            map.put("list",info);
        }
        return map;
    }
}
