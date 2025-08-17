package moody.andy.kata.diamond;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DiamondPrinterTest {

    private final DiamondPrinter underTest = new DiamondPrinter(CharacterSet.UPPERCASE_CHARS);
    private final Writer writer = new StringWriter();
    @ParameterizedTest
    @ValueSource(chars = {'a', 'z', '1', '0', '9', '@', '['})
    void shouldThrowInvalidCharacterExceptionForCharactersOutsideOfTheAllowedCharacterRange(char invalidChar) {
        assertThrows(InvalidCharacterException.class, () -> underTest.printDiamond(invalidChar, writer));
    }

    @Test
    void shouldPrintSingleCharacterDiamondForA() throws InvalidCharacterException, IOException {
        underTest.printDiamond('A', writer);
        assertThat(writer.toString(), equalTo("A"));
    }
}
