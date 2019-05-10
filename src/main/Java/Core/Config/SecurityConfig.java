package Core.Config;

import Core.db.Dao.userDao;
import Core.db.Service.userLogService;
import Utils.SpringSecurityAjaxForLog.AjaxAuthFailHandlerForLog;
import Utils.SpringSecurityAjaxForLog.AjaxAuthSuccessHandlerForLog;
import Utils.SpringSecurityAjaxForLog.UnauthorizedEntryPointForLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

import javax.sql.DataSource;

@Configuration
@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    userDao userDao;

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(new userLogService(userDao));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                    .exceptionHandling().authenticationEntryPoint(new UnauthorizedEntryPointForLog())//出错处理的方法
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .successHandler(new AjaxAuthSuccessHandlerForLog())//成功后调用的方法
                    .failureHandler(new AjaxAuthFailHandlerForLog())//失败后调用的方法
                .and()
                    .logout()
                    .logoutSuccessUrl("/home")
                .and()
                    .rememberMe()
                    .tokenValiditySeconds(60*60*24*7)
                    .key("Love")
                .and()
                    .authorizeRequests()
                    .antMatchers("/myhomepage").authenticated()
                    .antMatchers("/codesearch").authenticated()
                    .antMatchers("/descriptioncode").authenticated()
                    .antMatchers("/home/user/detials").authenticated()
                    .antMatchers("/utils/getchessdata").authenticated()
                    .antMatchers("/utils/getmoviename").authenticated()
                    .anyRequest().permitAll()
                .and()
                    .csrf().disable();
    }
}