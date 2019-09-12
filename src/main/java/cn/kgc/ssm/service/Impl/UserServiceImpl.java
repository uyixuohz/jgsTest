package cn.kgc.ssm.service.Impl;

import cn.kgc.ssm.bean.User;
import cn.kgc.ssm.mapper.UserMapper;
import cn.kgc.ssm.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private UserMapper userMapper;

    @Override
    public User getUser(String userCode) {
        User user =null;
        try {
            user = userMapper.getUser(userCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> getUserList(String userName, Integer userRole) {
        List<User> userList = null;
        try {
            userList = userMapper.getUserList(userName, userRole);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public Integer addUser(User user) {
        Integer res =0;
        try {
            res = userMapper.addUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public User getUserById(Integer id) {
        User user= null;
        try {
            user = userMapper.getUserById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public Integer updateUserById(User user) {
        Integer res=0;
        try {
            res=userMapper.updateUserById(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
}
