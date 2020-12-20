package jack.service;

import jack.pojo.Manager;

public interface ManagerService {
    //学院管理员登录
    public boolean managerLogin(Manager manager);
    //获得某个管理员的信息
    public Manager getManagerInfo(Manager manager);
}
