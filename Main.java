import java.util.Scanner;

class Main {
    static boolean isExited = false;

    static void showMainOptions() {
        System.out.println("1 -> Login");
        System.out.println("2 -> Register");
        System.out.println("5 -> Show data");
        System.out.println("9 -> Exit");
        // Logger.log("Abcd", "red");
        // Logger.log("Abcd", "red", true);
    }

    static void takeAction(int choice, Scanner sc) {
        switch (choice) {
            case 1:
                Login.getCredentials(sc);
                boolean isLoggedin = Login.authenticate();
                if (isLoggedin) {
                    Logger.log("\nYou are successfully loggedin\n", "green", true);
                } else {
                    Logger.log("\nInvalid credentials\n", "red", true);
                }
                break;
            case 2:
                Register.getCredentials(sc);
                int result = Register.registerUser();

                switch (result) {
                    case 1:
                        Logger.log("\nRegistration successful\n", "green", true);
                        break;
                    case -1:
                        Logger.log("\ninvalid Email address\n", "red", true);
                        break;
                    case -2:
                        Logger.log("\nPassword did not match\n", "red", true);
                        break;
                }
                break;
            case 5:
                Register.showAllData();
                break;
            default:
                Logger.log("\nWrong choice!\n", "red");
        }
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            Logger.log("Welcome to Sun Banking.org", "yellow", true);
            Logger.log("________________________________________________________________\n", "yellow", true);
            do {
                showMainOptions();
                System.out.print("\nEnter your choice: ");
                int choice = sc.nextInt();
                if (choice == 9) {
                    System.out.println("\nExiting....\n");
                    isExited = true;
                } else {
                    takeAction(choice, sc);
                }
            } while (!isExited);
        } catch (Exception e) {
            Logger.log("\nError: " + e.getMessage() + "\n", "red");
        }
    }
}
