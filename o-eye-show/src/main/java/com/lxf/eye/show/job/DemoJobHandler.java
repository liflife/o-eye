package com.lxf.eye.show.job;
import com.lxf.eye.common.domain.User;
import com.lxf.eye.show.listener.CanalRabbitListener;
import com.lxf.eye.show.service.UserService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@JobHandler(value="demoJobHandler")
@Component
public class DemoJobHandler extends IJobHandler {
    private static final Logger logger = LoggerFactory.getLogger(DemoJobHandler.class);
    @Resource
    private UserService userService;
    @Override
    public ReturnT<String> execute(String param) throws Exception {
        logger.info("XXL-JOB, Hello World.");

        List<User> users = userService.queryUserAll();
        logger.info("query user:{}",users);
        return IJobHandler.SUCCESS;
    }
}
