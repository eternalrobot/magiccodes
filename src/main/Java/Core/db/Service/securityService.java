package Core.db.Service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Iterator;

@Service
public class securityService {
    public boolean isHasRole(Collection<? extends GrantedAuthority> authorities,String role){
        Iterator iterator =authorities.iterator();
        while(iterator.hasNext()){
            SimpleGrantedAuthority simpleGrantedAuthority= (SimpleGrantedAuthority) iterator.next();//获得每一个权限名
            if(simpleGrantedAuthority.getAuthority().equals(role)){//如果权限相同就返回true
                return true;
            }
        }
        return false;
    }
}
