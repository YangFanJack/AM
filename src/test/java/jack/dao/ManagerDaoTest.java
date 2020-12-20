package jack.dao;

import jack.pojo.Manager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-dao.xml" })
public class ManagerDaoTest {
    //查找某个管理员
    @Test
    public void selectManager(Map<String,Object> parameters){

    }
}
