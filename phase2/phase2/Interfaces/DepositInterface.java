package phase2.Interfaces;

import phase2.Accounts.Account;
import phase2.Display.ATM;
import phase2.People.User;
import phase2.Transactions.Deposit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;

public class DepositInterface extends JFrame {

    private JButton depositIncomingTransfersButton;
    private JPanel root;
    private static Account account;
    private static User user;

    public DepositInterface(Account a, User U) {
        add(root);
        setVisible(true);
        this.account = a;
        this.user = U;
        setSize(500,500);
        depositIncomingTransfersButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                // Calendar time = ATM.clock.getCurrentTime();
                // Deposit t = new Deposit("deposits.txt", a, time);
                // U.makeTransaction(t);
                switchToLogin();
            }
        });
    }
    private void switchToLogin(){
        LoginActivity LA = new LoginActivity();
        LA.setVisible(true);
        LA.pack();
        LA.setLocationRelativeTo(null);
        this.dispose();
    }
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DepositInterface(account,user).setVisible(true);
            }
        });
    }
}
