package cn.kgc.ssm.service;

import cn.kgc.ssm.bean.User;

import java.util.List;


public interface UserService {
    public User getUser(String userCode);
    public List<User> getUserList(String userName, Integer userRole);
    public Integer addUser(User user);
    public User getUserById(Integer id);
    public Integer updateUserById(User user);
}
