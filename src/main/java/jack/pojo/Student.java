package jack.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private int id;
    private String stuNum;//学号
    private int identity;//身份：1团员，2党员
    private int isTuan;//是否是团支书
    private String name;//姓名
    private String _class;//专业+班级：a1,a2,a3,b1,b2,b3...
    private String password;//密码
    private int points;//分数
    private int joinCounts;//参加活动次数

    //外键
    private Manager managerId;//学院管理员
}
