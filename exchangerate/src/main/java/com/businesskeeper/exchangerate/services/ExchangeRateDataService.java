package com.businesskeeper.exchangerate.services;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.businesskeeper.exchangerate.models.EnumCurrency;
import com.businesskeeper.exchangerate.models.EnumTrend;
import com.businesskeeper.exchangerate.models.ExchangeRateHistory;
import com.businesskeeper.exchangerate.models.ExchangeRateModel;
import com.businesskeeper.exchangerate.models.UIModel;

/**
 * Retrieves the data from external Api provider
 * 
 * @author
 *
 */
@SuppressWarnings("unused")
@Service
public class ExchangeRateDataService {

	private static String BASE_URL = "https://api.exchangeratesapi.io/";
	
	private static final int NO_DAYS_BEFORE = 5; 
	
	@Autowired
	private RestTemplate restTemplate;

	
	// ways to call 
	// https://api.exchangeratesapi.io/2010-01-12
	// https://api.exchangeratesapi.io/latest?base=USD
	// https://api.exchangeratesapi.io/latest?symbols=USD,GBP
	// see https://exchangeratesapi.io/
	// for more ways of getting data

	// https://api.exchangeratesapi.io/2020-04-23?symbols=INR,GBP
	/**
	 * Returns the rate of provided target currency against base currency for the given date
	 * 
	 * @param baseCurrency
	 * @param targetCurrency
	 * @param date
	 * @return exchange rate on the day
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public UIModel findRateOf(EnumCurrency baseCurrency, 
										EnumCurrency targetCurrency, 
										String date)
			throws IOException, InterruptedException {

		// if dates less than 2000-01-01, throw error
		// if dates in future, throw error message
		// return error in case invalid inputs
		
		//TODO check date not null
//		String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(date);
		String queryBase = baseCurrency.name(); 
		
		// Sending request to the external API using RestTemplate
		ExchangeRateModel allRates = restTemplate.getForObject(BASE_URL+date+"?"+queryBase, 
																ExchangeRateModel.class);

		// Filter out targetCurrency
		double targetRate = allRates.getRates().get(targetCurrency);
		Map<EnumCurrency, Double> rate = new HashMap<EnumCurrency,Double>(); 
		rate.put(targetCurrency, targetRate); 
		
		double avgOf5days = calculateAverage(date, baseCurrency, targetCurrency); 
//		TODO find trend withing calcAverage loop
//		EnumTrend trend = calculateTrend(); 
		
//		return new ExchangeRateModel(rate, date, baseCurrency);
		return new UIModel(targetRate,avgOf5days, EnumTrend.ASC ); 
	}
	
	// calc average of 5 days before the requested data
	public double calculateAverage(String date, EnumCurrency base, EnumCurrency target) {
		
		//String date to LocalDate
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate end_date = LocalDate.parse(date, formatter);
		
	
		// startdate = date -5 days
		LocalDate begin_at = end_date.minusDays(NO_DAYS_BEFORE);
		String start_at = begin_at.toString(); 
		String end_at = end_date.toString(); 
		
		// call rate history of 5 days period
		ExchangeRateHistory ratesHistory = restTemplate.getForObject(
				BASE_URL + "history?start_at=" + start_at + "&end_at=" + end_at + "&base=" + base,
				ExchangeRateHistory.class);
		
		// https://api.exchangeratesapi.io/history?start_at=2018-01-01&end_at=2018-09-01&base=USD
		
		//calc average
		// filter out nonsense and extract target value for each target currency
		Map<Date, Map<EnumCurrency, Double>> rateHistoryMap = ratesHistory.getRates();
		Double sumOfRateValues = 0.0D;
		Double averageValue = 0.0D; 
		
		 // Loop over Map Entry set
        for (Map.Entry<Date,Map<EnumCurrency, Double>> entry : rateHistoryMap.entrySet())   {
        	Map<EnumCurrency, Double> ratesEachDay = new HashMap<>(); 
        	ratesEachDay = entry.getValue(); 
        	//Loop over each rates of each day
			for (Map.Entry<EnumCurrency, Double> rate : ratesEachDay.entrySet())
				if(target.equals(rate.getKey())) {
					sumOfRateValues += rate.getValue();
				}
		}
        averageValue = sumOfRateValues/5; 
    	return averageValue; 
	}
	
	// TODO 
	public EnumTrend calculateTrend(Map<Date, Double> rateMap) {
		
	// Sorting the values in ascending order
	// descending:   when   the   exchange   rates   in   the   last five   days   are   in   strictly descending order, 
    // ascending: when the exchange rates in the last five days are in strictly ascending order
	// constant: when the exchange rates in the last five days are  the same
	// undefined: in other cases
		
	// How to implement recursive funtion for this 	
		Double previousValue = 0.0D;
		boolean isAscending = false; 
		for (Map.Entry<Date, Double> rate : rateMap.entrySet()) {
			if(previousValue <rate.getValue()) {
				isAscending = true; 
				previousValue = rate.getValue(); 
			} 
			
			
		}
		
		Iterator rateIterator = rateMap.entrySet().iterator();
		EnumTrend enumTrend; 
		while(rateIterator.hasNext()) {
			 Map.Entry mapElement = (Map.Entry) rateIterator.next(); 
			 Double value= (Double) mapElement.getValue(); 
			 
			 Map.Entry mapElementNext = (Map.Entry)rateIterator.next();
			 Double valueNext = (Double) mapElementNext.getValue(); 
			 
			 if(value<valueNext) {
				 enumTrend = EnumTrend.ASC;
			 } else {
				 
			 }
		}
		
		return null;
	}
	
}
