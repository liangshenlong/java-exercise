package com.liangsl.test.dao;

import com.liangsl.test.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Insert("insert into user(name,id) values(#{name},#{id})")
    public void save(User user);
    @Update("update user set name=#{name} where id=#{id}")
    public boolean updateById(User user);
    @Delete("delete from user where id=#{id}")
    public boolean deleteById(String id);
    @Select("select * from user where id=#{id}")
    public User findById(String id);
    @Select("select * from user")
    public List<User> findAll();
}
