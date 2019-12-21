package com.stnb.ayuserservice.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.stnb.ayuserapi.api.AyUserDubboService;
import com.stnb.ayuserapi.domain.AyUser;

/**
 * @author 11299
 */
@Service(version = "1.0")
public class AyUserDubboServiceImpl implements AyUserDubboService {
    @Override
    public AyUser findByUserNameAndPassword(String name, String password) {
        AyUser ayUser = new AyUser();
        ayUser.setName("root1");
        ayUser.setPassword("123456");

        return ayUser;
    }
}
