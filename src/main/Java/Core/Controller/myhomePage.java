package Core.Controller;

import Core.data.user;
import Core.db.Service.mainPageService;
import Utils.fileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class myhomePage {
    @Autowired
    mainPageService mainPageService;

    static Logger logger=Logger.getLogger(myhomePage.class);

    @ModelAttribute
    public void addModel(Model model){
        UserDetails userDetails= (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();//获得当前用户的信息
        logger.info("当前用户："+userDetails.getUsername()+"，登陆其主页");
        user user= mainPageService.getUserByName(userDetails.getUsername());
        model.addAttribute("username",user.getUsername());
        model.addAttribute("ufc",user.getUsername().charAt(0));
    }

    @RequestMapping(value = "/myhomepage",method = RequestMethod.GET)
    public String myhomepage(Model model){
        return "/myhomePage";
    }
}
