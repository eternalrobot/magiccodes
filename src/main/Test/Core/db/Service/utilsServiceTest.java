package Core.db.Service;

import Core.Config.DataBaseConfig;
import Core.Config.WebSocketStompConfig;
import Core.data.movie;
import Core.data.project;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={DataBaseConfig.class,WebSocketStompConfig.class})
public class utilsServiceTest {
    @Autowired
    utilsService utilsService;

    @Test
    public void savemovie() {
        System.out.println(utilsService.findmovieByNameMOHU("末日").get(0));
    }

    @Test
    public void saveprojectdata() {
        utilsService.saveprojectdata(new project("搜集象棋数据",new Date(),null,"搜集很多很多的数据"));
    }
}