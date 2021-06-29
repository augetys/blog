package com.hope.blog.log.handle;

import com.hope.blog.log.model.SysLog;
import com.hope.blog.log.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by lijin on  2021/6/22
 */
@Component
public class SysLogHandle extends AbstractRequestAwareRunnable {

    @Autowired
    private SysLogService sysLogService;

    private SysLog sysLog;

    public void setSysLogHandle(SysLog sysLog) {
        this.sysLog = sysLog;
    }


    @Override
    protected void onRun() {
        sysLogService.save(sysLog);
    }
}
