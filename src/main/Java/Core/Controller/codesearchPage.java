package Core.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class codesearchPage {
//    @PreAuthorize()//必须具有什么权限才能调用某些方法

    @RequestMapping(value = "/codesearch",method = RequestMethod.GET)
    public String codesearch(){
        return "/codesearchPage";
    }


}
