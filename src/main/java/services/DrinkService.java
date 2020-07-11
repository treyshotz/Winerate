package services;

import models.Drink;
import models.Rating;

import java.util.ArrayList;
import java.util.List;

public class DrinkService {

    public List<Drink> drinksList;

    public DrinkService(){
        this.drinksList = new ArrayList<>();
    }

    public DrinkService(List<Drink> drinks){
        this.drinksList = drinks;
    }

    public List<Drink> getDrinksList() {
        return drinksList;
    }

    public boolean registerDrink(Drink drink){
        if (!drinksList.contains(drink)){
            drinksList.add(drink);
            return true;
        }
        return false;
    }
    
    public void registerRating(Drink drink, Rating rating){
        drinksList.stream().filter(p -> p.equals(drink)).findAny().ifPresent(drink1 -> drink1.addRating(rating));
    }
}
