import org.example.*;
import org.junit.*;

public class KolcsonzesTest {
    @Before
    public void setUp() {
        Main.isRunningTest = true;
        Main.testNev = "Dénes";
        Main.testIdopont = "12:50";
    }

    @Test
    public void testKolcsonzesClass() {
        Kolcsonzes k = new Kolcsonzes("Bence;A;9;30;11;00");
        assert k.getNev().equals("Bence");
        assert k.getJazon().equals("A");
        assert k.getEOra() == 9;
        assert k.getEPerc() == 30;
        assert k.getVOra() == 11;
        assert k.getVPerc() == 0;
    }

    @Test
    public void testMain1() {
        Main.testNev = "Kata";
        Main.testIdopont = "10:9";
        Main.main(null);
    }

    @Test
    public void testMain2() {
        Main.main(null);
    }

    @After
    public void tearDown() {
        Main.isRunningTest = false;
    }
}
