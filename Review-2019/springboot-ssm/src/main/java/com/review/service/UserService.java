package com.review.service;

import com.review.pojo.User;

import java.util.List;

public interface UserService {
    public List<User> findAll();

    public void insert(User user);

    public void update(User user);

    public void delete(Integer id);
}
