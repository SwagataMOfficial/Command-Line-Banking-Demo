
public class Logger {
    
    // regular colours
    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String MAGENTA = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";

    // bold colours
    public static final String BOLD_RESET = "\u001B[0m";
    public static final String BOLD_BLACK = "\u001B[1;30m";
    public static final String BOLD_RED = "\u001B[1;31m";
    public static final String BOLD_GREEN = "\u001B[1;32m";
    public static final String BOLD_YELLOW = "\u001B[1;33m";
    public static final String BOLD_BLUE = "\u001B[1;34m";
    public static final String BOLD_MAGENTA = "\u001B[1;35m";
    public static final String BOLD_CYAN = "\u001B[1;36m";
    public static final String BOLD_WHITE = "\u001B[1;37m";

    public static void log(String output, String colour) {

        switch (colour.toUpperCase()) {
            case "RED":
                System.out.println(RED + output + RESET);
                break;
            case "GREEN":
                System.out.println(GREEN + output + RESET);
                break;
            case "BLUE":
                System.out.println(BLUE + output + RESET);
                break;
            case "YELLOW":
                System.out.println(YELLOW + output + RESET);
                break;
            case "MAGENTA":
                System.out.println(MAGENTA + output + RESET);
                break;
            case "CYAN":
                System.out.println(CYAN + output + RESET);
                break;
            case "BLACK":
                System.out.println(BLACK + output + RESET);
                break;
            case "WHITE":
                System.out.println(WHITE + output + RESET);
                break;
        }
    }

    public static void log(String output, String colour, boolean bold) {

        if(bold){
            switch (colour.toUpperCase()) {
                case "RED":
                    System.out.println(BOLD_RED + output + RESET);
                    break;
                case "GREEN":
                    System.out.println(BOLD_GREEN + output + RESET);
                    break;
                case "BLUE":
                    System.out.println(BOLD_BLUE + output + RESET);
                    break;
                case "YELLOW":
                    System.out.println(BOLD_YELLOW + output + RESET);
                    break;
                case "MAGENTA":
                    System.out.println(BOLD_MAGENTA + output + RESET);
                    break;
                case "CYAN":
                    System.out.println(BOLD_CYAN + output + RESET);
                    break;
                case "BLACK":
                    System.out.println(BOLD_BLACK + output + RESET);
                    break;
                case "WHITE":
                    System.out.println(BOLD_WHITE + output + RESET);
                    break;
            }
        }
        else{
            log(output, colour);
        }
    }
}
