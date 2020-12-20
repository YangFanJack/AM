package jack.service;

import jack.pojo.PageBean;
import jack.pojo.Stu_act;
import jack.pojo.Student;

import java.util.List;

public interface Stu_actService {
    //报名
    public boolean enroll(Stu_act stu_act);
    //展示我所有的报名
    public PageBean<Stu_act> showMyAllEnrollByPage(int currentPage, int pageSize, String searchString, int sId);
    //展示我某个详细的报名
    public Stu_act showMyDetailEnroll(Stu_act stu_act);
    //签到
    public boolean doSign(int stu_actId);
    //留言
    public boolean doWords(Stu_act stu_act);
    //根据活动id和学生id查找是否已经报名
    public boolean isEnroll(Stu_act stu_act);
    //查询一个活动的所有学生
    public List<Stu_act> findStuInAct(int actId);
    //判断该报名是否已经签到
    public boolean isSign(int stu_actId);
}
