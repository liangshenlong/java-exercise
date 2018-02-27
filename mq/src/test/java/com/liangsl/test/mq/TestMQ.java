package com.liangsl.test.mq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.jms.Destination;
import javax.jms.JMSException;

/**
 * TODO 此处启动获取注解ContextConfiguration失败,引入activemq-all包的问题，使用activemq-core则无问题
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-mq.xml")
public class TestMQ{
    @Autowired
    private Producer producer;
    @Autowired
    private Consumer consumer;
    @Autowired
    private Destination defaultQueue;

    @Test
    public void testSend(){
        producer.sendMessage("hello world!111111");
    }

    @Test
    public void testRecive() throws JMSException {
        consumer.receive(defaultQueue);
    }
}
