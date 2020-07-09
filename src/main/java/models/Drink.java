package models;

import java.util.List;
import java.util.stream.Collectors;

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
	
	public Drink(int productId, String name, double alcohol, double volume, double price, String description, List<Rating> ratings) {
		this.productId = productId;
		this.name = name;
		this.alcohol = alcohol;
		this.volume = volume;
		this.price = price;
		this.description = description;
		this.ratings = ratings;
		this.avgRating = getAvgRating();
	}
	
	public int getProductId() {
		return productId;
	}
	
	public String getName() {
		return name;
	}
	
	public double getAlcohol() {
		return alcohol;
	}
	
	public double getVolume() {
		return volume;
	}
	
	public double getPrice() {
		return price;
	}
	
	public String getDescription() {
		return description;
	}
	
	public double getAvgRating() {
		if (avgRating == 0) findAvrRating();
		return avgRating;
	}
	
	public List<Rating> getRatings() {
		return ratings;
	}
	
	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setAlcohol(double alcohol) {
		this.alcohol = alcohol;
	}
	
	public void setVolume(double volume) {
		this.volume = volume;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	//Can be removed as the avrage rating should always be based on the list of ratings
	public void setAvgRating(double avgRating) {
		this.avgRating = avgRating;
	}
	
	public void addRating(Rating newRating) {
		this.ratings.add(newRating);
		findAvrRating();
	}

	public void findAvrRating(){
		this.avgRating = ratings.stream().mapToDouble(Rating::getRating).sum() / ratings.size();
	}
}
