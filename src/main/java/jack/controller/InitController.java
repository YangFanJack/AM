package jack.controller;

import jack.pojo.Activity;
import jack.pojo.Notice;
import jack.pojo.PageBean;
import jack.service.ActivityService;
import jack.service.ManagerService;
import jack.service.NoticeService;
import jack.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class InitController {

    @Autowired
    @Qualifier("ActivityServiceImpl")
    private ActivityService activityService;
    @Autowired
    @Qualifier("NoticeServiceImpl")
    private NoticeService noticeService;

    //访问主页之前需要获取的动态数据（通知和活动）
    @RequestMapping("initIndex")
    public String initIndex(String totalPage, String currentPage, String searchString, String collegeName, String _class, Model model, HttpSession session){
        if((collegeName == null || collegeName.equals("")) && (_class == null || _class.equals(""))){
            collegeName="XX";
        }
        PageBean<Activity> actPageBean = handleActivity(totalPage, currentPage, searchString, collegeName, _class);
        model.addAttribute("actPageBean",actPageBean);
        List<Notice> notices = handleNotice();
        model.addAttribute("notices",notices);
        model.addAttribute("choose_collegeName",collegeName);
        model.addAttribute("choose_class",_class);
        model.addAttribute("searchString",searchString);
        return "index";
    }

    //单独处理分页活动显示的方法
    private PageBean<Activity> handleActivity(String totalPage, String currentPage, String searchString, String collegeName, String _class){
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

        int pageSize = 5;
        int verifyStatus = 2;

        //这里的collegeName和_class放在service层处理
        PageBean<Activity> actPageBean = activityService.showChooseActivityByPage(currentPage0, pageSize, searchString, verifyStatus, collegeName, _class);
        return actPageBean;
    }

    //单独处理通知显示的函数
    private List<Notice> handleNotice(){
        int currentPage = 1;
        int pageSize = 5;
        PageBean<Notice> noticePageBean = noticeService.showAllNoticeByPage(currentPage, pageSize, null);
        List<Notice> notices = noticePageBean.getList();
        for (Notice n : notices) {
            System.out.println(n);
        }
        return notices;
    }
}
