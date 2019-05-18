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
                "4 5\n" +
                    "1 2 3 4 5\n" +
                    "3 2 3 2 1\n" +
                    "8 0 9 9 1\n" +
                    "1 3 4 5 6\n" +
                    "4 5\n" +
                    "1 1 4 4 5\n" +
                    "4 4 5 7 8\n" +
                    "1 2 3 9 8\n" +
                    "1 0 0 0 1",
                "2 3 7 8 10\n" +
                    "7 6 8 9 9\n" +
                    "9 2 12 18 9\n" +
                    "2 3 4 5 7"
            ),

            new PredefinedIOTestCase(
                "2 3\n" +
                    "1 4 5\n" +
                    "4 5 5\n" +
                    "4 5\n" +
                    "0 1 0 4 5\n" +
                    "1 7 8 9 4\n" +
                    "1 2 3 5 6\n" +
                    "1 3 4 3 8",
                "ERROR"
            ),

            new PredefinedIOTestCase(
                "4 5\n" +
                    "4 2 3 4 5 \n" +
                    "3 5 3 2 1\n" +
                    "8 0 9 9 1\n" +
                    "1 3 4 5 9\n" +
                    "4 5\n" +
                    "1 1 4 4 5\n" +
                    "4 4 5 7 8\n" +
                    "1 2 3 9 8\n" +
                    "1 0 0 0 1",
                "5 3 7 8 10\n" +
                    "7 9 8 9 9\n" +
                    "9 2 12 18 9\n" +
                    "2 3 4 5 10"
            ),

            new PredefinedIOTestCase(
                "1 1\n" +
                    "1\n" +
                    "1 1\n" +
                    "2",
                "3"
            ),

            new PredefinedIOTestCase(
                "1 2\n" +
                    "3 4\n" +
                    "1 2\n" +
                    "5 6",
                "8 10"
            ),

            new PredefinedIOTestCase(
                "2 1\n" +
                    "1\n" +
                    "2\n" +
                    "2 1\n" +
                    "2\n" +
                    "1",
                "3\n" +
                    "3"
            ),

            new PredefinedIOTestCase(
                "2 1\n" +
                    "2\n" +
                    "1\n" +
                    "1 2\n" +
                    "1 2",
                "ERROR"
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
