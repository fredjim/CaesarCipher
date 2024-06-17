package frontend;

import backend.CipherManagement;

import java.util.Scanner;

public class UI {

    public void run() {
        int option = mainMenu();
        while (true) {
            switch (option) {
                case 1:
                    optionReadText();
                    break;
                case 2:
                    optionFileEncode();
                    break;
                case 3:
                    optionFileDecode();
                    break;
                case 4:
                    optionHelp();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("\n" + Color.YELLOW + "Escribe una opción válida. Intenta de nuevo" + Color.RESET);
                    break;
            }
            clearScreen();
            option = mainMenu();
        }
    }

    private int mainMenu() {
        System.out.print(
                "  CIFRADO CÉSAR - MENÚ PRINCIPAL\n" +
                "----------------------------------\n" +
                "  (1) Leer texto\n" +
                "  (2) Cifrar archivo\n" +
                "  (3) Descifrar archivo\n" +
                "  (4) Ayuda\n" +
                "  (5) Salir\n\n" +
                Color.YELLOW + "Elige un número del menú: " + Color.RESET
        );
        return readNumber();
    }

    private void optionReadText() {
        clearScreen();
        System.out.println(Color.YELLOW + "Escribe un texto:" + Color.RESET);
        String text = writeString();
        System.out.print(Color.YELLOW + "Escribe nombre de archivo: " + Color.RESET);
        String fileName = writeString();
        CipherManagement cipherManagement = new CipherManagement();
        cipherManagement.saveText(text, fileName);
    }

    private void optionFileEncode() {
        clearScreen();
        CipherManagement cipherManagement = new CipherManagement();
        System.out.println(Color.YELLOW + "Lista de archivos:\n" + Color.RESET + cipherManagement.listFolderFiles());
        System.out.print(Color.YELLOW + "Escribe nombre de archivo normal para cifrarlo: " + Color.RESET);
        String fileName = writeString();
        System.out.print(Color.YELLOW + "Ingresa número de cambios: " + Color.RESET);
        int changeNumber = readNumber();
        fileName = cipherManagement.encode(changeNumber, fileName);
        System.out.println(Color.YELLOW + "Texto cifrado:" + Color.RESET);
        System.out.println(cipherManagement.readText(fileName));

        System.out.print("\n" + Color.YELLOW + "Presiona cualquier tecla para volver al menú principal: " + Color.RESET);
        writeString();
        return;
    }

    private void optionFileDecode() {
        clearScreen();
        CipherManagement cipherManagement = new CipherManagement();
        System.out.println(Color.YELLOW + "Lista de archivos:\n" + Color.RESET + cipherManagement.listFolderFiles());
        System.out.print(Color.YELLOW + "Escribe nombre de archivo cifrado: " + Color.RESET);
        String fileName = writeString();
        System.out.print(Color.YELLOW + "Escribe nombre de archivo de cambio de propiedades: " + Color.RESET);
        String fileNameChangeProperties = writeString();
        fileName = cipherManagement.decode(fileNameChangeProperties, fileName);
        System.out.println(Color.YELLOW + "Texto descifrado:" + Color.RESET);
        System.out.println(cipherManagement.readText(fileName));

        System.out.print("\n" + Color.YELLOW + "Presiona cualquier tecla para volver al menú principal: " + Color.RESET);
        writeString();
        return;
    }

    private void optionHelp() {
        clearScreen();
        System.out.println(
                "  CIFRADO CÉSAR AYUDA\n" +
                "-----------------------\n" +
                "  El programa usa el alfabeto inglés más los siguientes caracteres (.,'\":-!? )\n" +
                "  Primero recibe un texto normal desde la consola y luego pide un nombre de archivo para guardarlo.\n" +
                "  Segundo, debes escribir el nombre del archivo con el texto normal para cifrarlo. Para cifrar\n" +
                "  el texto del archivo normal debes escribir el número de cambios. El nombre del archivo cifrado tendrá\n" +
                "  adjunto el texto _encriptado.\n" +
                "  Tercero, para descifrar el texto cifrado del archivo debes escribir el nombre del archivo cifrado,\n" +
                "  además del archivo change.properties, si este nombre no se escribe correctamente tomará el cambio\n" +
                "  igual que cero. Si el nombre del archivo es correcto, el programa creará un nuevo archivo con el texto\n" +
                "  descifrado y el nombre del archivo contendrá el texto desencriptado."
        );

        System.out.print("\n" + Color.YELLOW + "Presiona cualquier tecla para volver al menú principal: " + Color.RESET);
        writeString();
        return;
    }

    private int readNumber() {
        Scanner keyboard = new Scanner(System.in);
        return keyboard.nextInt();
    }

    private String writeString() {
        Scanner keyboard = new Scanner(System.in);
        return keyboard.nextLine();
    }

    private void clearScreen() {
        try {
            String operatingSystem = System.getProperty("os.name");
            ProcessBuilder processBuilder;
            Process startProcess;
            if (operatingSystem.contains("Windows")) {
                processBuilder = new ProcessBuilder("cmd", "/c", "cls");
            } else {
                processBuilder = new ProcessBuilder("clear");
            }
            startProcess = processBuilder.inheritIO().start();
            startProcess.waitFor();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

}
