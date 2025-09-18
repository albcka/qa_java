package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
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
        when(felineMock.eatMeat()).thenReturn(List.of("Животные", "Птицы", "Рыба"));

        Cat cat = new Cat(felineMock);
        assertEquals("Должен вернуться правильный список еды", List.of("Животные", "Птицы", "Рыба"), cat.getFood());
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
    public void testConstructorInjection() {
        Feline testFeline = new Feline();
        Cat cat = new Cat(testFeline);
        assertNotNull("Кот должен быть создан", cat);
        assertEquals("Должен вернуться правильный звук", "Мяу", cat.getSound());
    }
}