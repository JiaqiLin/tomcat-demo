package com.xmu.mapper;

import com.xmu.pojo.User;
import com.xmu.pojo.VerifyInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {

    //根据用户号和密码查询用户
    User selectByUserIDAndPassword(@Param("userID")String userID, @Param("password")String password);
    //根据用户号查询用户
    User selectByUserID(String userID);
    //根据用户号在学籍表中查询用户
    User selectByUserIDInSchoolRoll(String userID);
    //根据用户号和注册状态在学籍表中查询用户
    User selectByUserIDAndRegisterStatusInSchoolRoll(String userID);
    //添加用户
    void add(User user);
    //更新用户信息
    void updateUserInfo(User user);
    //查询所有用户
    List<User> getAllUsers();

    //添加认证申请
    void addVerifyApply(@Param("userID")String userID,@Param("studentCardFront")String studentCardFront,@Param("studentCardContrary")String studentCardContrary);

    //通过用户ID查找认证申请
    VerifyInfo selectVerifyInfoByUserID(String userID);

    void verifyNotPass(String userID);

    void verifyPass(VerifyInfo verifyInfo);

    List<VerifyInfo> selectAllVerifyInfo();

    //分页查询
    List<User> selectByPage(@Param("begin") int begin, @Param("size") int size);

    //分页条件查询
    List<User> selectByPageAndCondition(@Param("begin") int begin, @Param("size") int size,@Param("user")User user);

    //查询符合条件的总记录数
    int selectTotalCountByCondition(User user);

    //查询总记录数
    @Select("select count(*) from user")
    int selectTotalCount();

    /**
     * 根据用户的id删除
     * @param user
     */
    void deleteByUser(User user);

}
