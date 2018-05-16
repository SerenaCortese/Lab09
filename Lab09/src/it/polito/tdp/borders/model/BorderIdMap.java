package it.polito.tdp.borders.model;

import java.util.HashMap;
import java.util.Map;

public class BorderIdMap {
	
	private Map<Integer, Border> map;

	public BorderIdMap() {
		this.map = new HashMap<>();
	}
	
	public Border get(Border border) {
		Border old = map.get(border.getCode());
		if(old==null) {
			//nella mappa non c'è questo corso => LO AGGIUNGO
			map.put(border.getCode(), border);
			return border;
		}
		
		//avevo già inserito quell'oggetto
		return old;
	
	}
	
	public Border get(int code) {
		return map.get(code);
	}
	
	public void put(Border border, int code) {
		map.put(code, border);
	}

}
