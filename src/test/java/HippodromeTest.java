import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class HippodromeTest {
    @Test
    public void nullNameHippodromeException() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }
    @Test
    public void  messageExceptionHorsesCannotBeNull() {
        assertEquals("Horses cannot be null.", assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null)).getMessage());
    }
    @Test
    public void EmptyListException(){
        List<Horse> horses = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(horses));
    }
    @Test
    public void messageEmptyListException(){
        List<Horse> horses = new ArrayList<>();
        assertEquals("Horses cannot be empty.", assertThrows(IllegalArgumentException.class, () -> new Hippodrome(horses)).getMessage());
    }
    @Test
    void getHorsesTest() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 1; i <31 ; i++) {
            horses.add(new Horse("" + i, i, i));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses,hippodrome.getHorses());
    }
    @Test
    void moveTest() {
            List<Horse> horses = new ArrayList<>();
            for (int i = 0; i < 50; i++) {
                horses.add(mock(Horse.class));
            }
            Hippodrome hippodrome = new Hippodrome(horses);

            hippodrome.move();

            for (Horse horse : horses) {
                verify(horse).move();
            }
        }

    @Test
    void getWinnerTest() {
        Horse horse1 = new Horse("Grey",2,3);
        Horse horse2 = new Horse("Green",2,1);
        Horse horse3 = new Horse("Gold",2,2.2);
        Horse horse4 = new Horse("Yellow",2,2.1);
        Horse horse5 = new Horse("Stone",2,3.5);
        Hippodrome hippodrome = new Hippodrome(List.of(horse1,horse2,horse3,horse4,horse5));
        assertSame(horse5,hippodrome.getWinner());
    }
}