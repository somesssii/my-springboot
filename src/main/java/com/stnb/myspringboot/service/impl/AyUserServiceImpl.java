package com.stnb.myspringboot.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.stnb.ayuserapi.api.AyUserDubboService;
import com.stnb.myspringboot.dao.AyUserDao;
import com.stnb.myspringboot.domain.AyUser;
import com.stnb.myspringboot.error.BusinessException;
import com.stnb.myspringboot.repository.AyUserRepository;
import com.stnb.myspringboot.service.AyUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Future;

/**
 * @author 11299
 * 服务层实现类
 */
@Transactional
@Service
public class AyUserServiceImpl implements AyUserService {

    @Reference(version = "1.0")
    public AyUserDubboService ayUserDubboService;

    @Resource(name = "ayUserRepository")
    private AyUserRepository ayUserRepository;

    @Resource
    private AyUserDao ayUserDao;

    @Resource
    private RedisTemplate redisTemplate;
    private static final String ALL_USER = "ALL_USER_LIST";

    Logger logger = LogManager.getLogger(this.getClass());

    @Override
    public AyUser findById(String id) {

        //1.查询redis缓存中的所有数据
        List<AyUser> ayUserList = redisTemplate.opsForList().range(ALL_USER,0,-1);
        if (ayUserList != null && ayUserList.size() > 0) {
            for (AyUser user : ayUserList) {
                if (user.getId().equals(id)) {
                    return user;
                }
            }
        }

        //2. 查询数据库中的数据
        //AyUser ayUser = ayUserRepository.findOne(id); springboot版本问题导致
        AyUser ayUser = ayUserRepository.findById(id).orElse(null);
        if (ayUser != null) {
            // 3.将数据插入redis缓存中
            redisTemplate.opsForList().leftPush(ALL_USER,ayUser);
        }
        return ayUser;
    }

    @Transactional
    @Override
    public AyUser save(AyUser ayUser) {
        AyUser saveUser =  ayUserRepository.save(ayUser);
//        String error = null;
//        error.split("/");
        return saveUser;
    }

    @Override
    public void delete(String id) {
        ayUserRepository.deleteById(id);
        logger.info("userId:" + id + "用户被删除。");
    }

    @Override
    public List<AyUser> findAll() {
        try{
            logger.info("开始做任务");
            long start = System.currentTimeMillis();
            List<AyUser> ayUserList = ayUserRepository.findAll();
            long end = System.currentTimeMillis();
            logger.info("完成任务耗时："+(end-start) + "毫秒");
            return ayUserList;
        }catch (Exception e) {
            logger.error("method [findAll] error" ,e);
            return Collections.EMPTY_LIST;
        }
    }

    @Override
    public List<AyUser> findByName(String name) {
        return ayUserRepository.findByName(name);
    }

    @Override
    public List<AyUser> findByNameLike(String name) {
        return ayUserRepository.findByNameLike(name);
    }

    @Override
    public List<AyUser> findByIdIn(String name) {
//        return ayUserRepository.findByIdIn(ids);
        return null;
    }

    @Override
    public AyUser findByNameAndPassword(String name, String password) {
        return ayUserDao.findByNameAndPassword(name,password);
    }

    @Override
    @Async
    public Future<List<AyUser>> findAsynAll() {
        try{
            logger.info("开始做任务");
            long start = System.currentTimeMillis();
            List<AyUser> ayUserList = ayUserRepository.findAll();
            long end = System.currentTimeMillis();
            logger.info("完成任务耗时："+(end-start) + "毫秒");
            return new AsyncResult<List<AyUser>>(ayUserList);
        }catch (Exception e) {
            logger.error("method [findAsynAll] error" ,e);
            return new AsyncResult<List<AyUser>>(null);
        }
    }

    @Override
    @Retryable(value = {BusinessException.class},maxAttempts = 5,backoff = @Backoff(delay = 5000,multiplier = 2))
    public AyUser findByNameAndPasswordRetry(String name, String password) {
        logger.info("[findByNameAndPasswordRetry] 方法失败重试了！！!");
        throw new BusinessException();
    }

    @Override
    public List<AyUser> findAll(Pageable pageable) {
        return ayUserRepository.findAll();
    }
}
