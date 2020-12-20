package jack.utils;

import org.apache.ibatis.io.Resources;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;

public class MailUtil {

    private static JavaMailSender javaMailSender;
    public static void setJavaMailSender(JavaMailSender javaMailSender) {
        MailUtil.javaMailSender = javaMailSender;
    }

    public static void sendMail(String mail, String subject, String text){
        MimeMessage mMessage=javaMailSender.createMimeMessage();//创建邮件对象
        MimeMessageHelper mMessageHelper;
        Properties prop = new Properties();
        String from;
        try {
            //从配置文件中拿到发件人邮箱地址
            prop.load(Resources.getResourceAsStream("/mail.properties"));
            from = prop.get("mail.smtp.username")+"";
            mMessageHelper=new MimeMessageHelper(mMessage,true);
            mMessageHelper.setFrom(from);//发件人邮箱
            mMessageHelper.setTo("huachun_w@163.com");//收件人邮箱
            mMessageHelper.setSubject("Spring的邮件发送");//邮件的主题
            mMessageHelper.setText("<p>这是使用spring的邮件功能发送的一封邮件</p><br/>" +
                    "<a href='https://blog.csdn.net/Mr__Viking'>打开我的博客主页</a><br/>" +
                    "<img src='fengye'>",true);//邮件的文本内容，true表示文本以html格式打开
//            File file=new File("D:/图片/大雄.jpg");//在邮件中添加一张图片
//            FileSystemResource resource=new FileSystemResource(file);
//            mMessageHelper.addInline("fengye", resource);//这里指定一个id,在上面引用
//            mMessageHelper.addAttachment("D:/图片/静香.jpg", resource);//在邮件中添加一个附件
            javaMailSender.send(mMessage);//发送邮件
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
