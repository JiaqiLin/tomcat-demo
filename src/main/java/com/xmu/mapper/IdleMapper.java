package com.xmu.mapper;

import com.xmu.pojo.Idle;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IdleMapper {
    //获取所有上架的闲置
    List<Idle> selectAllReleased();
    //根据闲置号获取上架的闲置
    Idle selectReleasedByIdleNumber(String idleNumber);
    //添加闲置
    void add(Idle idle);
    //根据闲置号下架闲置
    void offShelvesByIdleNumber(String idleNumber);
    //根据闲置号删除闲置，这里不是真删除，是用户端的删除
    void invisibleByIdleNumber(String idleNumber);
    //根据用户号获取该用户上架的闲置
    List<Idle> selectReleasedByUserID(String userID);
    //根据用户号获取该用户下架的闲置
    List<Idle> selectOffShelvesByUserID(String userID);
    //根据闲置号下架闲置
    void upShelvesByIdleNumber(String idleNumber);
    //根据关键词查询上架的所有闲置
    List<Idle> selectReleasedByKeyword(String keyword);
    //根据关键词分页查询上架的所有闲置
    List<Idle> selectReleasedByPage(@Param("begin") int begin,@Param("size") int size,@Param("keyword") String keyword);
    //根据关键词查询上架的闲置的数量
    int selectReleasedTotalCount(String keyword);




}
