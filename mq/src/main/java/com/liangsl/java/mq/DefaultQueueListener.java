package com.liangsl.java.mq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class DefaultQueueListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        TextMessage tm = (TextMessage) message;
        try {
            System.out.println("DefaultQueueListener onMessage() defaultQueueListener \t" + tm.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
