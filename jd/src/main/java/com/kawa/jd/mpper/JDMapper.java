package com.kawa.jd.mpper;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kawa.jd.entity.Phone;

public interface JDMapper extends JpaRepository<Phone, Long> {

}