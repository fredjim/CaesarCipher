package backend;

public class CaesarCipher {

    private int change;

    public CaesarCipher() {
        this.change = 0;
    }

    private char getEncodeCharacter(char normalCharacter) {
        Alphabet alphabet = new Alphabet();

        if (alphabet.existCharacter(normalCharacter)) {
            int index = alphabet.getIndex(normalCharacter);
            int currentIndex = index + change;

            if (0 <= currentIndex && currentIndex < Alphabet.ALPHABET.length()) {
                return alphabet.getCharacter(currentIndex);
            }

            if (currentIndex >= Alphabet.ALPHABET.length()) {
                return alphabet.getCharacter(currentIndex - Alphabet.ALPHABET.length());
            }
        }

        return normalCharacter;
    }

    private char getDecodeCharacter(char cipherCharacter) {
        Alphabet alphabet = new Alphabet();

        if (alphabet.existCharacter(cipherCharacter)) {
            int index = alphabet.getIndex(cipherCharacter);
            int currentIndex = index - change;

            if (0 <= currentIndex && currentIndex < Alphabet.ALPHABET.length()) {
                return alphabet.getCharacter(currentIndex);
            }

            if (currentIndex < 0) {
                return alphabet.getCharacter(Alphabet.ALPHABET.length() - Math.abs(currentIndex));
            }
        }

        return cipherCharacter;
    }

    public String encode(String text) {
        String cipherText = "";
        for (int index = 0; index < text.length(); index++) {
            cipherText += getEncodeCharacter(text.charAt(index));
        }
        return cipherText;
    }

    public String decode(String text) {
        String plainText = "";
        for (int index = 0; index < text.length(); index++) {
            plainText += getDecodeCharacter(text.charAt(index));
        }
        return plainText;
    }

    public void setChange(int change) {
        this.change = change;
    }

}
