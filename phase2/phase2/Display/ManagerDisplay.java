package phase2.Display;

import phase2.ATMTime;
import phase2.People.BankManager;
import phase2.Request.*;

import java.util.Scanner;

/**
 * The type Manager display.
 */
class ManagerDisplay {

    private static final ATMController controller = new ATMController();

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

        BankManager b = BankManager.getInstance();
        System.out.println("Hello bank manager, let's check the ATM alerts first!");

        Scanner sc = new Scanner(System.in);
        String command;
        b.restockCashMachine();

        System.out.println("Type '0' to view requests. Type '1' to set date. Type '2' to view Cash Machine alerts");
        command = sc.nextLine();
        command = command.replaceAll("//s", "");
        while (!(command.toLowerCase().equals("0")||command.toLowerCase().equals("1")
                ||command.toLowerCase().equals("2"))) {
            System.out.println("Wrong answer, try again");
            System.out.println("Type '0' to view requests. Type '1' to set date. Type '2' to view Cash Machine alerts");
            command = sc.nextLine();
            command = command.replaceAll("//s", "");
        }
        System.out.println(b.getRequestSummary());
        if (b.getRequestSummary().equals("You have no requests.")) {
            ATM.main(null);
        }

        boolean exited = false;
        while (!exited) {
            if (command.toLowerCase().equals("0")) {
                System.out.println("Which request do you want to address? Put in the appropriate number only." + "\n" +
                        "Type '0' for the oldest request");
                command = sc.nextLine();
                command = command.replaceAll("//s", "");
                int number = Integer.valueOf(command);
                while (number > b.getNumberOfRequests()){
                    System.out.println("This index is not assigned to a request. Please Try again");
                    command = sc.nextLine();
                    command = command.replaceAll("//s", "");
                    number = Integer.valueOf(command);
                }
                Request request = b.getRequest(number);
                System.out.println(request.toString());
                System.out.println("Do you want to accept this request");
                command = sc.nextLine();
                command = command.replaceAll("//s", "");
                controller.incorrectAns(sc,command);
                if (command.equals("yes")) {
                    b.completeRequest(number);
                } else {
                    b.ignoreRequest(number);
                }
                System.out.println("Would you like to exit?");
                command = sc.nextLine();
                command = command.replaceAll("//s", "");
                if (command.toLowerCase().equals("yes")) { exited = true; }
            }else if (command.toLowerCase().equals("1")){
                System.out.println("Please type the year for the new Date");
                command = sc.nextLine();
                int year = Integer.valueOf(command);
                System.out.println("Please type the month for the new Date");
                command = sc.nextLine();
                int month = Integer.valueOf(command);
                while(!((0<=month)&&(month<=11))){
                    System.out.println("This is an invalid month." +
                            " Please input something between and including 0 to 11");
                    command = sc.nextLine();
                    month = Integer.valueOf(command);
                }
                System.out.println("Please type the day for the new Date");
                command = sc.nextLine();
                int day = Integer.valueOf(command);
                while(!((1<=day)&&(day<=30))){
                    System.out.println("This is an invalid day." +
                            " Please input something between and including 1 to 30");
                    command = sc.nextLine();
                    day = Integer.valueOf(command);
                }
                System.out.println("Please type the hour for the new Date");
                command = sc.nextLine();
                int hour = Integer.valueOf(command);
                while(!((1<=hour)&&(hour<=24))){
                    System.out.println("This is an invalid hour." +
                            " Please input something between and including 1 to 24");
                    command = sc.nextLine();
                    hour = Integer.valueOf(command);
                }
                System.out.println("Please type the minutes for the new Date");
                command = sc.nextLine();
                int minutes = Integer.valueOf(command);
                while(!((0<=minutes)&&(minutes<=60))){
                    System.out.println("This is an invalid month." +
                            " Please input something between and including 0 to 11");
                    command = sc.nextLine();
                    minutes = Integer.valueOf(command);
                }
                System.out.println("Please type the seconds for the new Date");
                command = sc.nextLine();
                int seconds = Integer.valueOf(command);
                while(!((0<=seconds)&&(seconds<=60))){
                    System.out.println("This is an invalid month." +
                            " Please input something between and including 0 to 11");
                    command = sc.nextLine();
                    seconds = Integer.valueOf(command);
                }
                ATMTime.getInstance().setDate(year,month,day,hour,minutes,seconds);
            }else {b.restockCashMachine();}


        }
        ATM.main(null);

    }



}
