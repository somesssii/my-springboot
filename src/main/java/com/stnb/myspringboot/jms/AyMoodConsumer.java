package com.stnb.myspringboot.jms;

import com.stnb.myspringboot.domain.AyMood;
import com.stnb.myspringboot.service.AyMoodService;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 消费者
 * @author 11299
 */
@Component
public class AyMoodConsumer {

    @JmsListener(destination = "ay.queue")
    public void receiveQueue(String text) {
        System.out.println("用户发表说说{"+ text+"}成功！！！");
    }

    @Resource
    private AyMoodService ayMoodService;

    @JmsListener(destination = "ay.queue.asyn.save")
    public void receiveQueue(AyMood ayMood) {
        ayMoodService.save(ayMood);
    }
}
