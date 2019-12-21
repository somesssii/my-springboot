package com.stnb.myspringboot.dao;

import com.stnb.myspringboot.domain.AyUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 用户DAO or Mapper
 * @author 11299
 */
@Mapper
public interface AyUserDao {

    /**
     * 描述：通过用户名和密码查询用户
     * @param name
     * @param password
     * @return
     */
    AyUser findByNameAndPassword(@Param("name") String name,
                                 @Param("password") String password);
}
