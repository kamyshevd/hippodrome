import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

class MainTest {
    @Timeout(value = 22)
    @Test
    public void main() throws Exception {
        Main.main(null);
    }
}