package com.stnb.myspringboot.repository;

import com.stnb.myspringboot.domain.AyRole;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 描述：用户角色Repository
 * @author 11299
 */
public interface AyRoleRepository extends JpaRepository<AyRole,String> {
}
