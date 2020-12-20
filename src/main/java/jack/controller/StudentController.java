package jack.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import jack.dao.StudentDao;
import jack.pojo.Activity;
import jack.pojo.Manager;
import jack.pojo.PageBean;
import jack.pojo.Student;
import jack.service.*;
import jack.utils.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.naming.directory.SearchControls;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;

@Controller
public class StudentController {
    @Autowired
    @Qualifier("StudentServiceImpl")
    private StudentService studentService;
    @Autowired
    @Qualifier("ManagerServiceImpl")
    private ManagerService managerService;

    @RequestMapping("studentLogin")
    public String studentLogin(String stuNum, String password, String checkCode, Model model, HttpSession session){
        //验证码校验
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");

        //防止返回时验证码仍然有效
        session.removeAttribute("CHECKCODE_SERVER");
        if(checkCode.equals("") || !checkCode.equalsIgnoreCase(checkcode_server)){
            model.addAttribute("msg","验证码错误，登录失败!");
            return "student_login";
        }
        else {
            Student student = new Student();
            student.setStuNum(stuNum);
            student.setPassword(password);
            boolean isLogin = studentService.studentLogin(student);
            if(isLogin){
                //登录前先清楚所有session
                session.removeAttribute("sid");
                session.removeAttribute("stuNum");
                session.removeAttribute("name");
                session.removeAttribute("managerId");
                session.removeAttribute("_class");
                session.removeAttribute("isTuan");
                session.removeAttribute("identity");

                session.removeAttribute("mid");
                session.removeAttribute("collegeName");
                session.removeAttribute("username");

                //存入新的session
                Student s = studentService.getStudentInfo(student);

                session.setAttribute("sid",s.getId());
                session.setAttribute("stuNum",stuNum);
                session.setAttribute("name",s.getName());
                session.setAttribute("managerId",s.getManagerId().getId());
                session.setAttribute("_class",s.get_class());
                session.setAttribute("isTuan",s.getIsTuan());
                session.setAttribute("identity",s.getIdentity());

                return "redirect:/initIndex";
            }
            else{
                model.addAttribute("msg","用户名或者密码错误，请检查!");
                return "student_login";
            }
        }
    }

    @RequestMapping("showMyInfo")
    public String showMyInfo(@ModelAttribute("msg") String msg, Model model, HttpSession session){
        System.out.println("拿到重定向得到的参数msg:" + msg);
        model.addAttribute("msg", msg);

        int sid = 0;
        if(session.getAttribute("sid")!=null){
            sid = (int) session.getAttribute("sid");
        }
        Student student = new Student();
        student.setId(sid);
        Student stu = studentService.showMyInfo(student);
        model.addAttribute("student",stu);
        return "student_myInformation";
    }

    @RequestMapping("studentRegister")
    public String studentRegister(String name, String password, String rePassword, String collegeName,
              String isTuan, String _class, String identity, Model model, HttpSession session){
        if(!password.equals(rePassword)){
            model.addAttribute("msg","两次密码不一致，请检查!");
            return "student_register";
        }
        Student student = new Student();
        student.setName(name);
        student.setPassword(password);
        Manager manager = new Manager();
//            System.out.println("***************************");
//            System.out.println(collegeName);
        manager.setCollegeName(collegeName);
        Manager managerInfo = managerService.getManagerInfo(manager);
        student.setManagerId(managerInfo);
        int isT = Integer.parseInt(isTuan);
        student.setIsTuan(isT);
        student.set_class(_class);
        int iden = Integer.parseInt(identity);
        student.setIdentity(iden);
        student.setPoints(0);
        student.setJoinCounts(0);
        //如果选择的团支书，检查班上是否已有团支书
        if(isTuan.equals("2")){
            boolean tuanInClass = studentService.findTuanInClass(_class, Integer.parseInt(isTuan));
            if (tuanInClass){
                model.addAttribute("msg","注册失败，您的班级已有团支书!");
                return "student_register";
            }
            else{
                Student stu = studentService.studentRegister(student);
                if(stu != null){
                    //注册成功信息
                    model.addAttribute("msg","注册成功，请前往登录!");
                    model.addAttribute("stuNum",stu.getStuNum());
                    model.addAttribute("stuName",stu.getName());
                    return "student_login";
                }
                else{
                    model.addAttribute("msg","注册失败，用户名或者邮箱已被注册!");
                    return "student_register";
                }
            }
        }
        else {
            Student stu = studentService.studentRegister(student);
            if(stu != null){
                //注册成功信息
                model.addAttribute("msg","注册成功，请前往登录!");
                model.addAttribute("stuNum",stu.getStuNum());
                model.addAttribute("stuName",stu.getName());
                return "student_login";
            }
            else{
                model.addAttribute("msg","注册失败，用户名或者邮箱已被注册!");
                return "student_register";
            }
        }
    }

    //分页显示某个班级的所有学生
    @RequestMapping("showMyClassmatesByPage")
    public String showMyClassmatesByPage(String totalPage, String currentPage, Model model, HttpSession session){
        int currentPage0 = 0;
        if(currentPage!=null&&!currentPage.equals("")){
            currentPage0 = Integer.parseInt(currentPage);
            if(currentPage0 <= 1){
                currentPage0 = 1;
            }
        }
        if(totalPage!=null&&!totalPage.equals("")){
            if(Integer.parseInt(currentPage) >= Integer.parseInt(totalPage)){
                currentPage0 = Integer.parseInt(totalPage);
            }
        }
        if (currentPage0 == 0){
            currentPage0=1;
        }

        int tuanId = 0;
        if(session.getAttribute("sid")!=null){
            tuanId = (int) session.getAttribute("sid");
        }

        int pageSize = 5;
        PageBean<Student> stuPageBean = studentService.showMyClassmatesByPage(currentPage0, pageSize, tuanId);
        model.addAttribute("stuPageBean",stuPageBean);
        return "secretory_myOrganization";
    }


    //分页显示指定班级的所有学生
    @RequestMapping("showClassmatesByPage")
    public String showClassmatesByPage(String totalPage, String currentPage, String _class, Model model, HttpSession session){
        if(_class==null || _class.equals("")){
            String collegeName = (String) session.getAttribute("collegeName");
            switch (collegeName){
                case "XX":_class = "rj1";break;
                case "HH":_class = "hh1";break;
                case "JT":_class = "jg1";break;
                case "WY":_class = "yy1";break;
            }
        }

        int currentPage0 = 0;
        if(currentPage!=null&&!currentPage.equals("")){
            currentPage0 = Integer.parseInt(currentPage);
            if(currentPage0 <= 1){
                currentPage0 = 1;
            }
        }
        if(totalPage!=null&&!totalPage.equals("")){
            if(Integer.parseInt(currentPage) >= Integer.parseInt(totalPage)){
                currentPage0 = Integer.parseInt(totalPage);
            }
        }
        if (currentPage0 == 0){
            currentPage0=1;
        }
//        if(_class==null || _class.equals("")){
//            String managerAllUser_class = (String) session.getAttribute("managerAllUser_class");
//            if(managerAllUser_class!=null && !managerAllUser_class.equals("")){
//                _class = managerAllUser_class;
//            }
//            else{
//                String collegeName = (String) session.getAttribute("collegeName");
//                switch (collegeName){
//                    case "XX":_class = "rj1";break;
//                    case "HH":_class = "hh1";break;
//                    case "JT":_class = "jg1";break;
//                    case "WY":_class = "yy1";break;
//                }
//            }
//        }

        int pageSize = 5;
        PageBean<Student> stuPageBean = studentService.showClassmatesByPage(currentPage0, pageSize, _class);
        model.addAttribute("stuPageBean",stuPageBean);
        model.addAttribute("_class",_class);
        return "institution_manageAllUser";
    }

    //学生退出登录
    @RequestMapping("stuExit")
    public String stuExit(Model model, HttpSession session){
        session.removeAttribute("sid");
        session.removeAttribute("stuNum");
        session.removeAttribute("name");
        session.removeAttribute("managerId");
        session.removeAttribute("_class");
        session.removeAttribute("isTuan");
        session.removeAttribute("identity");
        return "redirect:/initIndex";
    }

    //学生修改密码
    @RequestMapping("changePassword")
    public String changePassword(String password, String newPassword, Model model, HttpSession session){
        int sid = 0;
        String stuNum = null;
        if(session.getAttribute("sid")!=null){
            sid = (int) session.getAttribute("sid");
        }
        if(session.getAttribute("stuNum")!=null){
            stuNum = (String) session.getAttribute("stuNum");
        }
        Student s1 = new Student();
        Student s2 = new Student();
        s1.setStuNum(stuNum);
        s1.setPassword(password);
        s2.setId(sid);
        s2.setPassword(newPassword);
        boolean isRight = studentService.studentLogin(s1);
        if(isRight){
            boolean b = studentService.changePassword(s2);
            if(b){
                model.addAttribute("msg","修改密码成功！");
                return "redirect:/showMyInfo";
            }
            else{
                model.addAttribute("msg","系统错误，密码修改失败！");
                return "redirect:/showMyInfo";
            }
        }
        else{
            model.addAttribute("msg","您的旧密码错误，无法修改密码！");
            return "redirect:/showMyInfo";
        }
    }
}