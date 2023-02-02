package CC12.Richard.Group1.Assign2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaymentProcessedGUI extends GUI {


    public PaymentProcessedGUI(Controller controller) {

        super(controller);
        setLayout(new GridLayout(5, 1));
        setMinimumSize(new Dimension(900, 700));
        getContentPane().setBackground(Color.darkGray);

        add(createFirstPanel());
        add(createSecondPanel());
        add(createThirdPanel());
        add(createFourthPanel());
        add(createFifthPanel());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Store");
        setVisible(true);

    }

    private JPanel createFirstPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 3, 0, 30));
        panel.setBackground(Color.darkGray);

        //Create back button
        JButton back = new JButton("Back");
        back.setBackground(Color.darkGray);
        back.setForeground(Color.lightGray);
        back.setFocusable(false);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                controller.setUser(new User());
                backFunction();

            }
        });
        panel.add(back);

        panel.add(new JLabel(""));

        //Create Icon
        JLabel image = new JLabel();
        image.setHorizontalAlignment(JLabel.TRAILING);
        image.setIcon(new ImageIcon("src/main/resources/LiteSnacks.png"));
        panel.add(image);

        return panel;
    }

    private JPanel createSecondPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 1));
        panel.setBackground(Color.darkGray);

        //Create farewell label
        JLabel farewell = new JLabel("Payment Processed!");
        farewell.setForeground(Color.lightGray);
        farewell.setHorizontalAlignment(JLabel.CENTER);
        farewell.setFont(new Font("Calibri", Font.BOLD, 25));
        panel.add(farewell);


        return panel;
    }
    private JPanel createThirdPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 1));
        panel.setBackground(Color.darkGray);



        //Create title
        JLabel tick = new JLabel();
        tick.setHorizontalAlignment(JLabel.CENTER);

        tick.setIcon(new ImageIcon("src/main/resources/purchaseTick.png"));

        panel.add(tick);
//        panel.add(new JLabel(""));

        return panel;
    }

    private JPanel createFourthPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 1));
        panel.setBackground(Color.darkGray);

        //Create farewell label
        JLabel farewell = new JLabel("Enjoy Your Food!");
        farewell.setForeground(Color.lightGray);
        farewell.setHorizontalAlignment(JLabel.CENTER);
        farewell.setFont(new Font("Calibri", Font.BOLD, 15));
        panel.add(farewell);


        return panel;
    }

    private JPanel createFifthPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 4));
        panel.setBackground(Color.darkGray);

        //Create farewell label
        JLabel total = new JLabel("Total: $" + controller.getUser().getTotalCost());
        total.setForeground(Color.lightGray);
        total.setHorizontalAlignment(JLabel.TRAILING);
        total.setFont(new Font("Calibri", Font.BOLD, 20));
        panel.add(total);

        panel.add(new JLabel(""));


        JLabel change = new JLabel("Change: ");
        change.setForeground(Color.lightGray);
        change.setHorizontalAlignment(JLabel.TRAILING);
        change.setFont(new Font("Calibri", Font.BOLD, 20));
        panel.add(change);

        JPanel denomGivenPanel = new JPanel();
        denomGivenPanel.setLayout(new BoxLayout(denomGivenPanel, BoxLayout.Y_AXIS));
        denomGivenPanel.setBackground(Color.darkGray);
        for (Denom denom : controller.getDenomChangeGiven()) {
            if (denom.getQuantity() > 0) {
                JLabel changeLabel = new JLabel("$" + denom.getDenom() + " : " + denom.getQuantity());
                changeLabel.setForeground(Color.lightGray);
                changeLabel.setFont(new Font("Calibri", Font.BOLD, 20));
                denomGivenPanel.add(changeLabel);
            }
        }
        panel.add(denomGivenPanel);
        return panel;
    }
}
