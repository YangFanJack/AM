package jack.dao;

import jack.pojo.Manager;

import java.util.Map;

public interface ManagerDao {
    //查找某个管理员
    public Manager selectManager(Map<String,Object> parameters);
}
