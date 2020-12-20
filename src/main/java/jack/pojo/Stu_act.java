package jack.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stu_act {
    private int id;
    private int joinStatus;//参加状态：1已报名，2已签到，3已逾期
    private int isSign;//是否签到：1未签到；2已签到
    private String wordsTime;//留言时间
    private String wordsContent;//留言内容

    //外键
    private Student stuId;//学生
    private Activity actId;//活动
}
