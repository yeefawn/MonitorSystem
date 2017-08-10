package com.yeewenfag.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext*.xml")
public class HttpUtilTest {

    @Autowired
    private HttpUtil httpUtil;

    @Test
    public void testDoPostSSL(){
        String params = "CorpID=GZJS001588&Pwd=zt@668&Mobile=17620646814&Content=POST请求清楚是什么情况了&Cell=&SendTime=";
        //String result = httpUtil.doPostSSL("https://sdk2.028lk.com/sdk2/BatchSend2.aspx", params, "GBK");

        //System.out.println("返回结果:" + result);
    }
}