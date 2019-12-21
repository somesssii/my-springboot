package com.stnb.myspringboot.service.impl;

import com.stnb.myspringboot.domain.AyMood;
import com.stnb.myspringboot.jms.AyMoodProducer;
import com.stnb.myspringboot.repository.AyMoodRepository;
import com.stnb.myspringboot.service.AyMoodService;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;

/**
 * mood 服务实现层
 * @author 11299
 */
@Service
public class AyMoodServiceImpl implements AyMoodService {

    @Resource
    private AyMoodRepository ayMoodRepository;

    /**
     * 队列
     */
    private static Destination destination = new ActiveMQQueue("ay.queue.asyn.save");

    @Resource
    private AyMoodProducer ayMoodProducer;


    @Override
    public AyMood save(AyMood ayMood) {
        return ayMoodRepository.save(ayMood);
    }

    @Override
    public String asynSave(AyMood ayMood) {
        /**
         * 往队列ay.queue.asyn.save推送消息，消息内容为说说实体
         */
        ayMoodProducer.sendMessage(destination, ayMood);
        return "success";
    }
}
