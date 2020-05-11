package io.nirozjung.exchangerate.models;

import java.util.Date;
import java.util.Map;

/**
 * Model representing the data to be exposed by Webservice
 * 
 * @author niroz
 *
 */
public class ExchangeRateModel {

	private Map<EnumCurrency, Double> rates;

	private String date;

	private EnumCurrency baseCurrency;
	
	/**
	 * Default Construct
	 */
	public ExchangeRateModel() {
	}

	/**
	 * Constructor
	 * 
	 * @param rates
	 * @param date
	 * @param baseCurrency
	 */
	public ExchangeRateModel(Map<EnumCurrency, Double> rates, String date, EnumCurrency baseCurrency) {
		super();
		this.rates = rates;
		this.date = date;
		this.baseCurrency = baseCurrency;
	}

	// getters and setters
	public Map<EnumCurrency, Double> getRates() {
		return rates;
	}

	public void setRates(Map<EnumCurrency, Double> rates) {
		this.rates = rates;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public EnumCurrency getBaseCurrency() {
		return baseCurrency;
	}

	public void setBaseCurrency(EnumCurrency baseCurrency) {
		this.baseCurrency = baseCurrency;
	}

}
