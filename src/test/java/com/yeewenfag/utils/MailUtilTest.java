package com.yeewenfag.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring/applicationContext*.xml")
public class MailUtilTest {

    @Autowired
    private MailUtil mailUtil;

    @Test
    public void send() throws Exception {

        String recipients = "313554558@qq.com";
        String subject = "Java邮件测试";
        String content = "测试是否能接收到？";

        //mailUtil.send(recipients, subject, content);
    }

}