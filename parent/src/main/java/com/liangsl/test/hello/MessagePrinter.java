package com.liangsl.test.hello;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessagePrinter implements InitializingBean, DisposableBean, BeanNameAware, BeanFactoryAware {
    @Autowired
    private MessageService service;

    public MessagePrinter(MessageService service) {
        this.service = service;
    }

    public MessagePrinter(){}

    public void printMessage() {
        System.out.println(this.service.getMessage());
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {

    }

    @Override
    public void setBeanName(String s) {

    }

    @Override
    public void destroy() throws Exception {

    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}