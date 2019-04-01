package phase2.Display;

import phase2.ATMTime;
import phase2.People.BankManager;
import phase2.Request.*;

import java.util.Scanner;

/**
 * The type Manager display.
 */
class ManagerDisplay {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        BankManager b = BankManager.getInstance();
        Scanner sc = new Scanner(System.in);
        String command;
        ATMController controller = new ATMController();

        System.out.println("Type '0' if you would like to view requests." + "\n"
                + "Type '1' to set the date." + "\n"
                + "Type '2' to restock the cash machine." + "\n" +
                " Type '3' to log out.");
        command = sc.nextLine().replaceAll("//s", "");
        while (!(command.equals("0")|| command.equals("1") || command.equals("2") || command.equals("3"))) {
            System.out.println("That is not a valid action, try again.");
            command = sc.nextLine().replaceAll("//s", "");
        }

        switch(command) {
            case("0"):
                System.out.println(b.getRequestSummary());
                if (b.getRequestSummary().equals("You have no requests.")) {
                    ManagerDisplay.main(null);
                }
                System.out.println("Which request do you want to address? Put in the appropriate number only." + "\n" +
                        "Type '0' for the oldest request.");
                command = sc.nextLine().replaceAll("//s", "");
                int number = Integer.valueOf(command);
                while (number > b.getNumberOfRequests()){
                    System.out.println("This index is not assigned to a request. Please try again.");
                    command = sc.nextLine().replaceAll("//s", "");
                    number = Integer.valueOf(command);
                }
                Request request = b.getRequest(number);
                System.out.println(request.toString());
                System.out.println("Do you want to accept this request?");
                command = sc.nextLine().replaceAll("//s", "");
                controller.incorrectAns(sc,command);
                if (command.toLowerCase().equals("yes")) {
                    b.completeRequest(number);
                } else {
                    b.ignoreRequest(number);
                }
                ManagerDisplay.main(null);

            case("1"):
                System.out.println("Please type the year for the new date:");
                command = sc.nextLine();
                int year = Integer.valueOf(command);
                System.out.println("Please type the month for the new date");
                command = sc.nextLine();
                int month = Integer.valueOf(command);
                while(!((0 <= month) && (month <= 11))) {
                    System.out.println("This is an invalid month." +
                            " Please input something between and including 0 to 11");
                    command = sc.nextLine();
                    month = Integer.valueOf(command);
                }
                System.out.println("Please type the day for the new date");
                command = sc.nextLine();
                int day = Integer.valueOf(command);
                while(!((1<=day)&&(day<=31))){
                    System.out.println("This is an invalid day." +
                            " Please input something between and including 1 to 31.");
                    command = sc.nextLine();
                    day = Integer.valueOf(command);
                }
                System.out.println("Please type the hour for the new date");
                command = sc.nextLine();
                int hour = Integer.valueOf(command);
                while(!((1<=hour)&&(hour<=23))){
                    System.out.println("This is an invalid hour." +
                            " Please input something between and including 1 to 23.");
                    command = sc.nextLine();
                    hour = Integer.valueOf(command);
                }
                System.out.println("Please type the minutes for the new date");
                command = sc.nextLine();
                int minutes = Integer.valueOf(command);
                while(!((0<=minutes)&&(minutes<=59))){
                    System.out.println("This is an invalid minute." +
                            " Please input something between and including 0 to 59.");
                    command = sc.nextLine();
                    minutes = Integer.valueOf(command);
                }
                System.out.println("Please type the seconds for the new date");
                command = sc.nextLine();
                int seconds = Integer.valueOf(command);
                while(!((0<=seconds)&&(seconds<=59))){
                    System.out.println("This is an invalid second." +
                            " Please input something between and including 0 to 59");
                    command = sc.nextLine();
                    seconds = Integer.valueOf(command);
                }
                ATMTime.getInstance().setDate(year,month,day,hour,minutes,seconds);
                ManagerDisplay.main(null);
                break;
            case("2"):
                b.restockCashMachine();
                ManagerDisplay.main(null);
                break;
            case("3"):
                ATM.main(null);
                break;
        }
    }
}