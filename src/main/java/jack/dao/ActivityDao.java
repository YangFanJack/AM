package jack.dao;

import jack.pojo.Activity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

public interface ActivityDao {
    //查找该活动所属的组织类型
    @Select("Select orgCategory from`activity` WHERE id=#{id}")
    public int selectOrgCategory(@Param("id") int id);
    //普通查找某个活动
    @Select("SELECT * FROM activity WHERE id = ${id};")
    public Activity selectActivity(@Param("id") int id);
    //查找某个团支书申请的活动
    public Activity selectActivity1(Map<String,Object> parameters);
    //查找某个学院发布的活动
    public Activity selectActivity2(Map<String,Object> parameters);
    //新增一个新的活动（团支书申请，管理员发布）
    public int insertActivity(Activity activity);
    //更新活动信息（修改活动，审核通过活动）
    public int updateActivity(Activity activity);
    //查找很多活动（团支书申请的所有活动&主页筛选的所有活动）
    public List<Activity> selectActivities1(Map<String,Object> parameters);
    //查找很多活动（管理员活动管理:本学院所有班级发的&本学院自己发的）
    public List<Activity> selectActivities2a(Map<String,Object> parameters);
    public List<Activity> selectActivities2b(Map<String,Object> parameters);
    //活动审核失败
    public int deleteActivity(@Param("id") int id);
    //分页总条数
    public int selectActivitiesCount1(Map<String,Object> parameters);
    public int selectActivitiesCount2a(Map<String,Object> parameters);
    public int selectActivitiesCount2b(Map<String,Object> parameters);
    //报名人数加一
    @Update("UPDATE `activity` SET enrollNum=enrollNum+1 where id=#{actId}")
    public void enrollAddOne(@Param("actId") int actId);
    //签到人数加一
    @Update("UPDATE `activity` SET signNum=signNum+1 where id=#{actId}")
    public void signAddOne(@Param("actId") int actId);
}
