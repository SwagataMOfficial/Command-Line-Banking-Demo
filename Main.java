import java.util.Scanner;

class Main {
    static boolean isExited = false;
    static boolean isUserLoggedin = false;
    static Bank bank = new Bank();

    static void showMainOptions() {
        System.out.println("1 -> Login");
        System.out.println("2 -> Register");
        System.out.println("5 -> Show data");
        System.out.println("9 -> Exit");
    }

    static void showLoggedinFeatures() {
        System.out.println("1 -> Amount Transfer");
        System.out.println("2 -> Check Balance");
        System.out.println("3 -> Deposit Amount");
        System.out.println("4 -> History");
        System.out.println("5 -> Logout");
    }

    static void takeAction(int choice, Scanner sc) {
        switch (choice) {
            case 1:
                Login.getCredentials(sc);
                boolean isLoggedin = Login.authenticate();
                if (isLoggedin) {
                    isUserLoggedin = true;
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

    static void takeUserActions(int choice, Scanner sc) {
        switch (choice) {
            case 1:
                System.out.print("\nEnter the amount: ");
                double amount = sc.nextDouble();
                if (bank.transferMoney(amount)) {
                    Logger.log("\nAmount successfully transfered!\n", "green", true);
                } else {
                    Logger.log("\nFailed to transfer!\n", "red", true);
                }
                break;
            case 2:
                Logger.log("\nYour balance is: " + bank.checkBalance() + "\n", "cyan", true);
                break;
            case 3:
                System.out.print("Enter the amount to deposit: ");
                double depositAmount = sc.nextDouble();
                if(bank.deposit(depositAmount))
                {
                    Logger.log("\nAmount successfully deposited!\n", "green", true);
                } else {
                    Logger.log("\nFailed to deposit!\n", "red", true);
                }
                break;
            case 4:
                Logger.log("\nYour history is nothing!\n", "cyan", true);
                break;
            default:
                Logger.log("\nWrong choice!\n", "red");
        }
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            Logger.log("\nWelcome to Sun Banking.org", "yellow", true);
            Logger.log("________________________________________________________________\n", "yellow", true);
            do {
                isUserLoggedin = Login.checkSession();
                // if user is not logged in then show main options
                if (!isUserLoggedin) {
                    showMainOptions();
                    System.out.print("\nEnter your choice: ");
                    int choice = sc.nextInt();
                    if (choice == 9) {
                        Logger.log("\nExiting....\n", "magenta", true);
                        isExited = true;
                    } else {
                        takeAction(choice, sc);
                    }
                }

                // if user is logged in then show loggedin features
                else {
                    while (true) {
                        showLoggedinFeatures();
                        System.out.print("\nEnter your choice: ");
                        int choice = sc.nextInt();
                        if (choice == 5) {
                            if (Login.logout()) {
                                Logger.log("\nLogged out....\n", "cyan");
                                isUserLoggedin = false;
                                break;
                            } else {
                                Logger.log("\nFailed to Logout!\n", "red", true);
                            }
                        } else {
                            takeUserActions(choice, sc);
                        }
                    }
                }
            } while (!isExited);
        } catch (Exception e) {
            Logger.log("\nError: " + e.getMessage() + "\n", "red");
        }
    }
}
