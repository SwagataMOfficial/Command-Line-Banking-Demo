import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Console;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Login {
    private static String email;
    private static String password;

    public static boolean authenticate() {
        // System.out.println(email);
        // System.out.println(password);

        String authString = email + "," + password;
        int lineCount = 0;
        // System.out.println(authString);

        // reading the file content for credentials
        try (BufferedReader br = new BufferedReader(new FileReader("textfile.txt"))) {
            while ((br.readLine()) != null) { // reading the number of lines to create the array of authtokens
                lineCount++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] authtokens = new String[lineCount];

        // populating authtokens array
        try (BufferedReader br = new BufferedReader(new FileReader("textfile.txt"))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                // System.out.println(line);
                authtokens[i] = line;
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // validating username and password
        try (BufferedReader br = new BufferedReader(new FileReader("textfile.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (authString.equals(line)) {

                    // creeating session for the loggedin user
                    try (BufferedWriter bw = new BufferedWriter(new FileWriter("session.txt"))) {
                        bw.write(email);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void getCredentials(Scanner sc) {
        Console console = System.console();

        System.out.print("\nEnter Your Email: ");
        email = sc.next();

        char[] passwordArray = console.readPassword("\nEnter Your Password: ");
        password = new String(passwordArray);
    }

    public static boolean checkSession() {
        // creating a new session for the current user
        try (BufferedReader br = new BufferedReader(new FileReader("session.txt"))) {
            String user = br.readLine();
            if(user != null){
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean logout()
    {
        // checking login validity
        try (BufferedReader br = new BufferedReader(new FileReader("session.txt"))) {
            String line = br.readLine();

            if(line != null)
            {
                try (BufferedWriter bw = new BufferedWriter(new FileWriter("session.txt"))) {
                    bw.write("");
                    return true;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
