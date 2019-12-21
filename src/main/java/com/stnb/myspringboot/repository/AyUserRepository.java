package com.stnb.myspringboot.repository;

import com.stnb.myspringboot.domain.AyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

/**
 * @author 11299
 */
public interface AyUserRepository extends JpaRepository<AyUser,String> {

    /**
     * 通过名字相等查询，参数为name
     * 相当于：select u from ay_user u where u.name = ?1
     * @param name
     * @return
     */
    List<AyUser> findByName(String name);

    /**
     * 通过名字like查询，参数为name
     * 相当于：select u from ay_user u where u.name like ?1
     * @param name
     * @return
     */
    List<AyUser> findByNameLike(String name);

    /**
     * 通过主键id集合查询 ，参数为id集合
     * 相当于：select u from ay_user u where id in(?,?,?)
     * @param ids
     * @return
     */
    List<AyUser> findByIdIn(Collection<String> ids);
}
