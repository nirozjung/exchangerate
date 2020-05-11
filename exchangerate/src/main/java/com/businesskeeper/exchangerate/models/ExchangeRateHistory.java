package com.businesskeeper.exchangerate.models;

import java.util.Date;
import java.util.Map;

public class ExchangeRateHistory {

	private Map<Date, Map<EnumCurrency, Double>> rates;

	private Date start_at;
	private Date end_at;

	private EnumCurrency base;

	/**
	 * create emtpy ExchangeRateHistory object
	 */
	public ExchangeRateHistory() {
	}

	/**
	 * create ExchangeRateHistory object
	 * @param rateshistory
	 * @param star_at
	 * @param end_at
	 * @param base
	 */
	public ExchangeRateHistory(Map<Date, Map<EnumCurrency, Double>> rateshistory, Date star_at, Date end_at,
			EnumCurrency base) {
		super();
		this.rates = rateshistory;
		this.start_at = star_at;
		this.end_at = end_at;
		this.base = base;
	}

	public Map<Date, Map<EnumCurrency, Double>> getRates() {
		return rates;
	}

	public void setRates(Map<Date, Map<EnumCurrency, Double>> rateshistory) {
		this.rates = rateshistory;
	}

	public Date getStart_at() {
		return start_at;
	}

	public void setStart_at(Date star_at) {
		this.start_at = star_at;
	}

	public Date getEnd_at() {
		return end_at;
	}

	public void setEnd_at(Date end_at) {
		this.end_at = end_at;
	}

	public EnumCurrency getBase() {
		return base;
	}

	public void setBase(EnumCurrency base) {
		this.base = base;
	}

}
