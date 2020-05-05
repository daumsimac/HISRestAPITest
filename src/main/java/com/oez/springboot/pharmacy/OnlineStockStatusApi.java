package com.oez.springboot.pharmacy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import com.oez.springboot.entity.SearchResultVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/v1/pharmacy")
public class OnlineStockStatusApi {

	@Autowired
	OnlineStockStatusService onlineStockStatusService;
	
	@GetMapping("/stock")
	public ResponseEntity  getStock() {
		return ResponseEntity.ok(onlineStockStatusService.DisplayPharmStock());
	}
}
