package phase2;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class UserInterface extends JFrame {
    private JCheckBox changePasswordCheckBox;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JButton setPasswordButton;
    private JButton makeTransactionButton;
    private JButton logOutButton;
    private JComboBox comboBox1;
    private JButton selectAccountTypeButton;
    private JComboBox comboBox2;
    private JButton addAccountButton;
    private boolean change;
    private String newPassword;
    private String verify;
    private ArrayList<Account> account;
    public Account a;
    public int i;

    public UserInterface(User U) {

        changePasswordCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(changePasswordCheckBox.isSelected()){ change = true;}

            }
        });
        passwordField2.addInputMethodListener(new InputMethodListener() {
            @Override
            public void inputMethodTextChanged(InputMethodEvent event) {
                newPassword = String.valueOf(passwordField2.getPassword());
            }

            @Override
            public void caretPositionChanged(InputMethodEvent event) {

            }
        });
        passwordField1.addInputMethodListener(new InputMethodListener() {
            @Override
            public void inputMethodTextChanged(InputMethodEvent event) {
                verify = String.valueOf(passwordField1.getPassword());
            }

            @Override
            public void caretPositionChanged(InputMethodEvent event) {

            }
        });
        setPasswordButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (verify.equals( newPassword)&&change) { U.setPassword(newPassword); }
            }
        });
        makeTransactionButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

            }
        });
        logOutButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
        selectAccountTypeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                a = account.get(i);
                switchToAccount();
            }
        });
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String command = comboBox1.getSelectedItem().toString();
                switch (command) {
                    case ("lc"):
                        account = U.getAccount("lc");
                        break;
                    case ("cc"):
                        account = U.getAccount("cc");
                        break;
                    case ("s"):
                        account = U.getAccount("s");
                        break;
                    case ("c"):
                        account = U.getAccount("c");
                        break;
                }
                for (int i = 0; i < account.size(); i++) {
                    comboBox2.addItem(String.valueOf(i));
                }
            }
        });
        comboBox2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                i = Integer.valueOf(comboBox2.getSelectedItem().toString());
            }
        });
    }
    public void switchToAccount(){ }
}
