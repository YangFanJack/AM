package jack.service.impl;

import jack.dao.StudentDao;
import jack.pojo.Activity;
import jack.pojo.PageBean;
import jack.pojo.Student;
import jack.service.StudentService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentServiceImpl implements StudentService {
    private StudentDao studentDao;

    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public boolean studentLogin(Student student) {
        Map<String,Object> map = new HashMap<>();
        map.put("stuNum",student.getStuNum());
        map.put("password",student.getPassword());
        Student stu = studentDao.selectStudent(map);
        if(stu != null){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public Student showMyInfo(Student student) {
        Map<String,Object> map = new HashMap<>();
        map.put("id",student.getId());
        map.put("stuNum",student.getStuNum());
        Student stu = studentDao.selectStudent(map);

        return stu;
    }

    @Override
    public Student studentRegister(Student student) {
//        Map<String,Object> map = new HashMap<>();
//        map.put("stuNum",student.getStuNum());
//        map.put("identity",student.getIdentity());
//        map.put("isTuan",student.getIsTuan());
//        map.put("name",student.getName());
//        map.put("_class",student.get_class());
//        map.put("password",student.getPassword());
//        map.put("points",student.getPoints());
//        map.put("joinCounts",student.getJoinCounts());
//        map.put("managerId",student.getManagerId().getId());
        try{
            //插入数据，返回自增主键id
            int id = studentDao.insertStudent(student);
//            System.out.println("*****************************");
//            System.out.println(id);
            int id1 = student.getId();
            int id0 = 22200000+id1;
            Student stu = new Student();
            stu.setId(id1);
            stu.setStuNum(String.valueOf(id0));
            int i = studentDao.updateStudent(stu);
            if(id1!=0 && i==1){
                Map<String,Object> map = new HashMap<>();
                map.put("id",student.getId());
                Student s = studentDao.selectStudent(map);
                return s;
            }
            else{
                return null;
            }
        }
        catch (Exception e){
            return null;
        }
    }

    @Override
    public PageBean<Student> showMyClassmatesByPage(int currentPage, int pageSize, int stuId) {
        PageBean<Student> stuPageBean = new PageBean<>();
        stuPageBean.setCurrentPage(currentPage);
        stuPageBean.setPageSize(pageSize);

        //查询当前团支书属于哪个班
        Map<String,Object> map = new HashMap<>();
        map.put("id",stuId);
        Student tuan = studentDao.selectStudent(map);
        String _class = tuan.get_class();

        Map<String,Object> map1 = new HashMap<>();
        map1.put("_class",_class);
        //调用dao查询总记录数
        int totalCount = studentDao.selectStudentsCount(map1);
        stuPageBean.setTotalCount(totalCount);

        int start = (currentPage-1)*pageSize;
        Map<String,Object> map2 = new HashMap<>();
        map2.put("start",start);
        map2.put("pageSize",pageSize);
        map2.put("_class",_class);
        List<Student> activities = studentDao.selectStudents(map2);
        stuPageBean.setList(activities);

        //设置总页码
        int totalPage = (totalCount % pageSize ) == 0 ? totalCount/pageSize : (totalCount/pageSize)+1;
        stuPageBean.setTotalPage(totalPage);
        return stuPageBean;
    }

    @Override
    public PageBean<Student> showClassmatesByPage(int currentPage, int pageSize, String _class) {
        PageBean<Student> stuPageBean = new PageBean<>();
        stuPageBean.setCurrentPage(currentPage);
        stuPageBean.setPageSize(pageSize);

        Map<String,Object> map1 = new HashMap<>();
        map1.put("_class",_class);
        //调用dao查询总记录数
        int totalCount = studentDao.selectStudentsCount(map1);
        stuPageBean.setTotalCount(totalCount);

        int start = (currentPage-1)*pageSize;
        Map<String,Object> map2 = new HashMap<>();
        map2.put("start",start);
        map2.put("pageSize",pageSize);
        map2.put("_class",_class);
        List<Student> activities = studentDao.selectStudents(map2);
        stuPageBean.setList(activities);

        //设置总页码
        int totalPage = (totalCount % pageSize ) == 0 ? totalCount/pageSize : (totalCount/pageSize)+1;
        stuPageBean.setTotalPage(totalPage);
        return stuPageBean;
    }

    @Override
    public Student getStudentInfo(Student student) {
        Map<String,Object> map = new HashMap<>();
        map.put("id",student.getId());
        map.put("stuNum",student.getStuNum());
        Student stu = studentDao.selectStudent(map);
        return stu;
    }

    @Override
    public List<Student> findStudents(int managerId) {
        Map<String,Object> map = new HashMap<>();
        map.put("managerId",managerId);
        List<Student> students = studentDao.selectStudents(map);
        return students;
    }

    @Override
    public boolean changePassword(Student student) {
        try{
            int i = studentDao.updateStudent(student);
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
    public boolean findTuanInClass(String _class, int isTuan) {
        Map<String,Object> map = new HashMap<>();
        map.put("class",_class);
        map.put("isTuan",isTuan);
        Student stu = studentDao.selectStudent(map);
        if(stu!=null){
            return true;
        }
        else{
            return false;
        }
    }
}
