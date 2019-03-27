package phase2.Interfaces;

import phase2.Display.ATM;
import phase2.People.User;
import phase2.People.UserManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


class LoginActivity extends JFrame{
    private JPanel root;
    private JButton clickHereIfYouButton;
    private JButton loginButton;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JLabel loginPageLabel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private User user;
    private boolean nameExists = false;
    private boolean passMatch = false;
    private String manager;
    private boolean managerlogin = false;

    public LoginActivity() {
        add(root);
        textField1.addInputMethodListener(new InputMethodListener() {
            @Override
            public void inputMethodTextChanged(InputMethodEvent event) {
                String input = textField1.getText();
                if (ATM.bankUsers.contains(input)) {
                    user = UserManager.getUser(input);
                    nameExists = true;
                } else if (input.equals("manager")){
                    manager = input;
                } else {nameExists = false;}
            }

            @Override
            public void caretPositionChanged(InputMethodEvent event) {

            }
        });
        passwordField1.addInputMethodListener(new InputMethodListener() {
            @Override
            public void inputMethodTextChanged(InputMethodEvent event) {
                String password  = String.valueOf(passwordField1.getPassword());
                if (nameExists) {
                    if(user.getPassword().equals(password)) {
                        System.out.println("Login Successful");
                        passMatch =  true;
                    }else{passMatch = false;}
                }else if (manager.equals("manager")){
                    managerlogin = password.equals("bestboss");
                }
            }

            @Override
            public void caretPositionChanged(InputMethodEvent event) {

            }
        });
        clickHereIfYouButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                switchToRegister();
            }
        });
        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (nameExists&&passMatch) {
                    switchToUser();
                }else if (managerlogin) {
                    switchToManager();
                }
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
    private void switchToRegister(){
        RegisterUserInterface RUI = new RegisterUserInterface();
        RUI.setVisible(true);
        RUI.pack();
        RUI.setLocationRelativeTo(null);
        this.dispose();
    }

    private void switchToManager(){
        ManagerInterface MI = new ManagerInterface();
        MI.setVisible(true);
        MI.pack();
        MI.setLocationRelativeTo(null);
        this .dispose();
    }
    private void switchToUser(){
        UserInterface UI = new UserInterface(user);
        UI.setVisible(true);
        UI.pack();
        UI.setLocationRelativeTo(null);
        this.dispose();
    }
    public void switchToAccount() {
      //  AccountInterface acc = new AccountInterface();
        //acc.setVisible(true);
        //acc.pack();
        //acc.setLocationRelativeTo(null);
        this.dispose();

    }
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginActivity().setVisible(true);
            }
        });
    }
}
