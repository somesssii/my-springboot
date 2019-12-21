package com.stnb.myspringboot.listener;

import com.stnb.myspringboot.domain.AyUser;
import com.stnb.myspringboot.service.AyUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.List;

/**
 * @author 11299
 */
@WebListener
public class AyUserListener implements ServletContextListener {

    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private AyUserService ayUserService;
    private static final String ALL_USER = "ALL_USER_LIST";

    Logger logger = LogManager.getLogger(this.getClass());

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        //查询数据库所有用户
        List<AyUser> ayUserList = ayUserService.findAll();

        //清除缓存中的用户数据
        redisTemplate.delete(ALL_USER);

        //将数据存放到redis缓存中
        redisTemplate.opsForList().leftPushAll(ALL_USER, ayUserList);

        //在项目中需要注释，查询所有的用户数据
        List<AyUser> queryUserList = redisTemplate.opsForList().range(ALL_USER,0,-1);
//        System.out.println("缓存中目前的用户数有：" + queryUserList.size() + "人！");

//        System.out.println("ServletContext上下文初始化");

        logger.info("ServletContext上下文初始化");
        logger.info("缓存中目前的用户数有：" + queryUserList.size() + "人！");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
//        System.out.println("ServletContext上下文销毁");
        logger.info("ServletContext上下文销毁");
    }
}
