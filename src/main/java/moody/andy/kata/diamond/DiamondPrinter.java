package moody.andy.kata.diamond;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.Writer;

public class DiamondPrinter {

    private static final String EMPTY_SPACE = " ";
    private static final String NEW_LINE = "\n";
    private final CharacterSet characters;

    public DiamondPrinter(CharacterSet characters) {
        this.characters = characters;
    }

    public void printDiamond(char character, Writer writer) throws InvalidCharacterException, IOException {
        int middleRowCharacterIndex = characters.getIndex(character);
        int startingIndex = characters.getStartingIndex();

        appendPrecedingRows(writer, startingIndex, middleRowCharacterIndex);
        writer.append(internalLineFor(character));
        if(startingIndex < middleRowCharacterIndex) {
            writer.append(NEW_LINE);
        }
        appendFollowingRows(writer, middleRowCharacterIndex, startingIndex);
    }

    private void appendFollowingRows(Writer writer, int middleRowCharacterIndex, int startingIndex) throws IOException, InvalidCharacterException {
        for (int i = middleRowCharacterIndex - 1; i >= startingIndex; i--) {
            appendPaddedInternalLineFor(writer, i, middleRowCharacterIndex, i > startingIndex);
        }
    }

    private void appendPrecedingRows(Writer writer, int startingIndex, int middleRowCharacterIndex) throws IOException, InvalidCharacterException {
        for (int i = startingIndex; i < middleRowCharacterIndex; i++) {
            appendPaddedInternalLineFor(writer, i, middleRowCharacterIndex, true);
        }
    }

    private void appendPaddedInternalLineFor(Writer writer, int i, int middleRowCharacterIndex, boolean withNewLine) throws InvalidCharacterException, IOException {
        writer.append(paddedInternalLineFor(characters.getCharacterForIndex(i), middleRowCharacterIndex - i));
        if(withNewLine) {
            writer.append(NEW_LINE);
        }
    }

    private String paddedInternalLineFor(char characterForIndex, int padding) throws InvalidCharacterException {
        String internalLine = internalLineFor(characterForIndex);
        return StringUtils.leftPad(internalLine, internalLine.length() + padding, EMPTY_SPACE);
    }

    private String internalLineFor(char character) throws InvalidCharacterException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(character);
        if(characters.getIndex(character) > characters.getStartingIndex()) {
            stringBuilder.append(internalSpaces(characters.getIndex(character) - characters.getStartingIndex()));
            stringBuilder.append(character);
        }
        return stringBuilder.toString();
    }

    /*
     * E.G.
     * A has an index diff of 0, and requires no internal spaces
     * B has index diff of 1, and requires a single internal space.
     * C has an index diff of 2, and requires 3 internal spaces.
     * D has an index diff of 3 and requires 5 internal spaces.
     */
    private String internalSpaces(int indexDiff) {
        int internalSpaces = (indexDiff * 2) - 1;
        return EMPTY_SPACE.repeat(Math.max(0, internalSpaces));
    }
}
