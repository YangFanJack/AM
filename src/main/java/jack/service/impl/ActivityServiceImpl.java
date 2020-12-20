package jack.service.impl;

import com.sun.org.apache.xpath.internal.operations.Mod;
import jack.dao.ActivityDao;
import jack.dao.ManagerDao;
import jack.dao.StudentDao;
import jack.pojo.Activity;
import jack.pojo.Manager;
import jack.pojo.PageBean;
import jack.pojo.Student;
import jack.service.ActivityService;
import org.springframework.ui.Model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivityServiceImpl implements ActivityService {
    private ActivityDao activityDao;
    private StudentDao studentDao;
    private ManagerDao managerDao;

    public void setActivityDao(ActivityDao activityDao) {
        this.activityDao = activityDao;
    }

    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public void setManagerDao(ManagerDao managerDao) {
        this.managerDao = managerDao;
    }

    //主页-活动详情
    @Override
    public Activity showDetailActivity(Activity act) {
        Map<String,Object> map = new HashMap<>();
        map.put("id",act.getId());
        int orgCategory = activityDao.selectOrgCategory(act.getId());
        Activity activity;
        if(orgCategory==1){//判断是团支书
            activity = activityDao.selectActivity1(map);
        }
        else{//判断是学院管理员
            activity = activityDao.selectActivity2(map);
        }
        return activity;
    }

    @Override
    public Activity showMyDetailActivity(Activity act) {
        Map<String,Object> map = new HashMap<>();
        map.put("id",act.getId());
        Activity activity = activityDao.selectActivity1(map);
        return activity;
    }

    @Override
    public boolean applyActivity(Activity act) {
        try{
            int i = activityDao.insertActivity(act);
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
    public boolean postActivity(Activity act) {
//        try {
            int i = activityDao.insertActivity(act);
//            if(i==1){
//                return true;
//            }
//            else{
//                return false;
//            }
//        }
//        catch (Exception e){
//            return false;
//        }
        return true;
    }

    @Override
    public boolean changeActivity(Activity act) {
        try{
            int i = activityDao.updateActivity(act);
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
    public boolean passActivity(Activity activity) {
        try{
            int i = activityDao.updateActivity(activity);
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
    public PageBean<Activity> showMyActivityByPage(int currentPage, int pageSize, int tuanId, String searchString) {
        PageBean<Activity> actPageBean = new PageBean<>();
        actPageBean.setCurrentPage(currentPage);
        actPageBean.setPageSize(pageSize);

        Map<String,Object> map1 = new HashMap<>();
        if(searchString!=null){
            searchString = "%"+searchString+"%";
        }

        //调用dao查询总记录数
        map1.put("actTitle",searchString);
        map1.put("tuanId",tuanId);
        int totalCount = activityDao.selectActivitiesCount1(map1);
        actPageBean.setTotalCount(totalCount);

        int start = (currentPage-1)*pageSize;
        Map<String,Object> map2 = new HashMap<>();
        map2.put("start",start);
        map2.put("pageSize",pageSize);
        map2.put("actTitle",searchString);
        map2.put("tuanId",tuanId);
        List<Activity> activities = activityDao.selectActivities1(map2);
        actPageBean.setList(activities);

        //设置总页码
        int totalPage = (totalCount % pageSize ) == 0 ? totalCount/pageSize : (totalCount/pageSize)+1;
        actPageBean.setTotalPage(totalPage);
        return actPageBean;
    }

    @Override
    public PageBean<Activity> showPassActivityByPage(int category, int currentPage, int pageSize, String searchString, int managerId, int verifyStatus) {
        PageBean<Activity> actPageBean = new PageBean<>();
        actPageBean.setCurrentPage(currentPage);
        actPageBean.setPageSize(pageSize);

        Map<String,Object> map1 = new HashMap<>();
        if(searchString!=null){
            searchString = "%"+searchString+"%";
        }

        //调用dao查询总记录数
        map1.put("actTitle",searchString);
        map1.put("verifyStatus",verifyStatus);
        map1.put("managerId",managerId);
        map1.put("category",category);
        int totalCount=0;
        if(category==3 || category==4){
            totalCount = activityDao.selectActivitiesCount2a(map1);
        }
        if(category==1 || category==2){
            totalCount = activityDao.selectActivitiesCount2b(map1);
        }
        actPageBean.setTotalCount(totalCount);

        int start = (currentPage-1)*pageSize;
        Map<String,Object> map2 = new HashMap<>();
        map2.put("start",start);
        map2.put("pageSize",pageSize);
        map2.put("actTitle",searchString);
        map2.put("verifyStatus",verifyStatus);
        map2.put("managerId",managerId);
        map2.put("category",category);
        List<Activity> activities = null;
        if(category==3 || category==4){
            activities = activityDao.selectActivities2a(map2);
        }
        if(category==1 || category==2){
            activities = activityDao.selectActivities2b(map2);
        }
        actPageBean.setList(activities);

        //设置总页码
        int totalPage = (totalCount % pageSize ) == 0 ? totalCount/pageSize : (totalCount/pageSize)+1;
        actPageBean.setTotalPage(totalPage);
        return actPageBean;
    }

    @Override
    public PageBean<Activity> showChooseActivityByPage(int currentPage, int pageSize, String searchString, int verifyStatus, String collegeName, String _class) {
        //通过collegeName和_class查找managerId和tuanId
        int managerId = 0;
        if(collegeName != null && !collegeName.equals("")){
            HashMap<String, Object> m1 = new HashMap<>();
            m1.put("collegeName",collegeName);
            Manager manager = managerDao.selectManager(m1);
            if(manager != null){
                managerId = manager.getId();
            }
        }

        int tuanId = 0;
        if(_class != null && !_class.equals("")) {
            HashMap<String, Object> m2 = new HashMap<>();
            m2.put("class",_class);
            m2.put("isTuan",2);
            Student student = studentDao.selectStudent(m2);
            if(student!=null){
                tuanId = student.getId();
            }
            else tuanId = -1;
        }


        PageBean<Activity> actPageBean = new PageBean<>();
        actPageBean.setCurrentPage(currentPage);
        actPageBean.setPageSize(pageSize);

        Map<String,Object> map1 = new HashMap<>();
        if(searchString!=null){
            searchString = "%"+searchString+"%";
        }

        //调用dao查询总记录数
        map1.put("actTitle",searchString);
        map1.put("verifyStatus",verifyStatus);
        map1.put("managerId",managerId);
        map1.put("tuanId",tuanId);
        int totalCount = activityDao.selectActivitiesCount1(map1);
        actPageBean.setTotalCount(totalCount);

        int start = (currentPage-1)*pageSize;
        Map<String,Object> map2 = new HashMap<>();
        map2.put("start",start);
        map2.put("pageSize",pageSize);
        map2.put("actTitle",searchString);
        map2.put("verifyStatus",verifyStatus);
        map2.put("managerId",managerId);
        map2.put("tuanId",tuanId);
        List<Activity> activities = activityDao.selectActivities1(map2);
        actPageBean.setList(activities);

        //设置总页码
        int totalPage = (totalCount % pageSize ) == 0 ? totalCount/pageSize : (totalCount/pageSize)+1;
        actPageBean.setTotalPage(totalPage);
        return actPageBean;
    }


    @Override
    public boolean outActivity(int actId) {
        try{
            int i = activityDao.deleteActivity(actId);
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
