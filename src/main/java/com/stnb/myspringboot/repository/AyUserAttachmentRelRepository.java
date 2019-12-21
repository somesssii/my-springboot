package com.stnb.myspringboot.repository;

import com.stnb.myspringboot.model.AyUserAttachmentRel;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * 描述：用户附件Repository
 * @author 11299
 */
public interface AyUserAttachmentRelRepository
        extends MongoRepository<AyUserAttachmentRel,String> {

}
