package phase2.Interfaces;

import phase2.People.UserManager;

import javax.swing.*;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class RegisterUserInterface extends JFrame {
    private JTextField textField1;
    private JButton requestUserButton;

    public RegisterUserInterface() {
        requestUserButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }
        });
        textField1.addInputMethodListener(new InputMethodListener() {
            @Override
            public void inputMethodTextChanged(InputMethodEvent event) {
                String username = textField1.getText();
                if(UserManager.authenticateUsername(username)) {
                    switchToLogin();
                }


            }

            @Override
            public void caretPositionChanged(InputMethodEvent event) {

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
}
