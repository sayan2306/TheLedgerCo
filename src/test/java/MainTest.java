import com.navi.Main;
import com.navi.exceptions.ModeNotSupportedException;
import com.navi.models.InMemoryStore;
import com.navi.models.Ledger;
import com.navi.models.Loan;
import com.navi.models.LoanDetails;
import com.navi.output.ConsoleOutputMode;
import com.navi.strategies.SimpleInterestCalculator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class MainTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    Ledger ledger;

    @Before
    public void before() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        ledger = new Ledger(
                new InMemoryStore(),
                new SimpleInterestCalculator(),
                new ConsoleOutputMode()
        );
        Loan loan = new Loan("IDIDI", "Harry", 5000.0, 2, 2.0);
        ledger.getStore().addToStore(new LoanDetails(loan));
    }

    @After
    public void after() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void testSampleInputFile1() throws IOException {
        final String expectedOutput =
                        "IDIDI Dale 1000 55\n" +
                        "IDIDI Dale 8000 20\n" +
                        "MBI Harry 1044 12\n" +
                        "MBI Harry 0 24\n";
        Main.main(new String[] {"input_file_1.txt"});
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testSampleInputFile2() throws IOException {
        final String expectedOutput =
                        "IDIDI Dale 1326 9\n" +
                        "IDIDI Dale 3652 4\n" +
                        "UON Shelly 15856 3\n" +
                        "MBI Harry 9044 10\n";
        Main.main(new String[] {"input_file_2.txt"});
        assertEquals(expectedOutput, outContent.toString());
    }




    @Test
    public void testFileModeWithInvalidFile() throws IOException {
        final String expectedOutput = "[ERROR] file.txt does not exist.\n";
        Main.main(new String[] {"file.txt"});
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test(expected = ModeNotSupportedException.class)
    public void testInvalidMode() throws IOException {
        Main.main(new String[] {"input_file_1.txt", "input_file_2.txt"});
    }
}
