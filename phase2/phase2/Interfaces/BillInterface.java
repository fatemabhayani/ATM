package phase2.Interfaces;

import phase2.Accounts.Account;
import phase2.Display.ATM;
import phase2.Tradable.*;
import phase2.People.User;
import phase2.Transactions.Bill;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * The type Bill interface.
 */
public class BillInterface extends JFrame {
    private JPanel root;
    private JTextField textField1;
    private JButton payBillButton;
    private int amount;
    private String currency;
    private static Account account1;
    private static User user1;

    /**
     * Instantiates a new Bill interface.
     *
     * @param account the account
     * @param user    the user
     */
    BillInterface(Account account, User user) {
        add(root);
        setSize(500,500);
        account1 = account;
        user1 = user;
        textField1.addInputMethodListener(new InputMethodListener() {
            @Override
            public void inputMethodTextChanged(InputMethodEvent event) {
                amount  = Integer.valueOf(textField1.getText());

            }

            @Override
            public void caretPositionChanged(InputMethodEvent event) {

            }
        });
        payBillButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                // Bill bill = new Bill(new ForeignCurrency("CAD",amount),account, ATM.clock.getCurrentTime());
                //user.makeTransaction(bill);
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

    /**
     * Main.
     *
     * @param args the args
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new BillInterface(account1,user1).setVisible(true));
    }

}
