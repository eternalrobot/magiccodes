package Core.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class descriptioncodePage {
    @RequestMapping(value = "/descriptioncode",method = RequestMethod.GET)
    public String descriptioncode(){
        return "/descriptioncodePage";
    }
}
