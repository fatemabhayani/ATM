package phase2.Interfaces;

import phase2.Display.ATM;
import phase2.People.BankManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * The type Manager interface.
 */
class ManagerInterface extends JFrame {
    private JComboBox comboBox1;
    private JTextField textField1;
    private JButton acceptRequestButton;
    private JButton declineUserButton;
    private JTextField welcomeMrManagerTextField;
    private JPanel root;
    private String option;

    /**
     * Instantiates a new Manager interface.
     */
    ManagerInterface() {
        add(root);
        setSize(500,500);

        for (int i = 0; i <  BankManager.getInstance().getNumberOfRequests(); i++) {
            comboBox1.addItem(BankManager.getInstance().getRequest(i).toString()+"\n");
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

    /**
     * Main.
     *
     * @param arg the arg
     */
    public static void main(String[] arg){
        EventQueue.invokeLater(() -> new ManagerInterface().setVisible(true));

    }
}
