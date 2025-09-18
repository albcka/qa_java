package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class FelineTest {

    @Test
    public void testEatMeat() throws Exception {
        Feline feline = new Feline();
        assertEquals("Должен вернуться список еды для хищника", List.of("Животные", "Птицы", "Рыба"), feline.eatMeat());
    }

    @Test
    public void testGetFamily() {
        Feline feline = new Feline();
        assertEquals("Должно вернуться правильное семейство", "Кошачьи", feline.getFamily());
    }

    @Test
    public void testGetKittensDefault() {
        Feline feline = new Feline();
        assertEquals("По умолчанию должен возвращаться 1 котенок", 1, feline.getKittens());
    }

    @Test
    public void testGetKittensWithCount5() {
        Feline feline = new Feline();
        assertEquals("Должно вернуться 5 котят", 5, feline.getKittens(5));
    }

    @Test
    public void testGetKittensWithCount0() {
        Feline feline = new Feline();
        assertEquals("Должно вернуться 0 котят", 0, feline.getKittens(0));
    }

    @Test
    public void testGetKittensWithNegativeCount() {
        Feline feline = new Feline();
        assertEquals("Должно вернуться отрицательное количество котят", -2, feline.getKittens(-2));
    }

    @Test
    public void testFelineImplementsPredator() {
        Feline feline = new Feline();
        assertTrue("Feline должен реализовывать Predator", feline instanceof Predator);
    }

    @Test
    public void testGetFoodDelegatesToAnimal() throws Exception {
        Feline feline = new Feline();
        assertEquals("Должен вернуться список еды от родительского класса", List.of("Животные", "Птицы", "Рыба"), feline.getFood("Хищник"));
    }
}