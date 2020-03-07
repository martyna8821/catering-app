package pl.martyna.catering.app.report.kitchen.report;

import lombok.Data;
import lombok.Getter;

@Getter
public class MealCookingData {

    private int weight = 0;
    private int portionsNumber = 0;
    private KitchenRecipe kitchenRecipe = new KitchenRecipe();

    public void addWeight(int weight){
        this.weight += weight;
    }

    public void addPortionsNumber(int portionsNumber){
        this.portionsNumber += portionsNumber;
    }
}
