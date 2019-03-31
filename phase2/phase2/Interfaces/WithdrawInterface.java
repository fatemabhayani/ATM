package phase2.Interfaces;

import phase2.Accounts.Account;
import phase2.People.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * The type Withdraw interface.
 */
public class WithdrawInterface extends JFrame {
    private JTextField textField1;
    private JPanel root;
    private JButton withdrawButton;
    private JLabel withdrawLabel;
    private JLabel enterTheSumYouLabel;
    private int amount;
    private static Account account;
    private static User user;

    /**
     * Instantiates a new Withdraw interface.
     *
     * @param a the account
     * @param U the user
     */
    WithdrawInterface(Account a, User U) {
        add(root);
        setVisible(true);
        setSize(500,500);
        account = a;
        user = U;
        textField1.addInputMethodListener(new InputMethodListener() {
            @Override
            public void inputMethodTextChanged(InputMethodEvent event) {
                amount = Integer.valueOf(textField1.getText());
            }

            @Override
            public void caretPositionChanged(InputMethodEvent event) {

            }
        });
        withdrawButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                //Calendar time = ATM.clock.getCurrentTime();
                //Withdraw t = new Withdraw(new ForeignCurrency("CAD", amount), a, time);
                //U.makeTransaction(t);
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
        EventQueue.invokeLater(() -> new WithdrawInterface(account,user).setVisible(true));
    }
}
