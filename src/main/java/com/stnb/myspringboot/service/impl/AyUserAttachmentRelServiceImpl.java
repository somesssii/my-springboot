package com.stnb.myspringboot.service.impl;

import com.stnb.myspringboot.model.AyUserAttachmentRel;
import com.stnb.myspringboot.repository.AyUserAttachmentRelRepository;
import com.stnb.myspringboot.service.AyUserAttachmentRelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 描述：用户附件实现层
 * @author 11299
 */
@Service
public class AyUserAttachmentRelServiceImpl implements AyUserAttachmentRelService {

    @Resource
    private AyUserAttachmentRelRepository ayUserAttachmentRelRepository;

    @Override
    public AyUserAttachmentRel save(AyUserAttachmentRel ayUserAttachmentRel) {
        return ayUserAttachmentRelRepository.save(ayUserAttachmentRel);
    }
}
