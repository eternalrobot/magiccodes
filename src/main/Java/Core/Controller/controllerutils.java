package Core.Controller;

import Core.data.*;
import Core.db.Service.securityService;
import Core.db.Service.utilsService;
import net.sf.json.JSONArray;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class controllerutils {
    @Autowired
    utilsService utilsService;
    @Autowired
    securityService securityService;

    static Logger logger=Logger.getLogger(controllerutils.class);

    @RequestMapping(value = "/utils/getmoviename",method = POST)
    @ResponseBody
    public Map<String,Object> getmoviebyname(HttpServletRequest request){
        UserDetails userDetails= (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();//获得当前用户的信息
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        logger.info("当前用户权限："+authorities);
        Map<String,String[]> requestParameterMap=request.getParameterMap();
        String moviename=requestParameterMap.get("moviename")[0];
        List<movie> movieList=utilsService.findmovieByNameMOHU(moviename);
        Map<String,Object> map=new HashMap<String, Object>();
        if(movieList!=null){
            JSONArray jsonArray=new JSONArray();
            for(movie movie:movieList){
                if(securityService.isHasRole(authorities,movie.getPower())){//判断是不是含有权限
                    jsonArray.add(movie);
                }
            }
            map.put("result",jsonArray.toString());
            map.put("success",true);
        }else{
            map.put("success",false);
        }
        return map;
    }
}
