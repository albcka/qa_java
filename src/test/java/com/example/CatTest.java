package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CatTest {

    @Mock
    private Feline felineMock;

    @Test
    public void testGetSound() {

        Cat cat = new Cat(felineMock);
        assertEquals("Должен вернуться правильный звук", "Мяу", cat.getSound());
    }

    @Test
    public void testGetFood() throws Exception {
        when(felineMock.eatMeat()).thenReturn(List.of("Мясо", "Рыба"));

        Cat cat = new Cat(felineMock);
        assertEquals("Должен вернуться правильный список еды", List.of("Мясо", "Рыба"), cat.getFood());
    }

    @Test(expected = Exception.class)
    public void testGetFoodThrowsException() throws Exception {
        when(felineMock.eatMeat()).thenThrow(new Exception("Ошибка"));

        Cat cat = new Cat(felineMock);
        cat.getFood();
    }

    @Test
    public void testCatWithRealFeline() throws Exception {
        Feline realFeline = new Feline();
        Cat cat = new Cat(realFeline);


        assertEquals("Должен вернуться правильный звук", "Мяу", cat.getSound());

        List<String> food = cat.getFood();
        assertNotNull("Еда не должна быть null", food);
        assertFalse("Список еды не должен быть пустым", food.isEmpty());
    }

    @Test
    public void testMultipleCatsDifferentDependencies() throws Exception {
        Feline feline1 = mock(Feline.class);
        Feline feline2 = mock(Feline.class);

        when(feline1.eatMeat()).thenReturn(Arrays.asList("Мясо"));
        when(feline2.eatMeat()).thenReturn(Arrays.asList("Рыба"));

        Cat cat1 = new Cat(feline1);
        Cat cat2 = new Cat(feline2);

        assertEquals("Первый кот должен использовать первую зависимость", Arrays.asList("Мясо"), cat1.getFood());
        assertEquals("Второй кот должен использовать вторую зависимость", Arrays.asList("Рыба"), cat2.getFood());
    }

    @Test
    public void testConstructorInjection() {
        Feline testFeline = new Feline();
        Cat cat = new Cat(testFeline);
        assertNotNull("Кот должен быть создан", cat);
        assertEquals("Должен вернуться правильный звук", "Мяу", cat.getSound());
    }
}