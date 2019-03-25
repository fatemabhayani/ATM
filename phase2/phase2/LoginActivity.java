package phase2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;


public class LoginActivity extends JFrame{
    private JPanel panel = new JPanel();
    private JButton clickHereIfYouButton = new JButton();
    private JButton loginButton = new JButton();
    private JLabel loginPageLabel = new JLabel();
    private JTextField textField1 = new JTextField();
    private JPasswordField passwordField1;
    private User user;
    private boolean nameExists;
    private boolean passMatch;

    public LoginActivity() {
        setVisible(true);
        setSize(600,300);
        textField1.addInputMethodListener(new InputMethodListener() {

            @Override
            public void inputMethodTextChanged(InputMethodEvent event) {
                String input = textField1.getText();
                if (ATM.bankUsers.contains(input)) {
                    user = UserManager.getUser(input);
                    nameExists = true;
                }else {System.out.println("This is an invalid Username");}


            }

            @Override
            public void caretPositionChanged(InputMethodEvent event) {

            }
        });
        passwordField1.addInputMethodListener(new InputMethodListener() {
            @Override
            public void inputMethodTextChanged(InputMethodEvent event) {
                while(nameExists) {
                    String password  = String.valueOf(passwordField1.getPassword());
                    if(user.getPassword() == password) {
                        System.out.println("Login Successful");
                        passMatch =  true;
                        panel.setVisible(false);
                    }else{System.out.println("The password does not math the username");}
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
                while (nameExists&&passMatch) {
                    switchToAccount();
                }
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
    public void switchToRegister(){
        ManagerInterface man = new ManagerInterface();
        man.setVisible(true);
        man.pack();
        man.setLocationRelativeTo(null);
        this.dispose();

    }
    public void switchToAccount() {
        AccoutInterface acc = new AccoutInterface();
        acc.setVisible(true);
        acc.pack();
        acc.setLocationRelativeTo(null);
        this.dispose();

    }
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginActivity().setVisible(true);
            }
        });
    }
}
