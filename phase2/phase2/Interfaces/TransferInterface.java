package phase2.Interfaces;

import phase2.Accounts.Account;
import phase2.Display.ATM;
import phase2.Tradable.*;
import phase2.People.User;
import phase2.People.UserManager;
import phase2.Transactions.Transfer;

import javax.swing.*;
import javax.xml.crypto.dsig.TransformService;
import java.awt.event.*;
import java.util.ArrayList;

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
    private JTextField textField2;
    private boolean isexternal;
    private boolean isinternal;
    private User reciever;
    private String currency;
    private int number;
    private int amount;
    private Account accoutTo;
    private ArrayList<Account> ac;

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
                // Transfer t = new Transfer(new ForeignCurrency(currency,amount),A,ac.get(number), ATM.clock.getCurrentTime());
                // u.makeTransaction(t);


            }
        });
        comboBox2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                number = Integer.valueOf(comboBox2.getSelectedItem().toString());

            }
        });
        comboBox3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isexternal) {
                    String r = comboBox3.getSelectedItem().toString();
                    ac = reciever.getAccountList(r);
                    if (ac.size()>0) {
                        for (int i = 0; i< ac.size(); i++) {
                            comboBox2.addItem(String.valueOf(i));
                        }
                    }else {System.out.println("There are no accounts to select from." +
                            " Please Select a different typ of account");}
                }

            }
        });
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isinternal) {
                    String r = comboBox1.getSelectedItem().toString();
                    ac = u.getAccountList(r);
                    if (ac.size()>0) {
                        for (int i = 0; i< ac.size(); i++) {
                            comboBox2.addItem(String.valueOf(i));
                        }
                    }else {System.out.println("There are no accounts to select from." +
                            " Please Select a different typ of account");}
                }
            }
        });
        externalTransactionCheckBox.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                isexternal = externalTransactionCheckBox.isSelected();
            }
        });
        internalTransactionCheckBox.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                isinternal = internalTransactionCheckBox.isSelected();
            }
        });
        textField2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currency = textField2.getText();
            }
        });
        textField1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reciever = UserManager.getUser(textField1.getText());
            }
        });
    }
}
