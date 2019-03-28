package phase2.Interfaces;

import phase2.Accounts.Account;
import phase2.People.User;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * The type Transfer interface.
 */
public class TransferInterface extends JFrame{
    private JPanel root;
    private JCheckBox internalTransactionCheckBox;
    private JCheckBox externalTransactionCheckBox;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    private JTextField textField1;
    private JLabel receiverUsernameLabel;
    private JLabel yourAccountTypeLabel;
    private JLabel receiverAccountLabel;
    private JLabel yourAccountNumberLabel;
    private JButton completeTransferButton;
    private JLabel recieverUsernameLabel;
    private JLabel recieverAccountLabel;

    /**
     * Instantiates a new Transfer interface.
     *
     * @param A the account
     * @param u the user
     */
    TransferInterface(Account A, User u) {
        add(root);
        setVisible(true);
        setSize(500,500);
        completeTransferButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
    }
}
