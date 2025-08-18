package moody.andy.kata.diamond;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.PrintWriter;

public class DiamondKataMain {

    public static void main(String[] args) throws IOException {
        if(ArrayUtils.isEmpty(args) || StringUtils.isBlank(args[0])) {
            exitWithError();
        }
        try (PrintWriter printWriter = new PrintWriter(System.out)) {
            new DiamondPrinter(CharacterSet.UPPERCASE_CHARS).printDiamond(args[0].charAt(0), printWriter);
            printWriter.flush();
        } catch (InvalidCharacterException e) {
            exitWithError();
        }
    }

    private static void exitWithError() {
        System.err.println("Please run with a single uppercase letter as the first argument");
        System.exit(1);
    }

}
