package com.oez.springboot.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CcmaCom {
	
	@Autowired
	CcmaComRepo ccmaComRepo;

	public List SelectSQLMap(String sSQL){
		return ccmaComRepo.SelectSQLMap(sSQL);
	}
}
