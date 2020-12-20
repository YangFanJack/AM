package jack.dao;

import jack.pojo.Stu_act;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface Stu_actDao {
    //新增报名对象
    public int insertStu_act(Stu_act stu_act);
    //查找某个报名对象（某个学生的报名详情）
    public Stu_act selectStu_act(Map<String,Object> parameters);
    //查找多个报名对象（学生所有的报名对象）
    public List<Stu_act> selectStu_acts(Map<String,Object> parameters);
    //更新报名对象（签到）(留言)
    public int updateStu_act(Map<String,Object> parameters);
    //分页总条数
    public int selectStu_actsCount(Map<String,Object> parameters);
    //活动逾期自动更新报名状态为逾期
    public int autoUpdateOnTime();
    //判断是否签到
    @Select("select * from stu_act where id=${stu_actId} and isSign = 2")
    public Stu_act isSign(int stu_actId);
}
