package phase2.Display;

import phase2.ATMTime;
import phase2.Accounts.Account;
import phase2.People.User;
import phase2.Tradable.ForeignCurrency;
import phase2.Transactions.Bill;
import phase2.Transactions.Transaction;
import phase2.Transactions.Withdraw;

import java.util.Calendar;
import java.util.Scanner;

class ATMController {

    public ATMController(){}

    public void incorrectAns(Scanner scanner, String inUse){
        while (!(inUse.toLowerCase().equals("no") ||inUse.toLowerCase().equals("yes"))){
            System.out.println("You did not give a valid answer, try again");
            inUse = scanner.nextLine();
        }
    }
    public void managerUse(Scanner sc, String command){
        if (command.equals("bankmanager")){
            System.out.println("Please input your password");
            command = sc.nextLine();
            command = command.replaceAll("//s", "");

            while (!(command.toLowerCase().equals("bestboss"))){
                System.out.println("You did not give a valid password, try again, if you would like to go to the start screen type exit");
                command = sc.nextLine();
                if(command.toLowerCase().equals("exit")){
                    ATM.main(null);
                }
            }
            ManagerDisplay.main(null);

        }
    }
    public void BillWithdraw(User U, Transaction t, Account a){
        Scanner tmp = new Scanner(System.in);
        System.out.println("Enter the sum of money you wish to withdraw.");
        String command = tmp.nextLine();
        int amt = Integer.valueOf(command.replaceAll("//s", ""));
        Calendar time = ATMTime.getInstance().getCurrentTime();
        if (t instanceof Withdraw){
            t = new Withdraw(new ForeignCurrency("CAD", amt), a, time);
        }else if (t instanceof Bill) {
            System.out.println("Enter the three digit currency code of the currency you wish to pay the bill in");
            command = tmp.nextLine();
            command = command.replaceAll("//s", "");
            t = new Bill(new ForeignCurrency(command, amt), a, time);
        }

        U.makeTransaction(t);

        AccountDisplay.main(null);
    }


}
