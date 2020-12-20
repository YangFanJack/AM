package jack.controller;

import jack.dao.MessageDao;
import jack.pojo.Activity;
import jack.pojo.Message;
import jack.pojo.PageBean;
import jack.service.ActivityService;
import jack.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.lang.model.element.VariableElement;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;

@Controller
public class MessageController {
    @Autowired
    @Qualifier("MessageServiceImpl")
    private MessageService messageService;

    //展示所有消息
    //学生都要显示
    @RequestMapping("showAllMessageByPage")
    public String showAllMessageByPage(String totalPage, String currentPage, String searchString, Model model, HttpSession session){
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

        int sid = 0;
        if(session.getAttribute("sid")!=null){
            sid = (int) session.getAttribute("sid");
        }

        int pageSize = 5;
        PageBean<Message> mesPageBean = messageService.showAllMessageByPage(currentPage0, pageSize, searchString, sid);
        model.addAttribute("mesPageBean",mesPageBean);
        model.addAttribute("searchString",searchString);
        return "student_myMessage";
    }

    @RequestMapping("doRead")
    public String doRead(String messageId, Model model, HttpSession session){
        int messageId0 = 0;
        if(messageId!=null && !messageId.equals("")){
            messageId0 = Integer.parseInt(messageId);
        }
        boolean b = messageService.doRead(messageId0);
        return "redirect:/showAllMessageByPage";
    }

    @RequestMapping("deleteMessage")
    public String deleteMessage(String messageId, Model model, HttpSession session){
        int messageId0 = 0;
        if(messageId!=null && !messageId.equals("")){
            messageId0 = Integer.parseInt(messageId);
        }
        boolean b = messageService.deleteMessage(messageId0);
        return "student_myMessage";
    }

}