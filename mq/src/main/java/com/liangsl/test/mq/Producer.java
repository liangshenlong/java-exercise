package com.liangsl.test.mq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Destination;

@Component
public class Producer {
    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendMessage(String msg){
        String destination = jmsTemplate.getDefaultDestination().toString();
        System.out.println(Thread.currentThread().getName()+":向队列"+destination+"发送消息！---------------"+msg);
        jmsTemplate.send(session -> session.createTextMessage(msg));
    }

    public void sendMessage(Destination destination , String msg){
        System.out.println(Thread.currentThread().getName()+":向队列"+destination.toString()+"发送消息！");
        jmsTemplate.send(destination,session -> session.createTextMessage(msg));
    }

}
