package backend;

public class CipherManagement {

    private final String FILE_NAME_CHANGE = "change.properties";

    private void saveChangeProperties(int changeNumber) {
        String algorithmUsed = "algoritmo_usado=cambio_" + changeNumber;
        FileManagement fileManagement = new FileManagement();
        fileManagement.save(algorithmUsed, FILE_NAME_CHANGE);
    }

    private int readChangeProperties(String fileNameChangeProperties) {
        FileManagement fileManagement = new FileManagement();
        String text = fileManagement.read(fileNameChangeProperties);
        int changeProperty = 0;
        try {
            changeProperty = Integer.parseInt(text.split("_")[2]);
        } catch (ArrayIndexOutOfBoundsException exception) {
            System.out.println("Error: El nombre del archivo debe tener punto y extensión.");
        }
        return changeProperty;
    }

    public void saveText(String text, String fileName) {
        FileManagement fileManagement = new FileManagement();
        fileManagement.save(text, fileName);
    }

    public String readText(String fileName) {
        return new FileManagement().read(fileName);
    }

    public String encode(int change, String fileName) {
        FileManagement fileManagement = new FileManagement();
        String text = fileManagement.read(fileName);
        CaesarCipher caesarCipher = new CaesarCipher();
        caesarCipher.setChange(change);
        String cipherText = caesarCipher.encode(text);
        String[] piecesFileName = fileName.split("\\.");
        fileName = piecesFileName[0] + "_encriptado." + piecesFileName[1];
        fileManagement.save(cipherText, fileName);
        saveChangeProperties(change);
        return fileName;
    }

    public String decode(String fileNameChangeProperties, String fileName) {
        FileManagement fileManagement = new FileManagement();
        String text = fileManagement.read(fileName);
        CaesarCipher caesarCipher = new CaesarCipher();
        int change = readChangeProperties(fileNameChangeProperties);
        caesarCipher.setChange(change);
        String plainText = caesarCipher.decode(text);
        String[] piecesFileName = fileName.split("encriptado");
        fileName = piecesFileName[0] + "desencriptado" + piecesFileName[1];
        fileManagement.save(plainText, fileName);
        return fileName;
    }

    public String listFolderFiles() {
        return new FileManagement().listFiles();
    }

}
