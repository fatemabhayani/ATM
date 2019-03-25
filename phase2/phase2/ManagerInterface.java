package phase2;

import javax.swing.*;
import java.awt.event.*;

public class ManagerInterface extends JFrame {
    private JComboBox comboBox1;
    private JTextField textField1;
    private JButton acceptRequestButton;
    private JButton declineUserButton;
    private JTextField welcomeMrManagerTextField;

    public ManagerInterface() {
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        textField1.addInputMethodListener(new InputMethodListener() {
            @Override
            public void inputMethodTextChanged(InputMethodEvent event) {

            }

            @Override
            public void caretPositionChanged(InputMethodEvent event) {

            }
        });
        declineUserButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
        acceptRequestButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
    }
}
