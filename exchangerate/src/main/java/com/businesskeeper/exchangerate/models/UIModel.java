package com.businesskeeper.exchangerate.models;

/**
 * @author nkarki
 *
 */
public class UIModel {
	
	// exchange rate of request date, base, target
	double rate;  
	
	// average  of  the  five  days  before  the  requested  date  (excluding  Saturday  andSunday )
	double average; 

	// exchange rate trend.
	EnumTrend trend;

	/**
	 * default construct
	 */
	public UIModel() {
	}

	/**
	 * @param rate
	 * @param average
	 * @param trend
	 */
	public UIModel(double rate, double average, EnumTrend trend) {
		this.rate = rate;
		this.average = average;
		this.trend = trend;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public double getAverage() {
		return average;
	}

	public void setAverage(double average) {
		this.average = average;
	}

	public EnumTrend getTrend() {
		return trend;
	}

	public void setTrend(EnumTrend trend) {
		this.trend = trend;
	} 

}
