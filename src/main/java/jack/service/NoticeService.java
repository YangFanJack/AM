package jack.service;

import jack.pojo.Notice;
import jack.pojo.PageBean;

import java.util.List;

public interface NoticeService {
    //展示所有通知
    public PageBean<Notice> showAllNoticeByPage(int currentPage, int pageSize, String searchString);
    //显示某学院的所有通知
    public PageBean<Notice> showManNoticeByPage(int currentPage, int pageSize, String searchString, int managerId);
    //删除通知
    public boolean deleteNotice(int id);
    //发布通知
    public boolean postNotice(Notice notice);
    //展示详细通知
    public Notice showDetailNotice(Notice notice);
}
