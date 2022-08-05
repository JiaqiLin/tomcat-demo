package com.xmu.service;

import com.xmu.mapper.UserMapper;
import com.xmu.pojo.PageBean;
import com.xmu.pojo.User;
import com.xmu.pojo.VerifyInfo;
import com.xmu.util.SqlSessionFactoryUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class UserService {
    SqlSessionFactory factory= SqlSessionFactoryUtils.getSqlSessionFactory();

    /**
     * 根据用户号和密码查询用户
     * @param userID
     * @param password
     * @return
     */
    public User selectByUserIDAndPassword(String userID,String password){
        SqlSession sqlSession=factory.openSession();
        UserMapper mapper=sqlSession.getMapper(UserMapper.class);
        password= DigestUtils.md5Hex(userID+password);
        User user=mapper.selectByUserIDAndPassword(userID,password);
        sqlSession.close();
        return user;
    }

    /**
     * 根据用户号查询用户
     * @param userID
     * @return
     */
    public User selectByUserID(String userID){
        SqlSession sqlSession=factory.openSession();
        UserMapper mapper=sqlSession.getMapper(UserMapper.class);
        User user=mapper.selectByUserID(userID);
        sqlSession.close();
        return user;
    }

    /**
     * 根据用户号在学籍表中查询用户
     * @param userID
     * @return
     */
    public User selectByUserIDInSchoolRoll(String userID){
        SqlSession sqlSession=factory.openSession();
        UserMapper mapper=sqlSession.getMapper(UserMapper.class);
        User user=mapper.selectByUserIDInSchoolRoll(userID);
        sqlSession.close();
        return user;
    }

    /**
     * 根据用户号和注册状态在学籍表中查询用户
     * @param userID
     * @return
     */
    public User selectByUserIDAndRegisterStatusInSchoolRoll(String userID){
        SqlSession sqlSession=factory.openSession();
        UserMapper mapper=sqlSession.getMapper(UserMapper.class);
        User user=mapper.selectByUserIDAndRegisterStatusInSchoolRoll(userID);
        sqlSession.close();
        return user;
    }

    /**
     * 添加用户
     * @param user
     */
    public void add(User user){
        SqlSession sqlSession=factory.openSession();
        UserMapper mapper=sqlSession.getMapper(UserMapper.class);
        //使用md5加密
        user.setPassword(DigestUtils.md5Hex(user.getUserID()+user.getPassword()));
        mapper.add(user);
        sqlSession.commit();
        sqlSession.close();

    }

    /**
     * 更新用户信息
     * @param user
     */
    public void updateUserInformation(User user){
        SqlSession sqlSession=factory.openSession();
        UserMapper mapper=sqlSession.getMapper(UserMapper.class);
        mapper.updateUserInfo(user);
        sqlSession.commit();
        sqlSession.close();

    }

    /**
     * 查询所有用户
     * @return
     */
    public List<User> selectAllUsers() {
        SqlSession sqlSession=factory.openSession();
        UserMapper mapper=sqlSession.getMapper(UserMapper.class);
        List<User> allUsers = mapper.getAllUsers();
        sqlSession.close();
        return allUsers;
    }

    /**
     * 上传认证图片
     * @param userID
     * @param studentCardFront
     * @param studentCardContrary
     */
    public void addVerifyApply(String userID,String studentCardFront,String studentCardContrary){
        SqlSession sqlSession=factory.openSession();
        UserMapper mapper=sqlSession.getMapper(UserMapper.class);
        mapper.addVerifyApply(userID,studentCardFront,studentCardContrary);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 根据用户ID查找认证申请
     * @param userID
     * @return
     */
    public VerifyInfo selectVerifyInfoByUserID(String userID){
        SqlSession sqlSession=factory.openSession();
        UserMapper mapper=sqlSession.getMapper(UserMapper.class);
        VerifyInfo verifyInfo = mapper.selectVerifyInfoByUserID(userID);
        sqlSession.close();
        return verifyInfo;
    }

    /**
     * 查询所有待审核的信息
     * @return
     */
    public List<VerifyInfo> selectAllVerifyInfo() {
        SqlSession sqlSession=factory.openSession();
        UserMapper mapper=sqlSession.getMapper(UserMapper.class);
        List<VerifyInfo> verifyInfos = mapper.selectAllVerifyInfo();
        sqlSession.close();
        return verifyInfos;
    }

    public void verifyPass(VerifyInfo verifyInfo){
        SqlSession sqlSession=factory.openSession();
        UserMapper mapper=sqlSession.getMapper(UserMapper.class);
        mapper.verifyPass(verifyInfo);
        sqlSession.commit();
        sqlSession.close();
    }

    public void verifyNotPass(String userID){
        SqlSession sqlSession=factory.openSession();
        UserMapper mapper=sqlSession.getMapper(UserMapper.class);
        mapper.verifyNotPass(userID);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 根据用户id删除用户
     * @return
     */
    public void deleteByUser(User user) {
        SqlSession sqlSession=factory.openSession();
        UserMapper mapper=sqlSession.getMapper(UserMapper.class);
        mapper.deleteByUser(user);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 分页查询
     * @param currentPage 当前页码
     * @param pageSize    每页展示的条数
     * @return
     */
    public PageBean<User> selectByPage(int currentPage,int pageSize) {
        //1、获取sqlSession对象
        SqlSession sqlSession=factory.openSession();
        //2、获取UserMapper
        UserMapper mapper=sqlSession.getMapper(UserMapper.class);
        //3、计算索引
        int begin=(currentPage - 1) * pageSize;
        //计算查询行数
        int size=pageSize;

        //4、查询当前页数据
        List<User> rows = mapper.selectByPage(begin,size);

        //5、查询总记录数目
        int totalCount = mapper.selectTotalCount();

        //6、封装PageBean对象
        PageBean<User> pageUser = new PageBean<User>();
        pageUser.setRows(rows);
        pageUser.setTotalCount(totalCount);

        //7、释放资源
        sqlSession.close();

        return pageUser;
    }

    /**
     * 分页条件查询
     * @param currentPage
     * @param pageSize
     * @param user
     * @return
     */
    public PageBean<User> selectByPageAndCondition(int currentPage,int pageSize,User user) {
        //1、获取sqlSession对象
        SqlSession sqlSession=factory.openSession();
        //2、获取UserMapper
        UserMapper mapper=sqlSession.getMapper(UserMapper.class);
        //3、计算索引
        int begin=(currentPage - 1) * pageSize;
        //计算查询行数
        int size=pageSize;

/*        //处理user条件，模糊表达式
        String academy = user.getAcademy();
        if(academy != null && academy.length()>0){
            user.setAcademy("%"+academy+"%");
        }*/

        //4、查询当前页数据
        List<User> rows = mapper.selectByPageAndCondition(begin,size,user);

        //5、查询符合条件的总记录数目
        int totalCount = mapper.selectTotalCountByCondition(user);

        //6、封装PageBean对象
        PageBean<User> pageUser = new PageBean<User>();
        pageUser.setRows(rows);
        pageUser.setTotalCount(totalCount);

        //7、释放资源
        sqlSession.close();

        return pageUser;
    }
    /**
     * 批量删除
     * 在这里实现一下user转user_id
     * @return
     */
/*    public void deleteByIDs(String[] ids) {
        //获取sqlSession对象
        SqlSession sqlSession=factory.openSession();
        //获取userMapper
        UserMapper mapper=sqlSession.getMapper(UserMapper.class);

        //使用方法
        mapper.deleteByIDs(ids);

        //提交事务
        sqlSession.commit();

        //释放资源
        sqlSession.close();
    }*/
    public void deleteByIDs(List<User> users) {
        //获取sqlSession对象
        SqlSession sqlSession=factory.openSession();
        //获取userMapper
        UserMapper mapper=sqlSession.getMapper(UserMapper.class);

        /*  String[] ids=new String[8];*/
        for (int i = 0; i < users.size(); i++) {
            mapper.deleteByUser(users.get(i));
        }
        //使用方法


        //提交事务
        sqlSession.commit();

        //释放资源
        sqlSession.close();
    }



}
