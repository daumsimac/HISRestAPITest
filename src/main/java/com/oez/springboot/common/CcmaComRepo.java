package com.oez.springboot.common;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface CcmaComRepo {

	List SelectSQLMap(String sSQL);
}
