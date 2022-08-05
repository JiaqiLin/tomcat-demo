package com.xmu.service;

import com.xmu.mapper.AdministratorMapper;
import com.xmu.mapper.UserMapper;
import com.xmu.pojo.Administrator;
import com.xmu.pojo.User;
import com.xmu.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class AdministratorService {
    SqlSessionFactory factory= SqlSessionFactoryUtils.getSqlSessionFactory();


    public Administrator login(String administratorID, String password){
        SqlSession sqlSession=factory.openSession();
        AdministratorMapper mapper=sqlSession.getMapper(AdministratorMapper.class);
        Administrator administrator=mapper.selectByAdministratorIDAndPassword(administratorID,password);
        sqlSession.close();
        return administrator;
    }
}
