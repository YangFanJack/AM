package jack.service;

import jack.pojo.Manager;
import jack.pojo.PageBean;
import jack.pojo.Student;
import sun.security.util.Password;

import java.util.List;

public interface StudentService {
    //学生登录
    public boolean studentLogin(Student student);
    //显示学生信息
    public Student showMyInfo(Student student);
    //学生注册
    public Student studentRegister(Student student);
    //分页显示某个班级学生
    public PageBean<Student> showMyClassmatesByPage(int currentPage, int pageSize, int tuanId);
    //分页显示指定班级学生
    public PageBean<Student> showClassmatesByPage(int currentPage, int pageSize, String _class);
    //获得某个学生的信息
    public Student getStudentInfo(Student student);
    //查询某个学院的所有学生（不分页）
    public List<Student> findStudents(int managerId);
    //学生修改密码
    public boolean changePassword(Student student);
    //查询某班有没有团支书
    public boolean findTuanInClass(String _class, int isTuan);
}
