package com.xmu.service;

import com.xmu.mapper.DemandMapper;
import com.xmu.mapper.IdleMapper;
import com.xmu.pojo.Demand;
import com.xmu.pojo.Idle;
import com.xmu.pojo.PageBean;
import com.xmu.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class DemandService {
    SqlSessionFactory factory= SqlSessionFactoryUtils.getSqlSessionFactory();

    /**
     * 查询所有上架的需求
     * @return 所有上架的需求
     */
    public List<Demand> selectAllReleased(){
        SqlSession sqlSession=factory.openSession();
        DemandMapper mapper=sqlSession.getMapper(DemandMapper.class);
        List<Demand> demands=mapper.selectAllReleased();
        sqlSession.close();
        return demands;
    }

    /**
     *根据需求号查询上架的需求
     * @param demandNumber
     * @return 一个上架的需求
     */
    public Demand selectReleasedByDemandNumber(String demandNumber){
        SqlSession sqlSession=factory.openSession();
        DemandMapper mapper=sqlSession.getMapper(DemandMapper.class);
        Demand demand=mapper.selectReleasedByDemandNumber(demandNumber);
        sqlSession.close();
        return demand;
    }

    /**
     * 根据用户账号查询该用户所有上架的需求（用于响应？好像不用）
     * @param userID
     * @return 该用户所有上架的需求
     */
    public List<Demand> selectByUserID(String userID){
        SqlSession sqlSession=factory.openSession();
        DemandMapper mapper=sqlSession.getMapper(DemandMapper.class);
        List<Demand> demands=mapper.selectByUserID(userID);
        sqlSession.close();
        return demands;
    }

    /**
     * 添加需求
     * @param demand
     */
    public void add(Demand demand){
        SqlSession sqlSession=factory.openSession();
        DemandMapper mapper=sqlSession.getMapper(DemandMapper.class);
        mapper.add(demand);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 根据需求号下架需求
     * @param demandNumber
     */
    public void offShelvesByDemandNumber(String demandNumber){
        SqlSession sqlSession=factory.openSession();
        DemandMapper mapper=sqlSession.getMapper(DemandMapper.class);
        mapper.offShelvesByDemandNumber(demandNumber);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 根据需求号上架需求
     * @param demandNumber
     */
    public void upShelvesByDemandNumber(String demandNumber){
        SqlSession sqlSession=factory.openSession();
        DemandMapper mapper=sqlSession.getMapper(DemandMapper.class);
        mapper.upShelvesByDemandNumber(demandNumber);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 根据需求号“删除”需求，这里不是真删除，是用户端的删除，管理端依旧可见
     * @param demandNumber
     */
    public void invisibleByDemandNumber(String demandNumber){
        SqlSession sqlSession=factory.openSession();
        DemandMapper mapper=sqlSession.getMapper(DemandMapper.class);
        mapper.invisibleByDemandNumber(demandNumber);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 根据用户账号查询该用户所有上架的需求
     * @param userID
     * @return 该用户所有上架的需求
     */
    public List<Demand> selectReleasedByUserID(String userID){
        SqlSession sqlSession=factory.openSession();
        DemandMapper mapper=sqlSession.getMapper(DemandMapper.class);
        List<Demand> demands= mapper.selectReleasedByUserID(userID);
        sqlSession.close();
        return demands;
    }

    /**
     * 根据用户账号查询该用户所有下架的需求
     * @param userID
     * @return 该用户所有下架的需求
     */
    public List<Demand> selectOffShelvesByUserID(String userID){
        SqlSession sqlSession=factory.openSession();
        DemandMapper mapper=sqlSession.getMapper(DemandMapper.class);
        List<Demand> demands= mapper.selectOffShelvesByUserID(userID);
        sqlSession.close();
        return demands;
    }

    /**
     * 根据关键词查询所有上架的需求
     * @param keyword
     * @return
     */
    public List<Demand> selectReleasedByKeyword(String keyword){
        SqlSession sqlSession=factory.openSession();
        DemandMapper mapper=sqlSession.getMapper(DemandMapper.class);
        List<Demand> demands= mapper.selectReleasedByKeyword(keyword);
        sqlSession.close();
        return demands;
    }

    /**
     * 根据关键词分页查询上架的需求+上架的总需求数
     * @param currentPage 当前页
     * @param pageSize 一页的需求数
     * @param keyword  关键字
     * @return 分页的上架的需求+上架的总需求数
     */
    public PageBean<Demand> selectReleasedByPage(int currentPage,int pageSize,String keyword){
        SqlSession sqlSession=factory.openSession();
        DemandMapper mapper=sqlSession.getMapper(DemandMapper.class);
        int begin=(currentPage-1)*pageSize;
        int size=pageSize;
        List<Demand> rows=mapper.selectReleasedByPage(begin,size,keyword);
        int totalCount=mapper.selectReleasedTotalCount(keyword);
        PageBean<Demand> pageBean=new PageBean<Demand>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);
        return pageBean;
    }
}
