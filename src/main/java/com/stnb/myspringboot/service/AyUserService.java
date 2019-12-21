package com.stnb.myspringboot.service;

import com.stnb.myspringboot.domain.AyUser;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.concurrent.Future;

/**
 * @author 11299
 * 服务层接口类
 */
public interface AyUserService {

    AyUser findById(String id);

    List<AyUser> findAll();

    AyUser save(AyUser ayUser);

    void delete(String id);

    /**
     *分页
     */
    List<AyUser> findAll(Pageable pageable);

    List<AyUser> findByName(String name);
    List<AyUser> findByNameLike(String name);
    List<AyUser> findByIdIn(String name);

    AyUser findByNameAndPassword(String name,String password);

    //异步查询
    Future<List<AyUser>> findAsynAll();

    AyUser findByNameAndPasswordRetry(String name,String password);
}
