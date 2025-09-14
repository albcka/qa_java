package com.example;

import java.util.List;

public class Feline extends Animal implements Predator, FelineInterface{

    @Override
    public List<String> eatMeat() throws Exception {
        return getFood("Хищник");
    }

    @Override
    public String getFamily() {
        return "Кошачьи";
    }

    public int getKittens() {
        return getKittens(1);
    }

    public int getKittens(int kittensCount) {
        return kittensCount;
    }
    @Override
    public List<String> getFood(String animalType) throws Exception {
        return super.getFood(animalType);
    }
}
