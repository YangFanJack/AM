package jack.service;

import jack.pojo.Activity;
import jack.pojo.PageBean;

import java.util.List;

public interface ActivityService {
    //显示活动详情
    public Activity showDetailActivity(Activity activity);
    //显示团支书的申请活动详情
    public Activity showMyDetailActivity(Activity activity);
    //申请活动
    public boolean applyActivity(Activity activity);
    //发布活动
    public boolean postActivity(Activity activity);
    //修改活动（管理员未审核之前）
    public boolean changeActivity(Activity activity);
    //审核活动通过
    public boolean passActivity(Activity activity);
    //分页显示团支书申请的所有活动
    public PageBean<Activity> showMyActivityByPage(int currentPage, int pageSize, int tuanId, String searchString);
    //分页显示所有已通过活动
    public PageBean<Activity> showPassActivityByPage(int category, int currentPage, int pageSize, String searchString, int managerId, int verifyStatus);
    //分页显示所有活动（按照学院&班级）
    public PageBean<Activity> showChooseActivityByPage(int currentPage, int pageSize, String searchString, int verifyStatus, String collegeName, String _class);
    //审核活动不通过
    public boolean outActivity(int actId);
}
