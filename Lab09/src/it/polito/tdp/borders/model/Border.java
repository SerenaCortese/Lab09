package it.polito.tdp.borders.model;

public class Border {
	
	private int code;
	private Country country1;
	private Country country2;
	
	public Border(int code, Country country1, Country country2) {
		super();
		this.code = code;
		this.country1 = country1;
		this.country2 = country2;
		
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public Country getCountry1() {
		return country1;
	}
	public void setCountry1(Country country1) {
		this.country1 = country1;
	}
	public Country getCountry2() {
		return country2;
	}
	public void setCountry2(Country country2) {
		this.country2 = country2;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + code;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Border other = (Border) obj;
		if (code != other.code)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Border country1=" + country1 + ", country2=" + country2;
	}
	
	

}
