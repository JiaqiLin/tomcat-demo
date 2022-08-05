package com.xmu.mapper;

import com.xmu.pojo.Response;

import java.util.List;

public interface ResponseMapper {
    //根据需求号获取该需求的响应列表
    List<Response> selectByDemandNumber(String DemandNumber);

    //添加响应
    void add(Response response);

}
