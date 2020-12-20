package jack.service;

import jack.pojo.Message;
import jack.pojo.PageBean;

import java.util.List;

public interface MessageService {
    //展示学生所有的消息
    public PageBean<Message> showAllMessageByPage(int currentPage, int pageSize, String searchString, int sId);
    //确认已读
    public boolean doRead(int messageId);
    //删除消息
    public boolean deleteMessage(int messageId);
    //新增一条消息
    public boolean insertMessage(Message message);
}
