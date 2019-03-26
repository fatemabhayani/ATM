package phase2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AccountInterface extends JFrame {
    private JButton clickHereToAddButton;
    private JComboBox comboBox1;
    private JButton exitButton;
    private JButton makeTransactionButton;

    public AccountInterface() {
        clickHereToAddButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String choice = String.valueOf(comboBox1.getSelectedItem());
                if (choice.equals("Bill")) { switchToBill(); }
                else if (choice.equals("Transfer")) {switchToTransfer();}
                else if (choice.equals("Withdraw")) {switchToWithdraw();}
                else if (choice.equals("Deposit")) {switchToDeposit();}

            }
        });
        makeTransactionButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
    }
    public void switchToBill(){}
    public void switchToTransfer(){}
    public void switchToWithdraw(){}
    public void switchToDeposit(){}
}
