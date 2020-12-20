package jack.service.impl;

import jack.dao.ActivityDao;
import jack.dao.Stu_actDao;
import jack.dao.StudentDao;
import jack.pojo.*;
import jack.service.Stu_actService;

import javax.sound.midi.Soundbank;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Stu_actServiceImpl implements Stu_actService {
    private Stu_actDao stu_actDao;
    private ActivityDao activityDao;
    private StudentDao studentDao;

    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public void setActivityDao(ActivityDao activityDao) {
        this.activityDao = activityDao;
    }

    public void setStu_actDao(Stu_actDao stu_actDao) {
        this.stu_actDao = stu_actDao;
    }

    @Override
    public boolean enroll(Stu_act stu_act) {
        try {
            int i = stu_actDao.insertStu_act(stu_act);
            if(i==1){
                //报名成功，报名的活动的报名数加一
                activityDao.enrollAddOne(stu_act.getActId().getId());
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
    public PageBean<Stu_act> showMyAllEnrollByPage(int currentPage, int pageSize, String searchString, int sId) {
        PageBean<Stu_act> stu_actPageBean = new PageBean<>();
        stu_actPageBean.setCurrentPage(currentPage);
        stu_actPageBean.setPageSize(pageSize);

        Map<String,Object> map1 = new HashMap<>();
        if(searchString!=null){
            searchString = "%"+searchString+"%";
        }

        //调用dao查询总记录数
        map1.put("stuId",sId);
        map1.put("actTitle",searchString);
        int totalCount = stu_actDao.selectStu_actsCount(map1);
        stu_actPageBean.setTotalCount(totalCount);

        int start = (currentPage-1)*pageSize;
        Map<String,Object> map2 = new HashMap<>();
        map2.put("start",start);
        map2.put("pageSize",pageSize);
        map2.put("stuId",sId);
        map2.put("actTitle",searchString);
        List<Stu_act> sa = stu_actDao.selectStu_acts(map2);
        for(Stu_act m : sa){
            Map<String,Object> m1 = new HashMap<>();
            m1.put("id",m.getActId().getId());
            Map<String,Object> m2 = new HashMap<>();
            m2.put("id",m.getStuId().getId());
            int orgCategory = m.getActId().getOrgCategory();
            Activity act = null;
            if(orgCategory==1){//判断是团支书
                act = activityDao.selectActivity1(m1);
            }
            else{//判断是学院管理员
                act = activityDao.selectActivity2(m1);
            }
            Student stu = studentDao.selectStudent(m2);
            m.setActId(act);
            m.setStuId(stu);
        }
        stu_actPageBean.setList(sa);

        //设置总页码
        int totalPage = (totalCount % pageSize ) == 0 ? totalCount/pageSize : (totalCount/pageSize)+1;
        stu_actPageBean.setTotalPage(totalPage);
        return stu_actPageBean;
    }

    @Override
    public Stu_act showMyDetailEnroll(Stu_act stu_act) {
        Map<String,Object> map = new HashMap<>();
        map.put("id",stu_act.getId());
        Stu_act stu_act1 = stu_actDao.selectStu_act(map);
        int actId = stu_act1.getActId().getId();
        Map<String,Object> m1 = new HashMap<>();
        m1.put("id",actId);
        int stuId = stu_act1.getStuId().getId();
        Map<String,Object> m2 = new HashMap<>();
        m2.put("id",stuId);
        int orgCategory = stu_act1.getActId().getOrgCategory();
        Activity act = null;
        if(orgCategory==1){//判断是团支书
            act = activityDao.selectActivity1(m1);
        }
        else{//判断是学院管理员
            act = activityDao.selectActivity2(m1);
        }
        Student stu = studentDao.selectStudent(m2);
        stu_act1.setActId(act);
        stu_act1.setStuId(stu);
        return stu_act1;
    }

    @Override
    public boolean doSign(int stu_actId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id",stu_actId);
        map.put("isSign",2);
        map.put("joinStatus",2);
        try{
            int i = stu_actDao.updateStu_act(map);
            if(i==1){
                //签到成功，签到的活动的签到数加一
                Map<String,Object> m = new HashMap<>();
                m.put("id",stu_actId);
                Stu_act stu_act = stu_actDao.selectStu_act(m);
                activityDao.signAddOne(stu_act.getActId().getId());

                //签到成功，学生的报名分数加上活动分数&活动参加次数加一
                Activity a = activityDao.selectActivity(stu_act.getActId().getId());

                studentDao.joinCountsAddOne(stu_act.getStuId().getId());
                System.out.println("*********************************************");
                System.out.println(a.getGetPoints());
                System.out.println(stu_act.getStuId().getId());
                studentDao.pointsAdd(a.getGetPoints(), stu_act.getStuId().getId());
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
    public boolean doWords(Stu_act stu_act) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id",stu_act.getId());
        map.put("wordsTime",stu_act.getWordsTime());
        map.put("wordsContent",stu_act.getWordsContent());
        try{
            int i = stu_actDao.updateStu_act(map);
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
    public boolean isEnroll(Stu_act stu_act) {
        Map<String,Object> map = new HashMap<>();
        map.put("stuId",stu_act.getStuId().getId());
        map.put("actId",stu_act.getActId().getId());
        Stu_act stu_act1 = stu_actDao.selectStu_act(map);
        if(stu_act1 != null){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public List<Stu_act> findStuInAct(int actId) {
        Map<String,Object> map = new HashMap<>();
        map.put("actId",actId);

        List<Stu_act> stu_acts = stu_actDao.selectStu_acts(map);
        for(Stu_act m : stu_acts){
            Map<String,Object> m1 = new HashMap<>();
            m1.put("id",m.getActId().getId());
            Map<String,Object> m2 = new HashMap<>();
            m2.put("id",m.getStuId().getId());
            int orgCategory = m.getActId().getOrgCategory();
            Activity act = null;
            System.out.println(m1);
            if(orgCategory==1){//判断是团支书
                act = activityDao.selectActivity1(m1);
            }
            else{//判断是学院管理员
                act = activityDao.selectActivity2(m1);
            }
            Student stu = studentDao.selectStudent(m2);
            m.setActId(act);
            m.setStuId(stu);
        }
        return stu_acts;
    }

    @Override
    public boolean isSign(int stu_actId) {
        Stu_act stu_act = stu_actDao.isSign(stu_actId);
        if(stu_act != null){
            return true;
        }
        else{
            return false;
        }
    }
}
