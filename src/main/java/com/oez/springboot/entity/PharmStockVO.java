package com.oez.springboot.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PharmStockVO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9094615478511836711L;
	
	private String stkPumCod;
	private String estNamKor;
	private String estNamEng;
	private String stkQty;
	private String safWrnQty;
	private String safReqQty;
	private String safAutQty;
	private String odrQty;
	private String remQty;
	private String seeExpDte;
	private String estPcsDep;
	private String estPrmDep;
	private String estMidDep;
}
