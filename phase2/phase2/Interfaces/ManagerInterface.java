package phase2.Interfaces;

import phase2.Display.ATM;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class ManagerInterface extends JFrame {
    private JComboBox comboBox1;
    private JTextField textField1;
    private JButton acceptRequestButton;
    private JButton declineUserButton;
    private JTextField welcomeMrManagerTextField;
    private String option;

    public ManagerInterface() {
        for (int i = 0; i <  ATM.b.getNumberOfRequests(); i++) {
            comboBox1.addItem(ATM.b.getRequest(i).toString()+"\n");
        }

        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                option = comboBox1.getSelectedItem().toString();
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
    public static void main(String[] arg){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ManagerInterface().setVisible(true);
            }
        });

    }
}
