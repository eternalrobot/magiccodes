package Core.db.Service;

import Core.Config.DataBaseConfig;
import Core.data.user;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={DataBaseConfig.class})
public class registerPageServiceTest {

    @Autowired
    registerPageService registerPageService;

    @Test
    public void add_new_user() {
        registerPageService.add_new_user(new user("eternal","dyh179085","supermanager",true,"houd321@163.com"));
    }
}