import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class Vigenere_Encryption {

    public static void main(String[] args) {
        try{
            //Very thorough Tests
            encryptFile("test1.txt", "test2.txt", "dog");
            decryptFile("test2.txt", "test3.txt", "dog");
        } catch (IOException e) {
        System.out.println("Beautiful Error");
    }
}

    public static String encrytp_Vigenere(String klartext, String key) {
        StringBuilder r = new StringBuilder();
        int countkey = 0;

        for (int i = 0; i < klartext.length(); i++) {
            if (Character.isDigit(klartext.charAt(i)) || !Character.isLetter(klartext.charAt(i))) {
                r.append(klartext.charAt(i));
                continue;
            }

            if (Character.isUpperCase(klartext.charAt(i))) {
                char encryptedChar = (char) ((klartext.charAt(i) + key.toUpperCase().charAt(countkey) - 2 * 'A') % 26 + 'A');
                r.append(encryptedChar);
            } else if (Character.isLowerCase(klartext.charAt(i))) {
                char encryptedChar = (char) ((klartext.charAt(i) + key.toLowerCase().charAt(countkey) - 2 * 'a') % 26 + 'a');
                r.append(encryptedChar);
            }

            countkey = (countkey + 1) % key.length();
        }

        return r.toString();
    }

    public static void decryptFile(String srcFile, String destFile, String key) throws IOException {
        try (
                BufferedReader src = Files.newBufferedReader(Path.of(srcFile), StandardCharsets.UTF_8);
                BufferedWriter dest = Files.newBufferedWriter(Path.of(destFile), StandardCharsets.UTF_8);
        ) {
            String srcline;
            while ((srcline = src.readLine()) != null) {
                dest.write(decrytp_Vigenere(srcline, key) + "\n");
            }
        }
    }

    public static void encryptFile(String srcFile, String destFile, String key) throws IOException {
        try (
                BufferedReader src = Files.newBufferedReader(Path.of(srcFile), StandardCharsets.UTF_8);
                BufferedWriter dest = Files.newBufferedWriter(Path.of(destFile), StandardCharsets.UTF_8);
        ) {
            String srcline;
            while ((srcline = src.readLine()) != null) {
                dest.write(encrytp_Vigenere(srcline, key) + "\n");
            }
        }
    }

    public static String decrytp_Vigenere(String encrypted_text, String key) {
        StringBuilder r = new StringBuilder();
        int countkey = 0;

        for (int i = 0; i < encrypted_text.length(); i++) {
            if (Character.isDigit(encrypted_text.charAt(i)) || !Character.isLetter(encrypted_text.charAt(i))) {
                r.append(encrypted_text.charAt(i));
                continue;
            }

            if (Character.isUpperCase(encrypted_text.charAt(i))) {
                char klartext = (char) ((encrypted_text.charAt(i) - key.toUpperCase().charAt(countkey) + 130) % 26 + 'A');
                r.append(klartext);
            } else if (Character.isLowerCase(encrypted_text.charAt(i))) {
                char klartext = (char) ((encrypted_text.charAt(i) - key.toLowerCase().charAt(countkey) + 130) % 26 + 'a');
                r.append(klartext);
            }
            countkey = (countkey + 1) % key.length();
        }

        return r.toString();
    }


}
