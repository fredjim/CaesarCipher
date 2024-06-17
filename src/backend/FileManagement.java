package backend;

import java.io.*;

public class FileManagement {

    public String read(String fileName) {
        StringBuilder text = new StringBuilder();
        FileReader fileReader;
        try {
            fileReader = new FileReader(fileName);
            BufferedReader reader = new BufferedReader(fileReader);
            String line = reader.readLine();
            while (line != null) {
                text.append(line);
                line = reader.readLine();
            }
            reader.close();
            fileReader.close();
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
        return text.toString();
    }

    public void save(String text, String fileName) {
        FileWriter file;
        try {
            file = new FileWriter(fileName);
            BufferedWriter bufferedFile = new BufferedWriter(file);
            bufferedFile.write(text);
            bufferedFile.flush();
            file.close();
        } catch (IndexOutOfBoundsException | IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public String listFiles() {
        String stringListFiles = "";
        String folderPath = System.getProperty("user.dir");
        File folder = new File(folderPath);
        if (folder.exists() && folder.isDirectory()) {
            File[] listFiles = folder.listFiles();
            for (int i = 0; i < listFiles.length; i++) {
                stringListFiles += listFiles[i].getName() + "\n";
            }
        } else {
            System.out.println("Error: No existe el directorio " + folderPath);
        }
        return stringListFiles;
    }

}
