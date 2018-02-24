package com.liangsl.test.mq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.TextMessage;

@Component
public class Consumer {
    @Autowired
    private JmsTemplate jmsTemplate;

    public void receive(Destination destination) throws JMSException {
        TextMessage textMessage= (TextMessage) jmsTemplate.receive(destination);
        System.out.println("Consumer receive from "+destination.toString()+":"+textMessage.getText());
    }

}
