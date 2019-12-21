package com.stnb.myspringboot.service;

import com.stnb.myspringboot.domain.AyUserRoleRel;

import java.util.List;

/**
 * 用户角色关联Service
 * @author 11299
 */
public interface AyUserRoleRelService {

    List<AyUserRoleRel> findByUserId(String userId);
}
