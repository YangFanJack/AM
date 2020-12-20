package jack.service.impl;

import jack.dao.NoticeDao;
import jack.pojo.Activity;
import jack.pojo.Notice;
import jack.pojo.PageBean;
import jack.service.NoticeService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NoticeServiceImpl implements NoticeService {
    private NoticeDao noticeDao;

    public void setNoticeDao(NoticeDao noticeDao) {
        this.noticeDao = noticeDao;
    }

    @Override
    public PageBean<Notice> showAllNoticeByPage(int currentPage, int pageSize, String searchString) {
        PageBean<Notice> noticePageBean = new PageBean<>();
        noticePageBean.setCurrentPage(currentPage);
        noticePageBean.setPageSize(pageSize);

        Map<String,Object> map1 = new HashMap<>();
        if(searchString!=null){
            searchString = "%"+searchString+"%";
        }

        //调用dao查询总记录数
        map1.put("noticeTitle",searchString);
        int totalCount = noticeDao.selectNoticesCount(map1);
        noticePageBean.setTotalCount(totalCount);

        int start = (currentPage-1)*pageSize;
        Map<String,Object> map2 = new HashMap<>();
        map2.put("start",start);
        map2.put("pageSize",pageSize);
        map2.put("noticeTitle",searchString);
        List<Notice> notices = noticeDao.selectNotices(map2);
        noticePageBean.setList(notices);

        //设置总页码
        int totalPage = (totalCount % pageSize ) == 0 ? totalCount/pageSize : (totalCount/pageSize)+1;
        noticePageBean.setTotalPage(totalPage);
        return noticePageBean;
    }

    @Override
    public PageBean<Notice> showManNoticeByPage(int currentPage, int pageSize, String searchString, int managerId) {
        PageBean<Notice> noticePageBean = new PageBean<>();
        noticePageBean.setCurrentPage(currentPage);
        noticePageBean.setPageSize(pageSize);

        Map<String,Object> map1 = new HashMap<>();
        if(searchString!=null){
            searchString = "%"+searchString+"%";
        }

        //调用dao查询总记录数
        map1.put("noticeTitle",searchString);
        map1.put("managerId",managerId);
        int totalCount = noticeDao.selectNoticesCount(map1);
        noticePageBean.setTotalCount(totalCount);

        int start = (currentPage-1)*pageSize;
        Map<String,Object> map2 = new HashMap<>();
        map2.put("start",start);
        map2.put("pageSize",pageSize);
        map2.put("noticeTitle",searchString);
        map2.put("managerId",managerId);
        List<Notice> notices = noticeDao.selectNotices(map2);
        noticePageBean.setList(notices);

        //设置总页码
        int totalPage = (totalCount % pageSize ) == 0 ? totalCount/pageSize : (totalCount/pageSize)+1;
        noticePageBean.setTotalPage(totalPage);
        return noticePageBean;
    }

    @Override
    public boolean deleteNotice(int id) {
        try{
            int i = noticeDao.deleteNotice(id);
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
    public boolean postNotice(Notice notice) {
        try{
            int i = noticeDao.insertNotice(notice);
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
    public Notice showDetailNotice(Notice notice) {
        Map<String,Object> map = new HashMap<>();
        map.put("id",notice.getId());
        Notice not = noticeDao.selectNotice(map);
        return not;
    }
}
