package jack.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private int id;
    private int mesCategory;//消息类型：1活动即将开始，2活动逾期通知，3学院发布通知提醒，4团支书申请失败通知
    private String mesContent;//消息内容
    private int isRead;//是否阅读：1未读，2已读
    private String mesTime;//消息产生时间

    //外键
    private Student stuId;//学生
}
