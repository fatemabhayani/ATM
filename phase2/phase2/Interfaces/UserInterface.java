package phase2.Interfaces;

import phase2.Accounts.Account;
import phase2.Display.ATM;
import phase2.Display.UserDisplay;
import phase2.People.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import static phase2.Display.UserDisplay.*;

class UserInterface extends JFrame {
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
    private JPanel root;
    private boolean change;
    private String newPassword;
    private String verify;
    private ArrayList<Account> account;
    private Account a;
    public int i;

    public UserInterface(User U) {
        add(root);
        setSize(500,500);

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
                switchToAccount(a,U);
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
                    if (String.valueOf(i)!=null){
                        comboBox2.addItem(String.valueOf(i));
                    }

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
    private void switchToAccount(Account A,User user){
        AccountInterface AI = new AccountInterface(A,user);
        AI.setVisible(true);
        AI.pack();
        AI.setLocationRelativeTo(null);
        this.dispose();

    }
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new UserInterface(U).setVisible(true);
            }
        });
    }
}
