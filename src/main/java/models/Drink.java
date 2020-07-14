package models;

import java.util.List;
import java.util.Objects;
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
	private final String AVR_FIELD = "average";
	private final String NR_OF_RATINGS_FIELD = "nrofratings";
	
	
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

	/**
	 * Constructor where we do not take in ratings as parameter
	 * Its highly likely that we will add a drink before any users have rated it
	 * The average rating is set to 0
	 * @param productId
	 * @param name
	 * @param alcohol
	 * @param volume
	 * @param price
	 * @param description
	 */

	public Drink(int productId, String name, double alcohol, double volume, double price, String description) {
		this.productId = productId;
		this.name = name;
		this.alcohol = alcohol;
		this.volume = volume;
		this.price = price;
		this.description = description;
		this.avgRating = 0;
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

	/**
	 * 		//If the average ratings is 0, but there are ratings registered, the findAvrRating method is used to determine average rating
	 * @return
	 */

	public double getAvgRating() {
		if (avgRating == 0 && !ratings.isEmpty()) findAvrRating();
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

	/**
	 * Finds the average rating based on the sum of ratings in list
	 */

	public void findAvrRating(){
		this.avgRating = ratings.stream().mapToDouble(Rating::getRating).sum() / ratings.size();
	}

	/**
	 * Takes in a String and checks if it equals any of the predetermined final sorting fields
	 * @param field
	 * @return the double value given the parameter
	 */

	public double getGeneric(String field){
		if (field.toLowerCase().equals(AVR_FIELD)) {
			return this.avgRating;
		}
		if (field.toLowerCase().equals(NR_OF_RATINGS_FIELD))
			return this.ratings.size();
		return 0;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Drink drink = (Drink) o;
		return productId == drink.productId;
	}

}
