package Core.db.Service;

import Core.data.user;
import Core.db.Dao.userDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

public class userLogService implements UserDetailsService {

    private final userDao userDao;

    public userLogService(Core.db.Dao.userDao userDao) {
        this.userDao = userDao;
    }

    static Logger logger=Logger.getLogger(userLogService.class);

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        logger.info("开始识别："+s);
        user user=userDao.findByString(s);
        if(user!=null){
            logger.info("找到用户");
            List<GrantedAuthority> authorities=new ArrayList<GrantedAuthority>();
            if(user.getRole().equals("supermanager")){
                authorities.add(new SimpleGrantedAuthority(user.getRole()));
                authorities.add(new SimpleGrantedAuthority("normaluser"));
            }else{
                authorities.add(new SimpleGrantedAuthority(user.getRole()));
            }
            return new User(user.getUsername(),user.getPassword(),authorities);
        }
        throw new UsernameNotFoundException("User '"+s+"' not found.");
    }
}
