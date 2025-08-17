package moody.andy.kata.diamond;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.Writer;

public class DiamondPrinter {

    private static final String EMPTY_SPACE = " ";
    private final CharacterSet validCharacters;

    public DiamondPrinter(CharacterSet validCharacters) {
        this.validCharacters = validCharacters;
    }

    public void printDiamond(char character, Writer writer) throws InvalidCharacterException, IOException {
        int middleRowCharacterIndex = validCharacters.getIndex(character);
        int startingIndex = validCharacters.getStartingIndex();

        for (int i = startingIndex; i < middleRowCharacterIndex; i++) {
            char characterForIndex = validCharacters.getCharacterForIndex(i);
            writer.append(paddedInternalLineFor(characterForIndex, middleRowCharacterIndex - i));
            writer.append("\n");
        }
        writer.append(internalLineFor(character));
        if(startingIndex < middleRowCharacterIndex) {
            writer.append("\n");
        }
        for (int i = middleRowCharacterIndex - 1; i >= startingIndex; i--) {
            writer.append(paddedInternalLineFor(validCharacters.getCharacterForIndex(i), middleRowCharacterIndex - i));
            if(i > startingIndex) {
                writer.append("\n");
            }
        }
    }

    private String paddedInternalLineFor(char characterForIndex, int padding) throws InvalidCharacterException {
        String internalLine = internalLineFor(characterForIndex);
        return StringUtils.leftPad(internalLine, internalLine.length() + padding, EMPTY_SPACE);
    }

    private String internalLineFor(char character) throws InvalidCharacterException {
        int charIndex = validCharacters.getIndex(character);
        boolean afterStartingChar = charIndex > validCharacters.getStartingIndex();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(character);
        if(afterStartingChar) {
            internalSpaces(charIndex, stringBuilder);
            stringBuilder.append(character);
        }
        return stringBuilder.toString();
    }

    private void internalSpaces(int charIndex, StringBuilder stringBuilder) {
        int indexDiff = charIndex - validCharacters.getStartingIndex();
        int internalSpaces = (indexDiff * 2) - 1;
        stringBuilder.append(EMPTY_SPACE.repeat(Math.max(0, internalSpaces)));
    }
}
