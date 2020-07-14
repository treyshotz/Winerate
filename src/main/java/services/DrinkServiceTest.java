package services;

import models.Rating;
import models.Wine;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class DrinkServiceTest {

    DrinkService ds = new DrinkService();
    List<String> grapes = new ArrayList<>();
    Wine wine = new Wine(1, "Redwine", 12.5, 0.75, 100, "Description", "Red", 2018, "France", "kukregion", grapes);
    Wine wine2 = new Wine(2, "Whitewine", 11.5, 0.75, 110, "Description2", "White", 2019, "Italy", "kukregion", grapes);

    @Before
    public void startCondition() {


        ds.registerDrink(wine);
        ds.registerDrink(wine2);
    }

    @Test
    void registerRating() {
        ds.registerDrink(wine);
        ds.registerRating(wine, new Rating("Stian", 80, "Mhm, very nice", null));
        assertEquals(wine, ds.getDrinksList().stream().findFirst().get());
    }

    @Test
    void searchName() {
    }

    @Test
    void searchType() {
    }

    @Test
    void searchWineType() {
    }

    @Test
    void sortDrinks() {
        ds.registerDrink(wine);
        ds.registerDrink(wine2);
    }
}