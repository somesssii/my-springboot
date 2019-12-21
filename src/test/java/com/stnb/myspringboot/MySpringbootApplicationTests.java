package com.stnb.myspringboot;

import com.stnb.myspringboot.domain.AyMood;
import com.stnb.myspringboot.domain.AyUser;
import com.stnb.myspringboot.jms.AyMoodProducer;
import com.stnb.myspringboot.model.AyUserAttachmentRel;
import com.stnb.myspringboot.service.AyMoodService;
import com.stnb.myspringboot.service.AyUserAttachmentRelService;
import com.stnb.myspringboot.service.AyUserService;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.annotation.Resource;
import javax.jms.Destination;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Future;

@SpringBootTest
class MySpringbootApplicationTests {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Resource
    private AyUserService ayUserService;

    @Resource
    private AyMoodService ayMoodService;

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private AyMoodProducer ayMoodProducer;

    @Resource
    private AyUserAttachmentRelService ayUserAttachmentRelService;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    Logger logger = LogManager.getLogger(this.getClass());

    @Test
    public void mySqlTest() {

        String sql = "select id,name,password from ay_user";
        List<AyUser> userList = jdbcTemplate.query(sql, new RowMapper<AyUser>() {
            @Override
            public AyUser mapRow(ResultSet resultSet, int i) throws SQLException {
                AyUser user = new AyUser();
                user.setId(resultSet.getString("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));

                return user;
            }
        });

        System.out.println("查询成功");
        for (AyUser user:userList) {
            System.out.println("id:" + user.getId() + "; name:" + user.getName());
        }
    }

//    @Test
//    public void testRepository() {
//        //新增数据
//        AyUser ayUser = new AyUser();
//        ayUser.setName("test");
//        ayUser.setPassword("123456");
//        ayUserService.save(ayUser);
//
////        ayUserService.delete("2");
//    }

    @Test
    public void testTransaction() {
        AyUser ayUser = new AyUser();
        ayUser.setId("3");
        ayUser.setName("阿A");
        ayUser.setPassword("123456");
        ayUserService.save(ayUser);
    }

    @Test
    public void testRedis() {
        // 增加 key:name value:ay
//        redisTemplate.opsForValue().set("name","ay");
//        String name = (String)redisTemplate.opsForValue().get("name");
//        System.out.println(name);

        //删除
        redisTemplate.delete("name");
        //更新
        redisTemplate.opsForValue().set("name","a2");

        String name1 = stringRedisTemplate.opsForValue().get("name");
        System.out.println(name1);
    }

    @Test
    public void testLog4j() {
        ayUserService.delete("3");
        logger.info("delete success!!!");
    }

    @Test
    public void testMybatis() {
        AyUser ayUser = ayUserService.findByNameAndPassword("root","123456");
        logger.info("id是：" + ayUser.getId() + ",name是：" + ayUser.getName());
    }

    @Test
    public void testAyMood() {
        AyMood ayMood = new AyMood();
        ayMood.setId("1");
        ayMood.setUserId("1");
        ayMood.setPraiseNum(0);
        ayMood.setContent("第一条MOOD！！！");
        ayMood.setPublishTime(new Date());

        AyMood mood = ayMoodService.save(ayMood);
    }

    @Test
    public void testActiveMQ() {
        Destination destination = new ActiveMQQueue("ay.queue");
        ayMoodProducer.sendMessage(destination,"hello pssii!!!");
        logger.info("发表的说说：" + destination);
    }

    @Test
    public void testActiveMQAsynSave() {
        AyMood ayMood = new AyMood();
        ayMood.setId("2");
        ayMood.setUserId("2");
        ayMood.setPraiseNum(0);
        ayMood.setContent("第二条MOOD！！！");
        ayMood.setPublishTime(new Date());
        String msg = ayMoodService.asynSave(ayMood);

        System.out.println("异步发表的说说：" + msg);
        logger.info("异步发表的说说：" + msg);
    }

    @Test
    public void testAsync() {
        long startTime = System.currentTimeMillis();
        logger.info("第一次查询所有用户");
        List<AyUser> ayUserList1 = ayUserService.findAll();
        logger.info("第二次查询所有用户");
        List<AyUser> ayUserList2 = ayUserService.findAll();
        logger.info("第三次查询所有用户");
        List<AyUser> ayUserList3 = ayUserService.findAll();
        long endTime = System.currentTimeMillis();

        logger.info("总消耗：" + (endTime-startTime) + "毫秒");
    }

    @Test
    public void testAsync2() throws Exception {
        long startTime = System.currentTimeMillis();
        logger.info("Async第一次查询所有用户");
        Future<List<AyUser>> ayUserList1 = ayUserService.findAsynAll();
        logger.info("Async第二次查询所有用户");
        Future<List<AyUser>> ayUserList2 = ayUserService.findAsynAll();
        logger.info("Async第三次查询所有用户");
        Future<List<AyUser>> ayUserList3 = ayUserService.findAsynAll();
        while (true) {
            if (ayUserList1.isDone() && ayUserList2.isDone() && ayUserList3.isDone()) {
                break;
            } else {
                Thread.sleep(10);
            }
        }
        long endTime = System.currentTimeMillis();

        logger.info("总消耗：" + (endTime-startTime) + "毫秒");
    }

    @Test
    public void testMongoDB() {
        AyUserAttachmentRel ayUserAttachmentRel = new AyUserAttachmentRel();
        ayUserAttachmentRel.setId("1");
        ayUserAttachmentRel.setUserId("1");
        ayUserAttachmentRel.setFileName("个人简历.doc");
        ayUserAttachmentRelService.save(ayUserAttachmentRel);
        logger.info("保存成功！");
    }
}
