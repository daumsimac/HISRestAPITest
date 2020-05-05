package com.oez.springboot.entity;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchResultVO {
	private int totalCounter;
	private int pageNumber;
	private int pageSize;
	private int totalPage;

	private List<Object> resultList;
}
