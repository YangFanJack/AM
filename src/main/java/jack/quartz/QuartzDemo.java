package jack.quartz;

import jack.service.ActivityService;
import jack.service.OnTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Date;

public class QuartzDemo {

    @Autowired
    @Qualifier("OnTimeServiceImpl")
    private OnTimeService onTimeService;

    public void execute() throws Exception {
        onTimeService.onTimeActBegin();
        onTimeService.onTimeActEnd();
        System.out.println("=============定时demo:"+new Date());
    }
}
