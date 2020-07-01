package models;

import java.util.List;

public abstract class Drink implements Drinkable {
	
	private int productId;
	private String name;
	private double alcohol;
	private double volume;
	private double price;
	private String description;
	private double avgRating;
	private List<Rating> ratings;
	
	
	public Drink() {

	}
	
	public int getProductId() {
		return 0;
	}
	
	public String getName() {
		return null;
	}
	
	public double getAlcohol() {
		return 0;
	}
	
	public double getVolume() {
		return 0;
	}
	
	public double getPrice() {
		return 0;
	}
	
	public String getDescription() {
		return null;
	}
	
	public double getAvgRating() {
		return 0;
	}
	
	public List<String> getRatings() {
		return null;
	}
}
