package phase2.Interfaces;

import phase2.Accounts.Account;
import phase2.People.User;
import phase2.Transactions.Withdraw;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class AccountInterface extends JFrame {
    private JButton clickHereToAddButton;
    private JComboBox comboBox1;
    private JButton exitButton;
    private JButton makeTransactionButton;
    private JPanel root;
    private String choice;
    private Account inUse;

    public AccountInterface(Account A, User U) {
        inUse = A;
        add(root);
        setSize(500,500);
        clickHereToAddButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                choice = String.valueOf(comboBox1.getSelectedItem());
            }
        });
        makeTransactionButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (choice.equals("Bill")) {switchToBill(A, U);}
                else if (choice.equals("Transfer")) {switchToTransfer(A, U);}
                else if (choice.equals("Withdraw")) {switchToWithdraw(A, U);}
                else if (choice.equals("Deposit")) {switchToDeposit(A, U);}
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
