package jack.dao;

import jack.pojo.Notice;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-dao.xml" })
public class NoticeDaoTest {
    //查找很多通知
    @Test
    public void selectNotices(Map<String,Object> parameters){

    }
    //查找某个通知
    @Test
    public void selectNotice(Map<String,Object> parameters){

    }
    //删除通知
    @Test
    public void deleteNotice(int id){

    }
    //新增通知
    @Test
    public void insertNotice(Notice notice){

    }
    //分页总条数
    @Test
    public void selectNoticesCount(Map<String,Object> parameters){

    }
}
