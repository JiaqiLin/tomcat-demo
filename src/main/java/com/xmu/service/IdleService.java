package com.xmu.service;

import com.xmu.mapper.IdleMapper;
import com.xmu.pojo.Idle;
import com.xmu.pojo.PageBean;
import com.xmu.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class IdleService {
    SqlSessionFactory factory= SqlSessionFactoryUtils.getSqlSessionFactory();


    /**
     * 查询所有上架的闲置
     * @return所有上架的闲置
     */
    public List<Idle> selectAllReleased(){
        SqlSession sqlSession=factory.openSession();
        IdleMapper mapper=sqlSession.getMapper(IdleMapper.class);
        List<Idle> commodities=mapper.selectAllReleased();
        sqlSession.close();
        return commodities;
    }

    /**
     * 根据闲置号查询上架的闲置
     * @param idleNumber
     * @return一个具体闲置
     */
    public Idle selectReleasedByIdleNumber(String idleNumber){
        SqlSession sqlSession=factory.openSession();
        IdleMapper mapper=sqlSession.getMapper(IdleMapper.class);
        Idle idle =mapper.selectReleasedByIdleNumber(idleNumber);
        sqlSession.close();
        return idle;
    }

    /**
     * 添加闲置
     * @param idle
     */
    public void add(Idle idle) {
        SqlSession sqlSession=factory.openSession();
        IdleMapper mapper=sqlSession.getMapper(IdleMapper.class);
        mapper.add(idle);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 根据闲置号下架闲置
     * @param idleNumber
     */
    public void offShelvesByIdleNumber(String idleNumber){
        SqlSession sqlSession=factory.openSession();
        IdleMapper mapper=sqlSession.getMapper(IdleMapper.class);
        mapper.offShelvesByIdleNumber(idleNumber);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 根据闲置号上架闲置
     * @param idleNumber
     */
    public void upShelvesByIdleNumber(String idleNumber){
        SqlSession sqlSession=factory.openSession();
        IdleMapper mapper=sqlSession.getMapper(IdleMapper.class);
        mapper.upShelvesByIdleNumber(idleNumber);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 根据闲置号删除闲置，用户端删除
     * @param idleNumber
     */
    public void invisibleByIdleNumber(String idleNumber){
        SqlSession sqlSession=factory.openSession();
        IdleMapper mapper=sqlSession.getMapper(IdleMapper.class);
        mapper.invisibleByIdleNumber(idleNumber);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 根据用户号查询该用户所有上架的闲置
     * @param userID
     * @return
     */
    public List<Idle> selectReleasedByUserID(String userID){
        SqlSession sqlSession=factory.openSession();
        IdleMapper mapper=sqlSession.getMapper(IdleMapper.class);
        List<Idle> idles= mapper.selectReleasedByUserID(userID);
        sqlSession.close();
        return idles;
    }

    /**
     * 根据用户号查询该用户所有下架的闲置
     * @param userID
     * @return
     */
    public List<Idle> selectOffShelvesByUserID(String userID){
        SqlSession sqlSession=factory.openSession();
        IdleMapper mapper=sqlSession.getMapper(IdleMapper.class);
        List<Idle> idles= mapper.selectOffShelvesByUserID(userID);
        sqlSession.close();
        return idles;
    }

    /**
     * 根据关键词查询所有相关的上架闲置
     * @param keyword
     * @return
     */
    public List<Idle> selectReleasedByKeyword(String keyword){
        SqlSession sqlSession=factory.openSession();
        IdleMapper mapper=sqlSession.getMapper(IdleMapper.class);
        List<Idle> idles= mapper.selectReleasedByKeyword(keyword);
        sqlSession.close();
        return idles;
    }

    /**
     * 根据关键词分页查询所有相关的上架闲置+上架闲置总数
     * @param currentPage
     * @param pageSize
     * @param keyword
     * @return
     */
    public PageBean<Idle> selectReleasedByPage(int currentPage,int pageSize,String keyword){
        SqlSession sqlSession=factory.openSession();
        IdleMapper mapper=sqlSession.getMapper(IdleMapper.class);
        int begin=(currentPage-1)*pageSize;
        int size=pageSize;
        List<Idle> rows=mapper.selectReleasedByPage(begin,size,keyword);
        int totalCount=mapper.selectReleasedTotalCount(keyword);
        PageBean<Idle> pageBean=new PageBean<Idle>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);
        return pageBean;
    }


}
