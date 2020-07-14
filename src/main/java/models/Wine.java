package models;

import java.util.List;

public class Wine extends Drink {
	//TODO: Decide what to include in a wine object
	//Redwine, whitewine, rose, port, sparkling
	private String type;
	private int age;
	private String country;
	private String region;
	//A wine can have several different grapes
	private List<String> grapes;
	
	public Wine() {
	}
	
	public Wine(int productId, String name, double alcohol, double volume, double price, String description, List<Rating> ratings, String type, int age, String country, String region, List<String> grapes) {
		super(productId, name, alcohol, volume, price, description, ratings);
		this.type = type;
		this.age = age;
		this.country = country;
		this.region = region;
		this.grapes = grapes;
	}

	public Wine(int productId, String name, double alcohol, double volume, double price, String description, String type, int age, String country, String region, List<String> grapes) {
		super(productId, name, alcohol, volume, price, description);
		this.type = type;
		this.age = age;
		this.country = country;
		this.region = region;
		this.grapes = grapes;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getRegion() {
		return region;
	}
	
	public void setRegion(String region) {
		this.region = region;
	}
	
	public List<String> getGrapes() {
		return grapes;
	}
	
	public void setGrapes(List<String> grapes) {
		this.grapes = grapes;
	}
}
