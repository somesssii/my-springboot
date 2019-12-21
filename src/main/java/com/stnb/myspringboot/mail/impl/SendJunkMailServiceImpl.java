package com.stnb.myspringboot.mail.impl;

import com.stnb.myspringboot.domain.AyUser;
import com.stnb.myspringboot.mail.SendJunkMailService;
import com.stnb.myspringboot.service.AyUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import java.util.List;

/**
 * @author 11299
 */
@Service
public class SendJunkMailServiceImpl implements SendJunkMailService {

    @Autowired
    JavaMailSender javaMailSender;

    @Resource
    private AyUserService ayUserService;

    @Value("${spring.mail.username}")
    private String from;

    private static final Logger logger = LogManager.getLogger(SendJunkMailServiceImpl.class);


    @Override
    public boolean sendJunkMail(List<AyUser> ayUserList) {

        try{
           if (ayUserList == null || ayUserList.size() <= 0) {
               return Boolean.FALSE;
           }
           for (AyUser ayUser: ayUserList) {
               MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
               MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
               //邮件发送方
               message.setFrom(from);
               //邮件主题
               message.setSubject("Switch 开买！！！");
               //邮件接收方
               message.setTo("1129901556@qq.com");
               //邮件内容
               message.setText(ayUser.getName() + ",你知道吗，国行switch开卖了！！");
               this.javaMailSender.send(mimeMessage);
           }
        } catch (Exception e) {
            logger.error("sendJunkMail error and ayUser=%s",ayUserList,e);
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }
}
