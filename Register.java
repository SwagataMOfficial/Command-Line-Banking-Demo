import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Console;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Register {
    private static String email;
    private static String password;
    private static String cpassword;

    public static int registerUser() {

        // checking email validity
        if (email.contains("@") && email.contains(".")
                && (email.endsWith(".in") || email.endsWith(".com") || email.endsWith(".co.in"))) {
            // checking password validity
            if (password.equals(cpassword)) {

                // creating the token
                String authString = email + "," + password;

                // writing the token to the file
                try (BufferedWriter bw = new BufferedWriter(new FileWriter("textfile.txt", true))) {
                    bw.write(authString);
                    bw.newLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return 1;
            } else {
                return -2;
            }
        } else {
            return -1;
        }
    }

    public static void getCredentials(Scanner sc) {
        Console console = System.console();
        System.out.print("Enter Your Email: ");
        email = sc.next();

        char[] passwordArray = console.readPassword("Enter Your Password: ");
        password = new String(passwordArray);

        char[] cpasswordArray = console.readPassword("Confirm Your Password: ");
        cpassword = new String(cpasswordArray);
    }

    public static void showAllData() {
        String filePath = "textfile.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            System.out.printf("\n%-20s |\t%-10s%n", "Email", "Password");
            System.out.println("-------------------------------");

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",\\s*");
                if (parts.length == 2) {
                    String email = parts[0];
                    String password = parts[1];
                    System.out.printf("%-20s |\t%-10s%n", email, password);
                }
            }
            System.out.println();
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }
}
