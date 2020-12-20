package jack.service.impl;

import jack.dao.MessageDao;
import jack.dao.Stu_actDao;
import jack.dao.StudentDao;
import jack.pojo.Stu_act;
import jack.service.OnTimeService;

public class OnTimeServiceImpl implements OnTimeService {

    private MessageDao messageDao;
    private Stu_actDao stu_actDao;

    public void setMessageDao(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    public void setStu_actDao(Stu_actDao stu_actDao) {
        this.stu_actDao = stu_actDao;
    }

    @Override
    public void onTimeActBegin() {

    }

    @Override
    public void onTimeActEnd() {
        stu_actDao.autoUpdateOnTime();
    }
}
