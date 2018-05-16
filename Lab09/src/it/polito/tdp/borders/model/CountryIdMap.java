package it.polito.tdp.borders.model;

import java.util.HashMap;
import java.util.Map;

public class CountryIdMap {
	
	private Map<Integer, Country> map;

	public CountryIdMap() {
		this.map = new HashMap<>();
	}
	
	public Country get(Country country) {
		Country old = map.get(country.getCCode());
		if(old==null) {
			//nella mappa non c'è questo corso => LO AGGIUNGO
			map.put(country.getCCode(), country);
			return country;
		}
		
		//avevo già inserito quell'oggetto
		return old;
	
	}
	
	public Country get(int CCode) {
		return map.get(CCode);
	}
	
	public void put(Country country, int CCode) {
		map.put(CCode, country);
	}

}
