package jack.service;

public interface OnTimeService {
    //活动开始：发送活动开始消息
    public void onTimeActBegin();
    //活动结束还没签到：发送逾期消息并更新stu_act表的verifyStatus字段为3
    public void onTimeActEnd();
}
