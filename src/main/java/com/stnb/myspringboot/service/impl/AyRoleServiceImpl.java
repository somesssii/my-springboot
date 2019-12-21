package com.stnb.myspringboot.service.impl;

import com.stnb.myspringboot.domain.AyRole;
import com.stnb.myspringboot.repository.AyRoleRepository;
import com.stnb.myspringboot.service.AyRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 描述：用户角色Service实现层
 * @author 11299
 */
@Service
public class AyRoleServiceImpl implements AyRoleService {

    @Resource
    private AyRoleRepository ayRoleRepository;

    @Override
    public AyRole find(String id) {
        return ayRoleRepository.findById(id).get()  ;
    }
}
