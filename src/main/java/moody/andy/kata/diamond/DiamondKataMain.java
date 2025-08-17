package moody.andy.kata.diamond;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.PrintWriter;

public class DiamondKataMain {

    public static void main(String[] args) throws InvalidCharacterException, IOException {
        if(ArrayUtils.isEmpty(args) || StringUtils.isBlank(args[0])) {
            System.err.println("Please run with a single uppercase character as argument 1");
            System.exit(1);
        }
        try (PrintWriter printWriter = new PrintWriter(System.out)) {
            new DiamondPrinter(CharacterSet.UPPERCASE_CHARS).printDiamond(args[0].charAt(0), printWriter);
            printWriter.flush();
        }
    }

}
