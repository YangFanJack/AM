package jack.controller;

import com.sun.media.sound.SoftTuning;
import jack.dao.Stu_actDao;
import jack.pojo.*;
import jack.service.ActivityService;
import jack.service.Stu_actService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class Stu_actController {
    @Autowired
    @Qualifier("Stu_actServiceImpl")
    private Stu_actService stu_actService;
    @Autowired
    @Qualifier("ActivityServiceImpl")
    private ActivityService activityService;

    @RequestMapping("enroll")
    public String enroll(String managerId, String _class, String actId,String category, Model model, HttpSession session){
        int stuId = 0;
        int actId0 = 0;
        if(session.getAttribute("sid")!=null){
            stuId = (int) session.getAttribute("sid");
        }
        if(actId != null && !actId.equals("")){
            actId0 = Integer.parseInt(actId);
        }
        //先判断报名人数是否已满
        Activity activity = new Activity();
        activity.setId(actId0);
        Activity a= activityService.showDetailActivity(activity);
        if(a.getNumLevel() <= a.getEnrollNum()){
            model.addAttribute("msg","该活动报名人数已满！");
            return "redirect:/showDetailActivity?actId="+actId;
        }

        boolean b = false;
        int isSign = 1;
        int joinStatus = 1;
        Stu_act stu_act = new Stu_act();
        Student student = new Student();
        student.setId(stuId);
        stu_act.setStuId(student);
        stu_act.setActId(activity);
        stu_act.setIsSign(isSign);
        stu_act.setJoinStatus(joinStatus);

        //检查是否重复报名
        Stu_act sa = new Stu_act();
        Student s = new Student();
        Activity act = new Activity();
        s.setId((int)session.getAttribute("sid"));
        sa.setStuId(s);
        act.setId(Integer.parseInt(actId));
        sa.setActId(act);
        boolean isEnroll = stu_actService.isEnroll(sa);
        if(isEnroll){
            model.addAttribute("msg","您已报名了该活动，不能重复报名！");
            return "redirect:/showDetailActivity?actId="+actId;
        }

        int mid = 0;
        //学生身份验证
        if(managerId != null && !managerId.equals("")){
            mid = Integer.parseInt(managerId);
        }
        if(Integer.parseInt(category)==4){
            boolean b1 = mid==(int)session.getAttribute("managerId");
            boolean b2 = 2==(int)session.getAttribute("identity");
            if(b1 && b2){
                b = stu_actService.enroll(stu_act);
            }
            else {
                model.addAttribute("msg","您的身份不能选择该学院的党员活动!");
                return "redirect:/showDetailActivity?actId="+actId;
            }
        }
        if(Integer.parseInt(category)==3){
            boolean b1 = mid==(int)session.getAttribute("managerId");
            if(b1){
                b = stu_actService.enroll(stu_act);
            }
            else {
                model.addAttribute("msg","您的身份不能选择该学院的党团活动!");
                return "redirect:/showDetailActivity?actId="+actId;
            }
        }
        if(Integer.parseInt(category)==2){
            boolean b1 = _class.equals(session.getAttribute("_class"));
            boolean b2 = 2==(int)session.getAttribute("identity");
            if(b1 && b2){
                b = stu_actService.enroll(stu_act);
            }
            else {
                model.addAttribute("msg","您的身份不能选择该班级的党员活动!");
                return "redirect:/showDetailActivity?actId="+actId;
            }
        }
        if(Integer.parseInt(category)==1){
            boolean b1 = _class.equals(session.getAttribute("_class"));
            if(b1){
                b = stu_actService.enroll(stu_act);
            }
            else {
                model.addAttribute("msg","您的身份不能选择该班级的党团活动!");
                return "redirect:/showDetailActivity?actId="+actId;
            }
        }
        if(b){
            model.addAttribute("msg","报名成功!");
        }
        else{
            model.addAttribute("msg","系统错误，报名失败!");
        }
        return "redirect:/showDetailActivity?actId="+actId;
    }

    @RequestMapping("showMyAllEnrollByPage")
    public String showMyAllEnrollByPage(String totalPage, String currentPage, String searchString, Model model, HttpSession session){
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

        int sId = 0;
        if(session.getAttribute("sId")!=null){
            sId = (int) session.getAttribute("sId");
        }

        int pageSize = 5;
        PageBean<Stu_act> stu_actPageBean = stu_actService.showMyAllEnrollByPage(currentPage0, pageSize, searchString, sId);
        System.out.println("*******************************************");
        System.out.println(stu_actPageBean);
        model.addAttribute("stu_actPageBean",stu_actPageBean);
        model.addAttribute("searchString",searchString);
        return "student_myActivity1";
    }

    @RequestMapping("showMyDetailEnroll")
    public String showMyDetailEnroll(@ModelAttribute("msg") String msg, String stu_actId, String actId, Model model, HttpSession session){
        System.out.println("拿到重定向得到的参数msg:" + msg);
        model.addAttribute("msg", msg);

        int stu_actId0 = 0;
        int actId0 = 0;
        if(stu_actId!=null&&!stu_actId.equals("")){
            stu_actId0 = Integer.parseInt(stu_actId);
        }
        if(actId!=null&&!actId.equals("")){
            actId0 = Integer.parseInt(actId);
        }
        Stu_act stu_act1 = new Stu_act();
        stu_act1.setId(stu_actId0);
        Stu_act stu_act = stu_actService.showMyDetailEnroll(stu_act1);
        model.addAttribute("stu_act",stu_act);
        List<Stu_act> stuInAct = stu_actService.findStuInAct(actId0);
        model.addAttribute("stuInAct",stuInAct);
        return "student_myActivity2";
    }

    @RequestMapping("doSign")
    public String doSign(String actId, String stu_actId, Model model, HttpSession session){
        int stu_actId0 = 0;
        if(stu_actId!=null && !stu_actId.equals("")){
            stu_actId0 = Integer.parseInt(stu_actId);
        }
        //判断是否已经签到过了
        boolean sign = stu_actService.isSign(stu_actId0);
        if(sign){
            model.addAttribute("msg","不能重复签到！");
            return "redirect:/showMyDetailEnroll?stu_actId="+stu_actId+"&actId="+actId;
        }

        boolean b = stu_actService.doSign(stu_actId0);
        if(b){
            model.addAttribute("msg","签到成功！");
            return "redirect:/showMyDetailEnroll?stu_actId="+stu_actId+"&actId="+actId;
        }
        model.addAttribute("msg","系统错误，签到失败！");
        return "redirect:/showMyDetailEnroll?stu_actId="+stu_actId+"&actId="+actId;
    }

    @RequestMapping("doWords")
    public String doWords(String actId, String stu_actId, String wordsContent, Model model, HttpSession session){
        //获取当前系统时间并转化为字符串
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date=new Date();
        String wordsTime = sdf.format(date);

        //报名id
        int stu_actId0 = 0;
        if(stu_actId!=null&&!stu_actId.equals("")){
            stu_actId0 = Integer.parseInt(stu_actId);
        }

        Stu_act stu_act = new Stu_act();
        stu_act.setId(stu_actId0);
        stu_act.setWordsContent(wordsContent);
        stu_act.setWordsTime(wordsTime);

        boolean b = stu_actService.doWords(stu_act);
        if(b){
            model.addAttribute("msg","留言成功!");
        }
        else{
            model.addAttribute("msg","系统错误，留言失败!");
        }
        return "redirect:/showMyDetailEnroll?stu_actId="+stu_actId+"&actId="+actId;
    }
}