package com.liangsl.test;

import com.liangsl.test.dao.UserMapper;
import com.liangsl.test.entity.User;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.UUID;

/**
 * Unit test for simple App.
 */
public class AppTest
{
    @org.junit.Test
   public void test(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext-dao.xml");
        UserMapper dao = ac.getBean(UserMapper.class);

        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setName("张三");
//        dao.save(user);

        User user1 = dao.findById("5fc2d2c5-ccd3-4d9d-a12c-6f6042b8a83b");
        System.out.println(user1.getName());
   }
}
