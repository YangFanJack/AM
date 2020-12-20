package jack.controller;

import jack.pojo.Manager;
import jack.service.ActivityService;
import jack.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;

@Controller
public class ManagerController {
    @Autowired
    @Qualifier("ManagerServiceImpl")
    private ManagerService managerService;

    @RequestMapping("managerLogin")
    public String managerLogin(String username, String password, Model model, HttpSession session){

        Manager manager = new Manager();
        manager.setUsername(username);
        manager.setPassword(password);
        boolean isLogin = managerService.managerLogin(manager);
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
            Manager m = managerService.getManagerInfo(manager);
            session.setAttribute("mid",m.getId());
            session.setAttribute("collegeName",m.getCollegeName());
            session.setAttribute("username",m.getUsername());
//            return "redirect:/团党员管理";
            return "redirect:/showClassmatesByPage";
        }
        else{
            model.addAttribute("msg","用户名或者密码错误，请检查!");
            return "institution_login";
        }
    }

    //管理员退出登录
    @RequestMapping("manExit")
    public String manExit(Model model, HttpSession session){
        session.removeAttribute("mid");
        session.removeAttribute("collegeName");
        session.removeAttribute("username");
        return "redirect:/initIndex";
    }
}
