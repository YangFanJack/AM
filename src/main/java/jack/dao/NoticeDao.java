package jack.dao;

import jack.pojo.Notice;

import java.util.List;
import java.util.Map;

public interface NoticeDao {
    //查找很多通知
    public List<Notice> selectNotices(Map<String,Object> parameters);
    //查找某个通知
    public Notice selectNotice(Map<String,Object> parameters);
    //删除通知
    public int deleteNotice(int id);
    //新增通知
    public int insertNotice(Notice notice);
    //分页总条数
    public int selectNoticesCount(Map<String,Object> parameters);
}
