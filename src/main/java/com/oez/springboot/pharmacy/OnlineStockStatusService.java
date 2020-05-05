package com.oez.springboot.pharmacy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oez.springboot.common.CcmaCom;
//import com.oez.springboot.entity.SearchResultVO;
import com.oez.springboot.entity.PharmStockVO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OnlineStockStatusService {
	
	@Autowired
	CcmaCom COM;
	
	
	public List  DisplayPharmStock() {
		String sQuerySEL = "Select StkPumCod, EstNamKor, EstNamEng, StkQty, SafWrnQty, SafReqQty, SafAutQty, "
				+ "OdrQty, RemQty, SeeExpDte, EstPcsDep, EstPrmDep, EstMidDep   "
				+ "From	(Select StkPumCod, EstNamKor, EstNamEng, (StkEntQty - StkDlyQty + IsNull(ReqQty,0)) StkQty, "
				+ "StkSafWrn SafWrnQty, StkSafReq SafReqQty, StkSafAut SafAutQty, IsNull(OSP.OdrQty,0) OdrQty, "
				+ "(StkEntQty - StkDlyQty + IsNull(ReqQty,0)) RemQty, SeeExpDte, EstPcsDep, EstPrmDep, EstMidDep "
				+ "From ZStkInf WITH (NOLOCK) Left Outer Join (Select ReqDepCod, RedPumCod, Sum(RedReqQty - RedEntQty) "
				+ "ReqQty From ZReqInf, ZRedInf Where RedSlpTyp = ReqSlpTyp And RedSlpNum = ReqSlpNum And ReqSlpTyp = 'Q' "
				+ "And (RedReqQty - RedEntQty) > 0 And ReqCurStt <> 'F' And RedCurStt <> 'F' Group By ReqDepCod, RedPumCod) Red "
				+ "On ReqDepCod = StkDepCod And RedPumCod = StkPumCod , ZPumMst WITH (NOLOCK), ZEstMst WITH (NOLOCK), SeeMst "
				+ "WITH (NOLOCK), ( Select '' OdrCod, 0 OdrQty ) OSP Where EstCod = PumCod And EstSeqNum  = PumSeqNum "
				+ "And PumCod 	= StkPumCod And '20200505' Between PumAdpDte And PumExpDte And StkDepCod  = 'PHA1' "
				+ "And SeeOdrCod  = StkPumCod And SeeAdpDte  = (Select Top 1 SeeAdpDte From SeeMst WITH (NOLOCK) "
				+ "Where SeeOdrCod = StkPumCod Order By	SeeAdpDte Desc ) )   LST   Order By	RemQty";
		
		List aRs = COM.SelectSQLMap(sQuerySEL);
		return aRs  ; 
	}
	
	public List<PharmStockVO> buildPharmStockFromRS(List source) { 
		List<PharmStockVO> items = new ArrayList<PharmStockVO>() ;
		Iterator iter = source.iterator() ; 
		ObjectMapper mapper = new ObjectMapper();
 
	    while(iter.hasNext()) { 
	        Object item = iter.next(); 
	        if( item instanceof Map) { 
	        	PharmStockVO row =  mapper.convertValue(item , PharmStockVO.class);  
	        	items.add(row);
	        }
	    }
		return items ; 
	}
	
}
