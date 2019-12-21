package com.stnb.myspringboot.service;

import com.stnb.myspringboot.domain.AyMood;

/**
 * mood 服务层
 * @author 11299
 */
public interface AyMoodService {

    AyMood save(AyMood ayMood);

    /**
     * 异步消费模式
     * @param ayMood
     * @return
     */
    String asynSave(AyMood ayMood);
}
