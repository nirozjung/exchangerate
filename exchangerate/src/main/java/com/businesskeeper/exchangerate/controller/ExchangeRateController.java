package com.businesskeeper.exchangerate.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.businesskeeper.exchangerate.models.EnumCurrency;
import com.businesskeeper.exchangerate.models.UIModel;
import com.businesskeeper.exchangerate.services.ExchangeRateDataService;

/**
 * @author nkarki
 *
 */
@RestController
@RequestMapping("/api/exchange-rate")
public class ExchangeRateController {
	
	@Autowired
	ExchangeRateDataService exchangeDataService;
	
	/**
	 * Retrieve exchange rate of the requested date
	 * 
	 * @param date
	 * @return
	 * @throws InterruptedException
	 * @throws IOException 
	 */
	
	// Params date/baseCurrency/targetCurrency
	@RequestMapping("{date}/{baseCurrency}/{targetCurrency}")
	public UIModel getExchangeRates(
					 @PathVariable String date, 
					 @PathVariable String baseCurrency, 
					 @PathVariable String targetCurrency) 
							 throws IOException, InterruptedException {
		
		//TODO check if the path parameters are valid else throw error message
		// parse String input date to real date
		EnumCurrency base = EnumCurrency.valueOf(baseCurrency);
		EnumCurrency target = EnumCurrency.valueOf(targetCurrency);
		
		// call the service
		UIModel exrate = exchangeDataService.findRateOf(base, target, date);
		
		return exrate; 
	}
	
	
	
//	@PostMapping("/date")
//	public ExchangeRateModel getAvgOf5DaysBefore(@RequestParam("date") 
//					@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date) {
//		
//		// exclude Saturday and Sunday
//		// call the service
//		
//		// return the exchange rate
//		
//		return null; 
//	}
//	
//	@PostMapping("/date")
//	public ExchangeRateModel getExchangeRatesTrend(@RequestParam("date") 
//					@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date) {
//		// call the service
//		// return the exchange rate
//		
//		// descending when rate in last five days strictly descending oder
//		
//		// ascending when rate in last five days strictly ascending
//		
//		return null; 
//	}
	
	

}
