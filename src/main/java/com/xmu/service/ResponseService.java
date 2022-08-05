package com.xmu.service;

import com.xmu.mapper.ResponseMapper;
import com.xmu.pojo.Response;
import com.xmu.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class ResponseService {

    SqlSessionFactory factory= SqlSessionFactoryUtils.getSqlSessionFactory();


    /**
     * 根据需求号获取该需求的响应列表
     * @param demandNumber
     * @return
     */
    public List<Response> selectByDemandNumber(String demandNumber){
        SqlSession sqlSession=factory.openSession();
        ResponseMapper mapper=sqlSession.getMapper(ResponseMapper.class);
        List<Response> responses =mapper.selectByDemandNumber(demandNumber);
        sqlSession.close();
        return responses;
    }

    /**
     * 添加响应
     * @param response
     */
    public void addResponse(Response response){
        SqlSession sqlSession=factory.openSession();
        ResponseMapper mapper=sqlSession.getMapper(ResponseMapper.class);
        mapper.add(response);
        sqlSession.commit();
        sqlSession.close();
    }

}
