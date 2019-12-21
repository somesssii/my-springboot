package com.stnb.ayuserapi.api;

import com.stnb.ayuserapi.domain.AyUser;

/**
 * 用户接口
 * @author 11299
 */
public interface AyUserDubboService {

    AyUser findByUserNameAndPassword(String name, String password);
}
