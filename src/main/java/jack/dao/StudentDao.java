package jack.dao;

import jack.pojo.Student;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

public interface StudentDao {
    //查找某个学生
    public Student selectStudent(Map<String,Object> parameters);
    //插入新学生
    public int insertStudent(Student student);
    //更新某个学生的信息（密码）(注册的通知生产学号)
    public int updateStudent(Student student);
    //查找很多学生（按照班级&按照活动）
    public List<Student> selectStudents(Map<String,Object> parameters);
    //分页总条数
    public int selectStudentsCount(Map<String,Object> parameters);
    //参加活动积分的累加
    @Update("UPDATE `student` SET points=points+1 where id=#{stuId}")
    public int pointsAdd(@Param("points") int points,@Param("stuId") int stuId);
    //参加活动次数的累加
    @Update("UPDATE `student` SET joinCounts=joinCounts+1 where id=#{stuId}")
    public int joinCountsAddOne(@Param("stuId") int stuId);
}
