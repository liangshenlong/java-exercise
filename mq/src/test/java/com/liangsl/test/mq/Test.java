package com.liangsl.test.mq;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDateTime;

public class Test {
    @org.junit.Test
    public void test(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath*:applicationContext-mq.xml");
        Producer producer = applicationContext.getBean(Producer.class);
        producer.sendMessage("hello world"+ LocalDateTime.now());
    }
}
