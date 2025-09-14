package com.example;

import java.util.List;

public interface FelineInterface {
    int getKittens();
    int getKittens(int kittensCount);

    List<String> getFood(String animalType) throws Exception;
    List<String> eatMeat() throws Exception;
    String getFamily();
}
