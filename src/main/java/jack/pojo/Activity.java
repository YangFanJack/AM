package jack.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Activity {
    private int id;
    private int orgCategory;//组织类型：1团支书，2院管理员
    private String actTitle;//活动标题
    private String actContent;//活动内容
    private String actBeginTime;//活动开始时间
    private String actEndTime;//活动结束时间
    private int getPoints;//活动分数
    private String actPlace;//活动地点
    private int numLevel;//人数限制
    private String enrollEndTime;//报名结束时间
    private int category;//活动种类：1班级党团活动，2班级党员活动，3学院党团活动，4学院党员活动
    private int verifyStatus;//审核状态：1待审核，2已通过
    private int enrollNum;//报名人数
    private int signNum;//签到人数

    //外键
    private Student tuanId;//团支书编号
    private Manager managerId;//院管理员编号
}
