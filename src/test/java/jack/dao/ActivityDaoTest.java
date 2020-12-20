package jack.dao;

import jack.pojo.Activity;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-dao.xml" })
public class ActivityDaoTest {
    //查找该活动所属的组织类型
    @Test
    public void selectOrgCategory(int id){

    }
    //普通查找某个活动
    @Test
    public void selectActivity(int id){

    }
    //查找某个团支书申请的活动
    @Test
    public void selectActivity1(Map<String,Object> parameters){

    }
    //查找某个学院发布的活动
    @Test
    public void selectActivity2(Map<String,Object> parameters){

    }
    //新增一个新的活动（团支书申请，管理员发布）
    @Test
    public void insertActivity(Activity activity){

    }
    //更新活动信息（修改活动，审核通过活动）
    @Test
    public void updateActivity(Activity activity){

    }
    //查找很多活动（团支书申请的所有活动，管理员活动管理）
    @Test
    public void selectActivities(Map<String,Object> parameters){

    }
    //活动审核失败
    @Test
    public void deleteActivity(int id){

    }
    //分页总条数
    @Test
    public void selectActivitiesCount(Map<String,Object> parameters){

    }
    //报名人数加一
    @Test
    public void enrollAddOne(int actId){

    }
    //签到人数加一
    @Test
    public void signAddOne(int actId){

    }
}
