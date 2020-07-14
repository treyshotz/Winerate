package services;

import models.Drink;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Sort {

    String field;
    boolean ascending;
    List<Drink> drinksSorted;

    /**
     * Creating a sort object whit a specific field
     * The initial state of the sort is ascending
     * @param field
     */
    public Sort(String field){
        this.field = field;
        this.ascending = true;
        this.drinksSorted = new DrinkService().getDrinksList();
    }

    /**
     * A method which creates a new Sort object when called
     * @param field
     * @return
     */
    public static Sort by(String field){
        return new Sort(field);
    }

    public void descending(){
        if (ascending) ascending = false;
    }

    public void ascending(){
        if (!ascending) ascending = true;
    }

    /**
     * A method for creating an empty sort object
     * @return
     */
    public Sort empty(){
        return new Sort(null);
    }

    public boolean isEmpty(){
        return field == null;
    }

    /**
     * Assa, hvis den metoden her ikke er det feteste jeg har sett så legger jeg opp
     * Takes in the list of drinks you want sorted
     * Sorts the list based on the String as parameter
     * It sorts the list by one of the generic methods in the getGeneric method
     * Checks for wether ascending is true or false, and sorts accordingly
     * Sets the list drinkSorted to the appropriatetly sorted list
     * @param drinks
     */
    public void sortedDrinks(List<Drink> drinks){
        if (this.isEmpty()) return;
        drinks.sort(Comparator.comparingDouble(p -> p.getGeneric(this.field)));
        if (!ascending) drinks.sort((a, b) -> (int) (b.getGeneric(this.field) - a.getGeneric(this.field)));
        drinksSorted = drinks;

    }

    /**
     * Returns the sorted list with drinks through the sortedDrinks method
     * @param drinks
     * @return
     */
    public List<Drink> getDrinksSorted(List<Drink> drinks) {
        sortedDrinks(drinks);
        return drinksSorted;
    }
}
