package com.stnb.myspringboot.mail;

import com.stnb.myspringboot.domain.AyUser;

import java.util.List;

/**
 * 描述：发送用户邮件服务
 * @author 11299
 */
public interface SendJunkMailService {

    boolean sendJunkMail(List<AyUser> ayUser);
}
