package moody.andy.kata.diamond;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

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

    @ParameterizedTest
    @ValueSource(chars = {'A', 'B'})
    void shouldPrintSingleCharacterDiamondForA(char validChar) throws InvalidCharacterException, IOException {
        underTest.printDiamond(validChar, writer);
        assertThat(writer.toString(), equalTo(readFileFromClasspath(String.format("examples/%s.txt", validChar))));
    }

    private static String readFileFromClasspath(String filePath) throws IOException {
        return IOUtils.toString(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath)), StandardCharsets.UTF_8);
    }
}
