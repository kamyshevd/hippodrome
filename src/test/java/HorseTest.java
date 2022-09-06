import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;

class HorseTest {

    @Test
    public void nullNameHorseException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1, 1));
    }

    @Test
    public void messageExceptionNameCannotBeNull() {
        assertEquals("Name cannot be null.", assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1, 1)).getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "\t\t", "\n\n\n\n"})
    public void nullBlankParametrizedNameException(String name) {
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, 1, 1));
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "\t\t", "\n\n\n\n"})
    public void messageNameExceptionNameCannotBeBlank(String name) {
        assertEquals("Name cannot be blank.", assertThrows(IllegalArgumentException.class, () -> new Horse(name, 1, 1)).getMessage());
    }

    @Test
    public void negativeSpeedException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("horse", -1, 1));
    }

    @Test
    public void messageNegativeSpeedException() {
        assertEquals("Speed cannot be negative.", assertThrows(IllegalArgumentException.class, () -> new Horse("horse", -1, 1)).getMessage());
    }

    @Test
    public void negativeDistanceException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("horse", 1, -1));
    }

    @Test
    public void messageNegativeDistanceException() {
        assertEquals("Distance cannot be negative.", assertThrows(IllegalArgumentException.class, () -> new Horse("horse", 1, -1)).getMessage());
    }

    @Test
    void getName() throws NoSuchFieldException, IllegalAccessException {
        Horse horse = new Horse("Grez", 1, 1);
        Field name = Horse.class.getDeclaredField("name");
        name.setAccessible(true);
        String nameValue = (String) name.get(horse);
        assertEquals("Grez", nameValue);
    }

    @Test
    public void getSpeed() throws NoSuchFieldException, IllegalAccessException {
        Horse horse = new Horse("Grez", 15, 1);
        Field speed = Horse.class.getDeclaredField("speed");
        speed.setAccessible(true);
        Double speedValue = (Double) speed.get(horse);
        assertEquals(15, speedValue);
    }

    @Test
    public void getDistance() throws NoSuchFieldException, IllegalAccessException {
        Horse horse = new Horse("Grez", 15, 22);
        Field distance = Horse.class.getDeclaredField("distance");
        distance.setAccessible(true);
        Double distanceValue = (Double) distance.get(horse);
        assertEquals(22, distanceValue);
    }

    @Test
    public void nullValueDistanceIfTwoParameters() throws IllegalAccessException, NoSuchFieldException {
        Horse horse = new Horse("Grez", 15);
        Field distance = Horse.class.getDeclaredField("distance");
        distance.setAccessible(true);
        Double distanceValue = (Double) distance.get(horse);
        assertEquals(0, distanceValue);
    }

    @Test
    public void moveGetRandomDouble() {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            new Horse("Grez", 15, 22).move();
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }
    @ParameterizedTest
    @ValueSource(doubles = {0.1,0.2,0.3,0.4})
    public void move (double random) {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            Horse horse = new Horse("Grez", 15, 22);
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(random);

            horse.move();

            assertEquals(22 + 15 * random, horse.getDistance());
        }
    }
}