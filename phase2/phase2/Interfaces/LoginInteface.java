package phase2.Interfaces;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;

public class LoginInteface extends JFrame{
    private JLabel loginPageLabel;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton loginButton;
    private JButton clickHereToRegisterButton;
    private JPanel root;
    private JLabel usernameLabel;
    private JLabel passwordLabel;

    public LoginInteface() {
        add(root);
        textField1.addInputMethodListener(new InputMethodListener() {
            @Override
            public void inputMethodTextChanged(InputMethodEvent event) {

            }

            @Override
            public void caretPositionChanged(InputMethodEvent event) {

            }
        });
    }
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginInteface().setVisible(true);
            }
        });
    }
}
