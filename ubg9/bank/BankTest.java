
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BankTest {

    private Bank b;

    @BeforeEach
    public void setUp() throws Exception {
        b = new Bank(12234L);
    }

    @Test
    public void testClone(){
        Bank bClone = b.clone();
    }
}