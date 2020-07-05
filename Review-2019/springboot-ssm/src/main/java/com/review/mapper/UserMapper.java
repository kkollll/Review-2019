package com.review.mapper;

import com.review.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;


public interface UserMapper {

    @Select("select * from user")
    public List<User> findAll();

    @Insert("insert into user (name, birthday,address) values(#{user.name},#{user.birthday},#{user.address})")
    public void insert(@Param("user") User user);

    @Update("update user set name=#{user.name},birthday=#{user.birthday},address=#{user.address} where id=#{user.id}")
    public void update(@Param("user")User user);

    @Delete("delete from user where id = #{user.id}")
    public void delete(Integer id);
}
