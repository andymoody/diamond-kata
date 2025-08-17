package moody.andy.kata.diamond;

import java.io.IOException;
import java.io.Writer;

public class DiamondPrinter {

    private final CharacterSet validCharacters;

    public DiamondPrinter(CharacterSet validCharacters) {
        this.validCharacters = validCharacters;
    }

    public void printDiamond(char character, Writer writer) throws InvalidCharacterException, IOException {
        writer.append(internalLineFor(character));
    }

    private String internalLineFor(char character) throws InvalidCharacterException {
        validCharacters.getIndex(character);
        StringBuilder builder = new StringBuilder();
        builder.append(character);
        return builder.toString();
    }
}
