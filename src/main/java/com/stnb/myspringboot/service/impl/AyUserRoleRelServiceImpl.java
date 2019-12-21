package com.stnb.myspringboot.service.impl;

import com.stnb.myspringboot.domain.AyUserRoleRel;
import com.stnb.myspringboot.repository.AyUserRoleRelRepository;
import com.stnb.myspringboot.service.AyUserRoleRelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户角色关联Service 实现层
 * @author 11299
 */
@Service
public class AyUserRoleRelServiceImpl implements AyUserRoleRelService {

    @Resource
    private AyUserRoleRelRepository ayUserRoleRelRepository;

    @Override
    public List<AyUserRoleRel> findByUserId(String userId) {
        return ayUserRoleRelRepository.findByUserId(userId) ;
    }
}
