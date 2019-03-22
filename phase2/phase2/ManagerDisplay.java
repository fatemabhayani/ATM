package phase2;

import java.util.Scanner;

public class ManagerDisplay {

    public static void main(String[] args) {

        BankManager b = ATM.b;
        System.out.println("Hello bank manager, let's check the ATM alerts first!");

        Scanner sc = new Scanner(System.in);
        String command;
        b.restockCashMachine();

        System.out.println("Do you want to view your requests?");
        command = sc.nextLine();
        command = command.replaceAll("//s", "");
        while (!command.toLowerCase().equals("yes")) {
            System.out.println("Wrong answer, try again");
            System.out.println("Do you want to view your requests?");
            command = sc.nextLine();
            command = command.replaceAll("//s", "");
        }
        System.out.println(b.getRequestSummary());
        if (b.getRequestSummary().equals("You have no requests.")) {
            ATM.main(null);
        }

        boolean exited = false;
        while (!exited) {
            System.out.println("Which request do you want to address? Put in the appropriate number only." + "\n" +
                    "Type '0' for the oldest request");
            command = sc.nextLine();
            command = command.replaceAll("//s", "");
            int number = Integer.valueOf(command);

            System.out.println(b.getRequest(number).toString());
            System.out.println("Do you want to accept this request");
            command = sc.nextLine();
            command = command.replaceAll("//s", "");
            if (command.equals("yes")) {
                b.completeRequest(number);

            } else {
                b.ignoreRequest(number);
            }
            System.out.println("Would you like to exit?");
            command = sc.nextLine();
            command = command.replaceAll("//s", "");
            if (command.toLowerCase().equals("yes")) { exited = true; }
        }
        ATM.main(null);

    }



}
