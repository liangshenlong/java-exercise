package com.liangsl.java.web;

import com.liangsl.java.dao.UserMapper;
import com.liangsl.java.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;

    //添加一个日志器
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping("index")
    public String index(ModelMap modelMap){
        logger.info("the first jsp pages");
        modelMap.put("name","王五");
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setName("李四");
        userMapper.save(user);
        return "index";
    }
}
