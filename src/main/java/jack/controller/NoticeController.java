package jack.controller;

import jack.pojo.*;
import jack.service.MessageService;
import jack.service.NoticeService;
import jack.service.StudentService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.ParameterResolutionDelegate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class NoticeController {
    @Autowired
    @Qualifier("NoticeServiceImpl")
    private NoticeService noticeService;
    @Autowired
    @Qualifier("MessageServiceImpl")
    private MessageService messageService;
    @Autowired
    @Qualifier("StudentServiceImpl")
    private StudentService studentService;

    @RequestMapping("showAllNoticeByPage")
    public String showAllNoticeByPage(String totalPage, String currentPage, String searchString, Model model, HttpSession session){
        //将searchString存入session
//        if(searchString!=null && !searchString.equals("")){
//            session.setAttribute("searchString",searchString);
//        }
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
        PageBean<Notice> notPageBean = noticeService.showAllNoticeByPage(currentPage0, pageSize, searchString);
        model.addAttribute("notPageBean",notPageBean);
        model.addAttribute("searchString",searchString);
        return "index_detailNotice";
    }

    @RequestMapping("showManNoticeByPage")
    public String showManNoticeByPage(String totalPage, String currentPage, String searchString, Model model, HttpSession session){
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

        int managerId = 0;
        if(session.getAttribute("mid")!=null){
            managerId = (int) session.getAttribute("mid");
        }

        int pageSize = 5;
        PageBean<Notice> notPageBean = noticeService.showManNoticeByPage(currentPage0, pageSize, searchString, managerId);
        model.addAttribute("notPageBean",notPageBean);
        model.addAttribute("searchString",searchString);
        return "institution_manageNotice1";
    }

    @RequestMapping("deleteNotice")
    public String deleteNotice(String notId, Model model, HttpSession session){
        int notId0 = 0;
        if(notId!=null && !notId.equals("")){
            notId0 = Integer.parseInt(notId);
        }
        boolean b = noticeService.deleteNotice(notId0);
        if(b){
            model.addAttribute("msg","删除通知成功!");
        }
        else{
            model.addAttribute("msg","系统错误，删除通知失败!");
        }
        return "redirect:/showManNoticeByPage";
    }

    @RequestMapping("postNotice")
    public String postNotice(String noticeTitle, String noticeContent, Model model, HttpSession session){
        int managerId = 0;
        if(session.getAttribute("mid")!=null){
            managerId = (int) session.getAttribute("mid");
        }

        //获取当前系统时间并转化为字符串
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date=new Date();
        String noticeTime = sdf.format(date);

        Notice notice = new Notice();
        Manager manager = new Manager();
        manager.setId(managerId);
        notice.setManagerId(manager);
        notice.setNoticeTitle(noticeTitle);
        notice.setNoticeContent(noticeContent);
        notice.setNoticeTime(noticeTime);

        boolean b = noticeService.postNotice(notice);
        if(b){
            model.addAttribute("msg","发布通知成功!");
            //给该学院每一位同学发一个通知消息
            List<Student> students = studentService.findStudents(managerId);
            for (Student s : students) {
                Message message = new Message();
                message.setMesCategory(3);
                message.setMesTime(noticeTime);
                message.setIsRead(1);
                message.setMesContent("您所在学院发布了新的通知：“"+noticeTitle+"”，请注意查看！");
                Student student = new Student();
                student.setId(s.getId());
                message.setStuId(student);
                boolean b1 = messageService.insertMessage(message);
            }
        }
        else{
            model.addAttribute("msg","系统错误，发布通知失败!");
        }
        return "institution_releaseNotice";
    }

    @RequestMapping("showDetailNotice")
    public String showDetailNotice(String notId, Model model, HttpSession session){
        int notId0 = 0;
        if(notId!=null && !notId.equals("")){
            notId0 = Integer.parseInt(notId);
        }
        Notice not = new Notice();
        not.setId(notId0);
        Notice notice = noticeService.showDetailNotice(not);
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
        System.out.println(notice);
        model.addAttribute("notice",notice);
        return "institution_manageNotice2";
    }

    @Test
    public void test(){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date=new Date();
        String dateStr = sdf.format(date);
        System.out.println(dateStr);

        date.setTime(date.getTime() + 10*60*1000);
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date));
        System.out.println("********************************************");
        String testTime = "2020-12-12 10:10";
        String[] split = testTime.split("-| |:");
//        System.out.println(split[0]);
        for(int i=0;i<5;i++){
            System.out.println(split[i]);
        }
    }
}