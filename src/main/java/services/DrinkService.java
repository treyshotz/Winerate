package services;

import models.Drink;
import models.Rating;
import models.Wine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    /**
     * Searches through all drinks to find drinks with names containing the parameter
     * @param name
     * @return list with all drinks containing 'name'
     */
    public List<Drink> searchName(String name){
        return drinksList.stream().filter(p -> p.getName().toLowerCase().contains(name.toLowerCase())).collect(Collectors.toList());
    }

    public List<Drink> searchType(String type){
        //Todo method searching for all drinks in a specific subclass "type"
        return null;
    }

    public List<Wine> searchWineType(String type){
        List<Drink> winelist = new ArrayList<>();
        winelist = drinksList.stream().filter(p -> p instanceof Wine).collect(Collectors.toList());
        //need to cast this properly, but cant be bothered right now
        //return winelist.stream().filter(p -> p.getType.toLowerCase()).equals(type.toLowerCase())
        return null;
    }

}
