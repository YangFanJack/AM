package jack.dao;

import jack.pojo.Message;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-dao.xml" })
public class MessageDaoTest extends TestCase {

    // 注入Dao实现类依赖
    @Autowired
    private MessageDao messageDao;

    //查找所有消息
    @Test
    public void selectMessages(Map<String,Object> parameters){
    }
    //更新消息（已读）
    @Test
    public void updateMessage(Message message){

    }
    //删除消息
    @Test
    public void deleteMessage(int id){

    }
    //分页总条数
    @Test
    public void selectMessagesCount(Map<String,Object> parameters){

    }
    //新增一条信息
    @Test
    public void insertMessage(Message message){

    }
}