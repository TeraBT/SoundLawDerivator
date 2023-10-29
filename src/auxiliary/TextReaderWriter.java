package auxiliary;

import java.io.*;

public class TextReaderWriter {
    public static String read(String pathName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(pathName))) {
            StringBuilder fileString = new StringBuilder();
            int readInt;
            while ((readInt = reader.read()) != -1) {
                fileString.append((char) readInt);
            }
            return fileString.toString();
        } catch (IOException e) {
            return null;
        }
    }

    //TODO: WRITE TO FILE
}
