package com.stnb.myspringboot.repository;

import com.stnb.myspringboot.domain.AyMood;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Mood repository
 * @author 11299
 */
public interface AyMoodRepository extends JpaRepository<AyMood,String> {


}
