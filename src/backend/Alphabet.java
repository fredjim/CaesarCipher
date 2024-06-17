package backend;

public class Alphabet {

    public static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz.,'\":-!? ";

    public int getIndex(char character) {
        return ALPHABET.indexOf(character);
    }

    public char getCharacter(int index) {
        try {
            return ALPHABET.charAt(index);
        } catch (IndexOutOfBoundsException exception) {
            System.out.println("Error: Índice de cadena fuera de rango: " + index);
        }
        return '\u0000';
    }

    public boolean existCharacter(char character) {
        return ALPHABET.contains(String.valueOf(character));
    }

}
