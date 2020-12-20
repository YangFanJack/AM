package jack.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notice {
    private int id;
    private String noticeTitle;//通知标题
    private String noticeContent;//通知内容
    private String noticeTime;//通知时间

    //外键
    private Manager managerId;//学院管理员
}
