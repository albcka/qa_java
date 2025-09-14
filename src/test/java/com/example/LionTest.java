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
public class LionTest {

    @Mock
    private Predator predatorMock;

    @Test
    public void testMaleLionHasMane() throws Exception {
        Lion lion = new Lion("Самец", predatorMock);
        assertTrue("Самец должен иметь гриву", lion.doesHaveMane());
    }

    @Test
    public void testFemaleLionNoMane() throws Exception {
        Lion lion = new Lion("Самка", predatorMock);
        assertFalse("Самка не должна иметь гриву", lion.doesHaveMane());
    }

    @Test(expected = Exception.class)
    public void testInvalidSexThrowsException() throws Exception {
        new Lion("Неизвестно", predatorMock);
    }

    @Test
    public void testInvalidSexExceptionMessage() {
        try {
            new Lion("Invalid", predatorMock);
            fail("Должно было быть выброшено исключение");
        } catch (Exception e) {
            assertEquals("Сообщение об ошибке должно совпадать",
                    "Используйте допустимые значения пола животного - самец или самка",
                    e.getMessage());
        }
    }

    @Test
    public void testGetKittens() throws Exception {
        Feline felineMock = mock(Feline.class);
        when(felineMock.getKittens()).thenReturn(3);

        Lion lion = new Lion("Самец", felineMock);
        assertEquals("Должно вернуться 3 котенка", 3, lion.getKittens());
    }

    @Test
    public void testGetFood() throws Exception {
        List<String> expectedFood = Arrays.asList("Мясо", "Рыба");
        when(predatorMock.eatMeat()).thenReturn(expectedFood);

        Lion lion = new Lion("Самка", predatorMock);
        assertEquals("Должен вернуться правильный список еды", expectedFood, lion.getFood());
        verify(predatorMock).eatMeat();
    }

    @Test
    public void testLionWithRealFeline() throws Exception {
        Predator realFeline = new Feline();
        Lion lion = new Lion("Самец", realFeline);

        assertTrue("Самец должен иметь гриву", lion.doesHaveMane());
        assertEquals("Должен вернуться 1 котенок", 1, lion.getKittens());

        List<String> food = lion.getFood();
        assertNotNull("Еда не должна быть null", food);
        assertFalse("Список еды не должен быть пустым", food.isEmpty());
    }

    @Test
    public void testEmptySexString() throws Exception {
        try {
            new Lion("", predatorMock);
            fail("Должно было быть выброшено исключение");
        } catch (Exception e) {
            assertTrue("Должно быть исключение", e.getMessage().contains("Используйте допустимые значения"));
        }
    }

    @Test
    public void testNullSex() throws Exception {
        try {
            new Lion(null, predatorMock);
            fail("Должно было быть выброшено исключение");
        } catch (Exception e) {
            assertTrue("Должно быть исключение", e.getMessage().contains("Используйте допустимые значения"));
        }
    }
}