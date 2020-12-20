package jack.dao;

import jack.pojo.Student;
import org.apache.ibatis.annotations.Update;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-dao.xml" })
public class StudentDaoTest {
    //查找某个学生
    @Test
    public void selectStudent(Map<String,Object> parameters){

    }
    //插入新学生
    @Test
    public void insertStudent(Student student){

    }
    //更新某个学生的信息（密码）(注册的通知生产学号)
    @Test
    public void updateStudent(Student student){

    }
    //查找很多学生（按照班级）
    @Test
    public void selectStudents(Map<String,Object> parameters){

    }
    //分页总条数
    @Test
    public void selectStudentsCount(Map<String,Object> parameters){

    }
    //参加活动积分的累加
    @Test
    public void pointsAdd(int stuId,int points){

    }
    //参加活动次数的累加
    @Test
    public void joinCountsAddOne(int stuId){

    }
}
