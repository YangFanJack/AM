package jack.controller;

import jack.dao.ActivityDao;
import jack.pojo.*;
import jack.service.ActivityService;
import jack.service.MessageService;
import jack.service.Stu_actService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class ActivityController {
    @Autowired
    @Qualifier("ActivityServiceImpl")
    private ActivityService activityService;
    @Autowired
    @Qualifier("MessageServiceImpl")
    private MessageService messageService;
    @Autowired
    @Qualifier("Stu_actServiceImpl")
    private Stu_actService stu_actService;

    //显示活动详情
    //页面：主页-活动详情：活动id,涉及activity和manager和student三个表
    @RequestMapping("showDetailActivity")
    public String showDetailActivity(@ModelAttribute("msg") String msg, String to, String actId, Model model, HttpSession session){
        System.out.println("拿到重定向得到的参数msg:" + msg);
        model.addAttribute("msg", msg);

        int actId0 = 0;
        if(actId!=null&&!actId.equals("")){
            actId0 = Integer.parseInt(actId);
        }
        Activity act = new Activity();
        act.setId(actId0);
        Activity activity = activityService.showDetailActivity(act);
        model.addAttribute("activity",activity);
        if(to!=null && to.equals("verify")){
            return "institution_verifyActivity2";
        }
        else{
            return "index_detailActivity";
        }

    }

    //显示团支书的申请活动详情
    //页面：团支书特有我的申请2：活动id，涉及activity和student两个类
    @RequestMapping("showMyDetailActivity")
    public String showMyDetailActivity(String to, String actId, Model model, HttpSession session){
        int actId0 = 0;
        if(actId!=null&&!actId.equals("")){
            actId0 = Integer.parseInt(actId);
        }
        Activity act = new Activity();
        act.setId(actId0);
        Activity activity = activityService.showDetailActivity(act);
        model.addAttribute("activity",activity);
        System.out.println("#################################################");
        System.out.println(activity);
        if(to.equals("xianshi")){
            return "secretory_myApply2";
        }
        else{
            model.addAttribute("msg","change");
            return "secretory_applyActivity";
        }

    }

    //申请活动
    //页面：团支书特有申请活动：
    //orgcategory，tuanId，actTitle,actContent，actBeginTime,actEndTime,getPoints,actPlace,numLevel,enrollEndTime,category,verifyStatus,enrollNum,signNum
    //涉及activity一个类
    @RequestMapping("applyActivity")
    public String applyActivity(String actTitle, String actContent, String actBeginD, String actBeginT, String actEndD, String actEndT, String getPoints,
            String actPlace, String numLevel, String enrollEndD, String enrollEndT, String category, Model model, HttpSession session){
        int getPoints0 = 0;
        int numLevel0 = 0;
        int category0 = 0;
        int tuanId = 0;

        if(session.getAttribute("sid")!=null){
            tuanId = (int) session.getAttribute("sid");
        }
        if(getPoints!=null&&!getPoints.equals("")){
            getPoints0 = Integer.parseInt(getPoints);
        }
        if(numLevel!=null&&!numLevel.equals("")){
            numLevel0 = Integer.parseInt(numLevel);
        }
        if(category!=null&&!category.equals("")){
            category0 = Integer.parseInt(category);
        }
        Activity activity = new Activity();
        Student tuan = new Student();
        Manager manager = new Manager();
        tuan.setId(tuanId);
        activity.setTuanId(tuan);
        activity.setManagerId(manager);
        activity.setOrgCategory(1);
        activity.setActTitle(actTitle);
        activity.setActContent(actContent);

        activity.setActBeginTime(actBeginD+" "+actBeginT);
        activity.setActEndTime(actEndD+" "+actEndT);
        activity.setGetPoints(getPoints0);
        activity.setActPlace(actPlace);
        activity.setNumLevel(numLevel0);

        activity.setEnrollEndTime(enrollEndD+" "+enrollEndT);
        activity.setCategory(category0);
        activity.setVerifyStatus(1);
        activity.setEnrollNum(0);
        activity.setSignNum(0);

        boolean b = activityService.applyActivity(activity);
        if(b){
            model.addAttribute("msg","申请活动成功!");
        }
        else{
            model.addAttribute("msg","系统错误，申请活动失败!");
        }
        return "secretory_applyActivity";
    }

    //发布活动
    //页面：学院管理员发布活动：
    //orgcategory，managerId，actTitle,actContent，actBeginTime,actEndTime,getPoints,actPlace,numLevel,enrollEndTime,category,verifyStatus,enrollNum,signNum
    //涉及activity一个类
    @RequestMapping("postActivity")
    public String postActivity(String actTitle, String actContent, String actBeginD, String actBeginT, String actEndD, String actEndT, String getPoints,
            String actPlace, String numLevel, String enrollEndD, String enrollEndT, String category, Model model, HttpSession session){
        int getPoints0 = 0;
        int numLevel0 = 0;
        int category0 = 0;
        int managerId = 0;

        if(session.getAttribute("mid")!=null){
            managerId = (int) session.getAttribute("mid");
        }
        if(getPoints!=null&&!getPoints.equals("")){
            getPoints0 = Integer.parseInt(getPoints);
        }
        if(numLevel!=null&&!numLevel.equals("")){
            numLevel0 = Integer.parseInt(numLevel);
        }
        if(category!=null&&!category.equals("")){
            category0 = Integer.parseInt(category);
        }
        Activity activity = new Activity();
        Manager manager = new Manager();
        Student student = new Student();
        manager.setId(managerId);
        activity.setManagerId(manager);
        activity.setTuanId(student);
        activity.setOrgCategory(2);
        activity.setActTitle(actTitle);
        activity.setActContent(actContent);

        activity.setActBeginTime(actBeginD+" "+actBeginT);
        activity.setActEndTime(actEndD+" "+actEndT);
        activity.setGetPoints(getPoints0);
        activity.setActPlace(actPlace);
        activity.setNumLevel(numLevel0);

        activity.setEnrollEndTime(enrollEndD+" "+enrollEndT);
        activity.setCategory(category0);
        activity.setVerifyStatus(2);
        activity.setEnrollNum(0);
        activity.setSignNum(0);

//        System.out.println("7777777777777777777777777777777777777777777777777");
//        System.out.println(activity);
        boolean b = activityService.postActivity(activity);
        if(b){
            model.addAttribute("msg","发布活动成功!");
        }
        else{
            model.addAttribute("msg","系统错误，发布活动失败!");
        }
        return "institution_releaseActivity";
    }

    //修改活动（管理员未审核之前）
    //页面：团支书特有修改申请活动：
    //actId, actTitle, actContent，actBeginTime, actEndTime, getPoints, actPlace, numLevel, enrollEndTime,category
    //涉及activity一个类
    @RequestMapping("changeActivity")
    public String changeActivity(String actId, String actTitle, String actContent, String actBeginD, String actBeginT, String actEndD, String actEndT, String getPoints,
             String actPlace, String numLevel, String enrollEndD, String enrollEndT, String category, Model model, HttpSession session){
        int actId0 = 0;
        int getPoints0 = 0;
        int numLevel0 = 0;
        int category0 = 0;

        if(actId!=null&&!actId.equals("")){
            actId0 = Integer.parseInt(actId);
        }
        if(getPoints!=null&&!getPoints.equals("")){
            getPoints0 = Integer.parseInt(getPoints);
        }
        if(numLevel!=null&&!numLevel.equals("")){
            numLevel0 = Integer.parseInt(numLevel);
        }
        if(category!=null&&!category.equals("")){
            category0 = Integer.parseInt(category);
        }
        Activity activity = new Activity();
        activity.setId(actId0);
        activity.setActTitle(actTitle);
        activity.setActContent(actContent);

        activity.setActBeginTime(actBeginD+" "+actBeginT);
        activity.setActEndTime(actEndD+" "+actEndT);
        activity.setGetPoints(getPoints0);
        activity.setActPlace(actPlace);
        activity.setNumLevel(numLevel0);

        activity.setEnrollEndTime(enrollEndD+" "+enrollEndT);
        activity.setCategory(category0);

        boolean b = activityService.changeActivity(activity);
        if(b){
            model.addAttribute("msg","修改申请活动成功!");
        }
        else{
            model.addAttribute("msg","系统错误，修改申请活动失败!");
        }
        return "团支书我的申请界面";
    }

    //审核活动通过
    @RequestMapping("passActivity")
    public String passActivity(String actId, Model model, HttpSession session){
        int actId0 = 0;
        if(actId!=null&&!actId.equals("")){
            actId0 = Integer.parseInt(actId);
        }
        Activity activity = new Activity();
        activity.setId(actId0);
        activity.setVerifyStatus(2);
        boolean b = activityService.passActivity(activity);
        if(b){
            model.addAttribute("msg","审核活动通过成功!");
            //活动审核成功，团支书自动报名活动
            Activity a = activityService.showDetailActivity(activity);
            int isSign = 1;
            int joinStatus = 1;
            Stu_act stu_act = new Stu_act();
            Student student = new Student();
            student.setId(a.getTuanId().getId());
            stu_act.setStuId(student);
            stu_act.setActId(activity);
            stu_act.setIsSign(isSign);
            stu_act.setJoinStatus(joinStatus);
            boolean enroll = stu_actService.enroll(stu_act);
        }
        else{
            model.addAttribute("msg","系统错误，审核活动通过失败!");
        }
        return "redirect:/showVerifyingActivityByPage";
    }

    //分页显示团支书申请的所有活动
    @RequestMapping("showMyActivityByPage")
    public String showMyActivityByPage(String totalPage, String currentPage, String searchString, Model model, HttpSession session){
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
        PageBean<Activity> actPageBean = activityService.showMyActivityByPage(currentPage0, pageSize, tuanId, searchString);
        model.addAttribute("actPageBean",actPageBean);
        model.addAttribute("searchString",searchString);
        return "secretory_myApply1";
    }

    //管理员分页显示该学院所有审核通过活动
    @RequestMapping("showPassActivityByPage")
    public String showPassActivityByPage(String category, String totalPage, String currentPage, String searchString, Model model, HttpSession session){
        int currentPage0 = 0;
        int category0 = 4;
        int managerId = 0;

        if(category!=null&&!category.equals("")){
            category0 = Integer.parseInt(category);
        }

        if(session.getAttribute("mid")!=null){
            managerId = (int) session.getAttribute("mid");
        }
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

        int pageSize = 5;
        int verifyStatus = 2;
        PageBean<Activity> actPageBean = activityService.showPassActivityByPage(category0, currentPage0, pageSize, searchString, managerId, verifyStatus);
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println(actPageBean);
        model.addAttribute("actPageBean",actPageBean);
        model.addAttribute("category",category);
        model.addAttribute("searchString",searchString);
        return "institution_manageActivity";
    }

    //管理员分页显示该学院所正在审核活动
    @RequestMapping("showVerifyingActivityByPage")
    public String showVerifyingActivityByPage(String category, String totalPage, String currentPage, String searchString, Model model, HttpSession session){
        int currentPage0 = 0;
        int category0 = 4;
        int managerId = 0;

        if(category!=null&&!category.equals("")){
            category0 = Integer.parseInt(category);
        }

        if(session.getAttribute("mid")!=null){
            managerId = (int) session.getAttribute("mid");
        }
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

        int pageSize = 5;
        int verifyStatus = 1;
        PageBean<Activity> actPageBean = activityService.showPassActivityByPage(category0, currentPage0, pageSize, searchString, managerId, verifyStatus);
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println(actPageBean);
        model.addAttribute("actPageBean",actPageBean);
        model.addAttribute("category",category);
        model.addAttribute("searchString",searchString);
        return "institution_verifyActivity1";
    }

    //审核活动不通过
    @RequestMapping("outActivity")
    public String outActivity(String actId, Model model, HttpSession session){
        int actId0 = 0;
        if(actId!=null&&!actId.equals("")){
            actId0 = Integer.parseInt(actId);
        }
        Activity a = new Activity();
        a.setId(actId0);
        Activity activity = activityService.showDetailActivity(a);

        boolean b = activityService.outActivity(actId0);

        System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        System.out.println(b);

        //获取当前系统时间并转化为字符串
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date=new Date();
        String messageTime = sdf.format(date);
        if(b){
            model.addAttribute("msg","审核活动不通过成功!");
            //给团支书发一个审核失败通知消息
            Message message = new Message();
            message.setMesCategory(4);
            message.setMesTime(messageTime);
            message.setIsRead(1);
            message.setMesContent("您申请的活动：：“"+activity.getActTitle()+"”因内容问题审核失败，望周知！");
            Student student = new Student();
            student.setId(activity.getTuanId().getId());
            message.setStuId(student);
            boolean b1 = messageService.insertMessage(message);
        }
        else{
            model.addAttribute("msg","系统错误，审核活动不通过失败!");
        }
        return "redirect:/showVerifyingActivityByPage";
    }
}
