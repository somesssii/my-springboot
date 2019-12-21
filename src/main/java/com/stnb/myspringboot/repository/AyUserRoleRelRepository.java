package com.stnb.myspringboot.repository;

import com.stnb.myspringboot.domain.AyRole;
import com.stnb.myspringboot.domain.AyUserRoleRel;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 描述：用户角色关联Repository
 * @author 11299
 */
public interface AyUserRoleRelRepository extends JpaRepository<AyUserRoleRel,String> {

    List<AyUserRoleRel> findByUserId(@Param("userId") String userID);
}
