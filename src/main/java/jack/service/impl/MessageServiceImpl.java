package jack.service.impl;

import jack.dao.MessageDao;
import jack.pojo.Activity;
import jack.pojo.Message;
import jack.pojo.PageBean;
import jack.service.MessageService;

import javax.mail.search.MessageIDTerm;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageServiceImpl implements MessageService {
    private MessageDao messageDao;

    public void setMessageDao(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    @Override
    public PageBean<Message> showAllMessageByPage(int currentPage, int pageSize, String searchString, int sId) {
        PageBean<Message> mesPageBean = new PageBean<>();
        mesPageBean.setCurrentPage(currentPage);
        mesPageBean.setPageSize(pageSize);

        Map<String,Object> map1 = new HashMap<>();
        if(searchString!=null){
            searchString = "%"+searchString+"%";
        }

        //调用dao查询总记录数
        map1.put("stuId",sId);
        map1.put("mesContent",searchString);
        int totalCount = messageDao.selectMessagesCount(map1);
        mesPageBean.setTotalCount(totalCount);

        int start = (currentPage-1)*pageSize;
        Map<String,Object> map2 = new HashMap<>();
        map2.put("start",start);
        map2.put("pageSize",pageSize);
        map2.put("stuId",sId);
        map2.put("mesContent",searchString);
        List<Message> messages = messageDao.selectMessages(map2);
        mesPageBean.setList(messages);

        //设置总页码
        int totalPage = (totalCount % pageSize ) == 0 ? totalCount/pageSize : (totalCount/pageSize)+1;
        mesPageBean.setTotalPage(totalPage);
        return mesPageBean;
    }

    @Override
    public boolean doRead(int messageId) {
        Message message = new Message();
        message.setId(messageId);
        message.setIsRead(2);
        try{
            int i = messageDao.updateMessage(message);
            if(i==1){
                return true;
            }
            else{
                return false;
            }
        }
        catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean deleteMessage(int messageId) {
        int id = messageId;
        try{
            int i = messageDao.deleteMessage(id);
            if(i==1){
                return true;
            }
            else{
                return false;
            }
        }
        catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean insertMessage(Message message) {
        try{
            int i = messageDao.insertMessage(message);
            if(i==1){
                return true;
            }
            else{
                return false;
            }
        }
        catch (Exception e){
            return false;
        }
    }
}
