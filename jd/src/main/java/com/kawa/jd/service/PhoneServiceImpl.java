package com.kawa.jd.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kawa.jd.entity.Phone;
import com.kawa.jd.mpper.JDMapper;

@Service
public class PhoneServiceImpl implements PhoneService {
	
	@Resource
	private JDMapper mapper;

	@Override
	public void savePhone(Phone phone) {
		 mapper.save(phone);
	}

}
