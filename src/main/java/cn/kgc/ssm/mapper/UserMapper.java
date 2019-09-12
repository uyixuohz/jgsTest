package cn.kgc.ssm.mapper;

import cn.kgc.ssm.bean.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    /**
     * 登录
     * @param userCode
     * @return
     */
    public User getUser(@Param("userCode") String userCode)throws Exception;
    /**
     * 分页查询
     * @param userName
     * @param userRole
     * @return
     */
    public List<User> getUserList(@Param("userName")String userName,
                                  @Param("userRole")Integer userRole)throws Exception;
    /**
     * 新增用户
     * @param user
     * @return
     */
    public Integer addUser(User user)throws Exception;
    /**
     * 根据用户id查询，返回对象
     * @param id
     * @return
     * @throws Exception
     */
    public User getUserById(Integer id)throws Exception;

    public Integer updateUserById(User user)throws Exception;


}
