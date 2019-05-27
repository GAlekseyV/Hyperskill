import org.hyperskill.hstest.v4.stage.MainMethodTest;
import org.hyperskill.hstest.v4.testcase.CheckResult;
import org.hyperskill.hstest.v4.testcase.PredefinedIOTestCase;
import processor.Main;

import java.util.List;
import java.util.stream.Collectors;

public class NumericMatrixProcessorTest extends MainMethodTest {
    public NumericMatrixProcessorTest() throws Exception {
        super(Main.class);
    }


    @Override
    public List<PredefinedIOTestCase> generatePredefinedInputOutput() {
        return List.of(
            new PredefinedIOTestCase(
                "3 3\n" +
                    "1 2 3\n" +
                    "4 5 6\n" +
                    "7 8 9\n" +
                    "3",
                "3 6 9\n" +
                    "12 15 18\n" +
                    "21 24 27"
            ),

            new PredefinedIOTestCase(
                "2 3\n" +
                    "1 2 3\n" +
                    "4 5 6\n" +
                    "0",
                "0 0 0\n" +
                    "0 0 0"
            ),

            new PredefinedIOTestCase(
                "5 5 \n" +
                    "1 4 6 7 8\n" +
                    "1 9 5 2 2\n" +
                    "1 4 3 5 7\n" +
                    "1 4 6 4 1\n" +
                    "1 4 5 7 1\n" +
                    "5",
                "5 20 30 35 40\n" +
                    "5 45 25 10 10\n" +
                    "5 20 15 25 35\n" +
                    "5 20 30 20 5\n" +
                    "5 20 25 35 5"
            ),

            new PredefinedIOTestCase(
                "1 1\n" +
                    "1\n" +
                    "1",
                "1"
            ),

            new PredefinedIOTestCase(
                "1 1\n" +
                    "0\n" +
                    "1",
                "0"
            ),

            new PredefinedIOTestCase(
                "3 2\n" +
                    "1 2\n" +
                    "8 1\n" +
                    "9 1\n" +
                    "10",
                "10 20\n" +
                    "80 10\n" +
                    "90 10"
            )
        );
    }

    @Override
    public CheckResult checkSolved(String reply, String clue) {

        clue = clue.strip();
        reply = reply.strip();

        if (reply.equals(clue)) {
            return CheckResult.TRUE;
        }

        if (clue.equals("ERROR")) {
            return CheckResult.FALSE;
        }

        List<String> user = reply.lines().collect(Collectors.toList());
        List<String> answ = clue.lines().collect(Collectors.toList());

        if (user.size() != answ.size()) {
            return CheckResult.FALSE;
        }

        for (int i = 0; i < user.size(); i++) {
            String userLine = user.get(i).strip();
            String answLine = answ.get(i).strip();
            if (!userLine.equals(answLine)) {
                return CheckResult.FALSE;
            }
        }

        return new CheckResult(true);
    }
}
