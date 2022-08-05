package com.xmu.mapper;

import com.xmu.pojo.Administrator;
import com.xmu.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface AdministratorMapper {

    Administrator selectByAdministratorIDAndPassword(@Param("adminID")String adminID, @Param("password")String password);
}
