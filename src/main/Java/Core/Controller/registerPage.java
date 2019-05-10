package Core.Controller;

import Core.data.user;
import Core.db.Service.registerPageService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class registerPage {
    @Autowired
    registerPageService registerPageService;

    static Logger logger=Logger.getLogger(registerPage.class);

    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String register(){
        logger.info("有用户进入注册页面");
        return "/registerPage";
    }

    @RequestMapping(value = "/register/submit",method = POST)
    @ResponseBody
    public Map<String,Object> registersubmit(HttpServletRequest request){
        Map<String,String[]> requestParameterMap=request.getParameterMap();
        Map<String,Object> map=new HashMap<String, Object>();
        if(!requestParameterMap.get("token")[0].equals("123loveAndlove123")){
            map.put("success",false);
            map.put("token",false);
            return map;
        }
        String username=requestParameterMap.get("username")[0];
        String email=requestParameterMap.get("email")[0];
        String password=requestParameterMap.get("password")[0];
        if(registerPageService.add_new_user(new user(username,password,"normaluser",true,email))){
            map.put("success",true);
        }else{
            map.put("success",false);
        }
        return map;
    }

    @RequestMapping(value = "/register/checkthesame",method = POST)
    @ResponseBody
    public Map<String,Object> checkusername(HttpServletRequest request){
        Map<String,String[]> requestParameterMap=request.getParameterMap();
        Map<String,Object> map=new HashMap<String, Object>();
        String username=requestParameterMap.get("username")[0];
        String email=requestParameterMap.get("email")[0];
        logger.info("判断相同："+username+","+email);
        if(!email.equals("")&&email!=null&&registerPageService.is_has_same_email(email)==true){
            logger.warn("有相同email："+email);
            map.put("email",true);
        }
        else{
            map.put("email",false);
        }
        if(!username.equals("")&&username!=null&&registerPageService.is_has_same_username(username)==true){
            logger.warn("有相同username："+username);
            map.put("username",true);
        }else{
            map.put("username",false);
        }
        return map;
    }
}
