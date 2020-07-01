package models;

import java.util.List;

public interface Drinkable {

	int getProductId();
	
	String getName();
	
	double getAlcohol();
	
	double getVolume();
	
	double getPrice();
	
	String getDescription();
	
	double getAvgRating();
	
	List<Rating> getRatings();
	

}
