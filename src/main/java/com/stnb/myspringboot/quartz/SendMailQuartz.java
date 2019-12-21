package com.stnb.myspringboot.quartz;

import com.stnb.myspringboot.domain.AyUser;
import com.stnb.myspringboot.mail.SendJunkMailService;
import com.stnb.myspringboot.service.AyUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 定时器类
 * @author 11299
 */
@Component
@Configuration
@EnableScheduling
public class SendMailQuartz {

    /**
     *日志对象
     */
    private static final Logger logger = LogManager.getLogger(SendMailQuartz.class);

    @Resource
    private SendJunkMailService sendJunkMailService;
    @Resource
    private AyUserService ayUserService;

    /**
     * 每5秒执行一次
     */
    @Scheduled(cron = "*/5 * *  * * * ")
    public void reportCurrentByCron(){
        List<AyUser> userList = ayUserService.findAll();
        if (userList == null || userList.size() <= 0) {
            return;
        }
        //发送邮件
        sendJunkMailService.sendJunkMail(userList);

        logger.info("发送邮件--->定时器运行了！！！");
    }
}
