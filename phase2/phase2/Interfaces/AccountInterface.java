package phase2.Interfaces;

import phase2.Accounts.Account;
import phase2.People.User;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * The type Account interface.
 */
class AccountInterface extends JFrame {
    private JButton clickHereToAddButton;
    private JComboBox comboBox1;
    private JButton exitButton;
    private JButton makeTransactionButton;
    private JPanel root;
    private String choice;
    private Account inUse;

    /**
     * Instantiates a new Account interface.
     *
     * @param A the account
     * @param U the user
     */
    AccountInterface(Account A, User U) {
        inUse = A;
        add(root);
        setSize(500,500);
        clickHereToAddButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
        comboBox1.addActionListener(e -> choice = String.valueOf(comboBox1.getSelectedItem()));
        makeTransactionButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                switch (choice) {
                    case "Bill":
                        switchToBill(A, U);
                        break;
                    case "Transfer":
                        switchToTransfer(A, U);
                        break;
                    case "Withdraw":
                        switchToWithdraw(A, U);
                        break;
                    case "Deposit":
                        switchToDeposit(A, U);
                        break;
                }
            }
        });
        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
    }
    private void switchToBill(Account account,User user){
        BillInterface BI = new BillInterface(account,user);
        BI.setVisible(true);
        BI.pack();
        BI.setLocationRelativeTo(null);
        this.dispose();
    }
    private void switchToTransfer(Account account,User user){
        TransferInterface TI = new TransferInterface(account, user);
        TI.setVisible(true);
        TI.pack();
        TI.setLocationRelativeTo(null);
        this.dispose();
    }
    private void switchToWithdraw(Account account, User user){
        WithdrawInterface WI = new WithdrawInterface(account, user);
        WI.setVisible(true);
        WI.pack();
        WI.setLocationRelativeTo(null);
        this.dispose();
    }
    private void switchToDeposit(Account account, User user){
        DepositInterface DI = new DepositInterface(account, user);
        DI.setVisible(true);
        DI.pack();
        DI.setLocationRelativeTo(null);
        this.dispose();
    }
}
