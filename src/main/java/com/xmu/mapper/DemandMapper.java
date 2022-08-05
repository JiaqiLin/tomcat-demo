package com.xmu.mapper;

import com.xmu.pojo.Demand;
import org.apache.ibatis.annotations.Param;


import java.util.List;

public interface DemandMapper {

    //查询所有上架的需求
    List<Demand> selectAllReleased();

    //根据需求号查询已上架的需求
    Demand selectReleasedByDemandNumber(String demandNumber);

    //根据用户账号查询该用户所有上架的需求（用于响应？好像不用）
    List<Demand> selectByUserID(String userID);

    //添加需求
    void add(Demand demand);

    //根据需求号下架需求，需求status=0
    void offShelvesByDemandNumber(String demandNumber);

    //根据需求号“删除”需求，这里不是真删除，是用户端的删除，管理端依旧可见，需求status=-1
    void invisibleByDemandNumber(String demandNumber);

    //根据用户账号查询该用户所有上架的需求
    List<Demand> selectReleasedByUserID(String userID);

    //根据用户账号查询该用户所有下架的需求
    List<Demand> selectOffShelvesByUserID(String userID);

    //根据需求号上架需求，需求status=1
    void upShelvesByDemandNumber(String demandNumber);

    //根据关键词查询所有上架的需求
    List<Demand> selectReleasedByKeyword(String keyword);

    //根据关键词分页查询上架的需求
    List<Demand> selectReleasedByPage(@Param("begin") int begin, @Param("size") int size, @Param("keyword") String keyword);

    //根据关键词获取与关键词有关需求总数
    int selectReleasedTotalCount(String keyword);
}
