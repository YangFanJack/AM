package jack.dao;

import jack.pojo.Stu_act;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-dao.xml" })
public class Stu_actDaoTest {
    //新增报名对象
    @Test
    public void insertStu_act(Stu_act stu_act){

    }
    //查找某个报名对象（某个学生的报名详情）
    @Test
    public void selectStu_act(Map<String,Object> parameters){

    }
    //查找多个报名对象（学生所有的报名对象）
    @Test
    public void selectStu_acts(Map<String,Object> parameters){

    }
    //更新报名对象（签到）(留言)
    @Test
    public void updateStu_act(Map<String,Object> parameters){

    }
    //分页总条数
    @Test
    public void selectStu_actsCount(Map<String,Object> parameters){

    }
    //活动逾期自动更新报名状态为逾期
    @Test
    public void autoUpdateOnTime(){

    }
}
