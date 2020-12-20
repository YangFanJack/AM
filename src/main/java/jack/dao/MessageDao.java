package jack.dao;

import jack.pojo.Message;

import java.util.List;
import java.util.Map;

public interface MessageDao {
    //查找所有消息
    public List<Message> selectMessages(Map<String,Object> parameters);
    //更新消息（已读）
    public int updateMessage(Message message);
    //删除消息
    public int deleteMessage(int id);
    //分页总条数
    public int selectMessagesCount(Map<String,Object> parameters);
    //新增一条信息
    public int insertMessage(Message message);
}
