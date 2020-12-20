package jack.service.impl;

import jack.dao.ManagerDao;
import jack.pojo.Manager;
import jack.service.ManagerService;

import java.util.HashMap;

public class ManagerServiceImpl implements ManagerService {
    private ManagerDao managerDao;

    public void setManagerDao(ManagerDao managerDao) {
        this.managerDao = managerDao;
    }

    @Override
    public boolean managerLogin(Manager manager) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("username",manager.getUsername());
        map.put("password",manager.getPassword());
        Manager man = managerDao.selectManager(map);
        try{
            if(man != null){
                return true;
            }
            else{
                return false;
            }
        }
        catch (Exception e){
            return false;
        }
    }

    @Override
    public Manager getManagerInfo(Manager manager) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("collegeName",manager.getCollegeName());
        map.put("username",manager.getUsername());
        map.put("password",manager.getPassword());
        Manager man = managerDao.selectManager(map);
        return man;
    }
}
