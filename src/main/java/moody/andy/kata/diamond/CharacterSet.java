package moody.andy.kata.diamond;

public interface CharacterSet {

    CharacterSet UPPERCASE_CHARS = new CharacterSet() {
        private static final char UPPERCASE_Z = 'Z';
        private static final char UPPERCASE_A = 'A';

        @Override
        public int getIndex(char character) throws InvalidCharacterException {
            if(character < UPPERCASE_A || character > UPPERCASE_Z) {
                throw new InvalidCharacterException("Invalid character: " + character);
            }
            return character;
        }

        @Override
        public int getStartingIndex() {
            return UPPERCASE_A;
        }

        @Override
        public char getCharacterForIndex(int index) {
            return (char) index;
        }
    };

    int getIndex(char character) throws InvalidCharacterException;

    int getStartingIndex();

    char getCharacterForIndex(int i);
}
