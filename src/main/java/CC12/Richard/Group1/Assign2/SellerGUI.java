package CC12.Richard.Group1.Assign2;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SellerGUI extends GUI {


        public SellerGUI(Controller controller) {

            super(controller);
            setLayout(new GridLayout(12, 1));
            setMinimumSize(new Dimension(900, 700));
            getContentPane().setBackground(Color.darkGray);


            add(createFirstPanel());
            add(createButtonPanel());
            add(createThirdPanel());
            add(createSecondPanel());
            add(createFifthPanel());
            add(createFourthPanel());
            add(createSeventhPanel());
            add(createSixthPanel());
            add(createSeventhPanel());
            add(createEighthPanel());
            add(createSeventhPanel());
            add(createNinthPanel());

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setTitle("Store");
            setVisible(true);

        }


        private void setPanelDefaults(JPanel panel) {
            panel.setBackground(Color.darkGray);

        }

        private void setTitleDefault(JLabel label) {
            label.setForeground(Color.lightGray);
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setBorder(new LineBorder(Color.lightGray));
            label.setFont(new Font("Calibri", Font.BOLD, 20));
        }



        private JPanel createFirstPanel() {
            JPanel panel = new JPanel(new GridLayout(1, 3, 2, 30));
            panel.setBackground(Color.darkGray);

            panel.add(new JLabel(""));

            //    JPanel panel = new JPanel();

            setPanelDefaults(panel);
            panel.setLayout(new GridLayout(2, 1));

            //Create top label panel
            JPanel topPanel = new JPanel();
            setPanelDefaults(topPanel);
            topPanel.setLayout(new GridLayout(1, 3));

            String signInAsString = String.format("Signed in as: %s", controller.getUser().getName());
            JLabel signInText = new JLabel(signInAsString);
            signInText.setForeground(Color.lightGray);
            signInText.setHorizontalAlignment(JLabel.CENTER);

            JLabel pageLabel = new JLabel("Seller Home");
            setTitleDefault(pageLabel);

            JLabel image = new JLabel();
            image.setHorizontalAlignment(JLabel.TRAILING);
            image.setIcon(new ImageIcon("src/main/resources/LiteSnacks.png"));

            topPanel.add(signInText);
            topPanel.add(pageLabel);
            topPanel.add(image);
            panel.add(topPanel);

            return panel;
        }

        private JPanel createButtonPanel() {
            JPanel panel = new JPanel(new GridLayout(1, 3));
            panel.setBackground(Color.darkGray);

            //Create back button
            JButton owner = new JButton("Owner");
            owner.setBackground(Color.darkGray);
            owner.setForeground(Color.lightGray);
            owner.setFocusable(false);
            if (!controller.getUser().getName().equals("owner")) {
                owner.setEnabled(false);
            }
            owner.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    closeFrame();
                    new OwnerGUI(controller);
                }
            });
            JButton cashierButton = new JButton("Cashier");
            cashierButton.setBackground(Color.darkGray);
            cashierButton.setForeground(Color.lightGray);
            cashierButton.setFocusable(false);
            if (!controller.getUser().getName().equals("owner")) {
                cashierButton.setEnabled(false);
            }
            cashierButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    closeFrame();
                    new CashierGUI(controller);
                }
            });

            JButton sellerButton = new JButton("Seller");
            sellerButton.setBackground(Color.darkGray);
            sellerButton.setForeground(Color.lightGray);
            sellerButton.setFocusable(false);


            JPanel backPanel = new JPanel();
            setPanelDefaults(backPanel);
            backPanel.setLayout(new GridLayout(1, 3));
            backPanel.add(owner);
            backPanel.add(cashierButton);
            backPanel.add(sellerButton);
            panel.add(backPanel);
            panel.add(new JLabel(""));
            panel.add(new JLabel(""));

            return panel;
        }

        private JPanel createSecondPanel() {
            JPanel panel = new JPanel(new GridLayout(1, 3));
            panel.setBackground(Color.darkGray);

            //Create username label
            JButton username = new JButton("Drinks");
            username.setBackground(Color.darkGray);
            username.setForeground(Color.lightGray);
            username.setFocusable(false);
            username.setHorizontalAlignment(JLabel.CENTER);
            username.setVerticalAlignment(JLabel.CENTER);
            username.setFont(new Font("Calibri", Font.BOLD, 20));
            username.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    closeFrame();
                    new ItemTypePageGUI(controller, "Drinks", 0);
                }
            });
            panel.add(username);

            panel.add(new JLabel(""));
            panel.add(new JLabel(""));

            return panel;
        }

        private JPanel createThirdPanel() {
            JPanel panel = new JPanel(new GridLayout(1, 3));
            panel.setBackground(Color.darkGray);



            //Create textField Panel

            panel.add(new JLabel(""));
            panel.add(new JLabel(""));


            JButton reportButton = new JButton("Current Items Report");
            reportButton.setBackground(Color.darkGray);
            reportButton.setForeground(Color.lightGray);
            reportButton.setFocusable(false);
            reportButton.setHorizontalAlignment(JLabel.TRAILING);
            reportButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ItemDao.generateAvailableProductsReport(null);
                    JOptionPane.showMessageDialog(null, "Report Generated.", "Working on it..", JOptionPane.INFORMATION_MESSAGE);
                }
            });

            panel.add(reportButton);

            return panel;
        }


        private JPanel createFourthPanel() {
            JPanel panel = new JPanel(new GridLayout(1, 3));
            panel.setBackground(Color.darkGray);

            //Create username label
            JButton username = new JButton("Chocolate");
            username.setBackground(Color.darkGray);
            username.setForeground(Color.lightGray);
            username.setFocusable(false);
            username.setHorizontalAlignment(JLabel.CENTER);
            username.setVerticalAlignment(JLabel.CENTER);
            username.setFont(new Font("Calibri", Font.BOLD, 20));
            username.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    closeFrame();
                    new ItemTypePageGUI(controller, "Chocolate", 1);
                }
            });
            panel.add(username);

            panel.add(new JLabel(""));
            panel.add(new JLabel(""));

            return panel;
        }

        private JPanel createFifthPanel() {
            JPanel panel = new JPanel(new GridLayout(1, 3));
            panel.setBackground(Color.darkGray);



            //Create textField Panel

            panel.add(new JLabel(""));
            panel.add(new JLabel(""));


            JButton reportButton = new JButton("Quantity Sold Report");
            reportButton.setBackground(Color.darkGray);
            reportButton.setForeground(Color.lightGray);
            reportButton.setFocusable(false);
            reportButton.setHorizontalAlignment(JLabel.TRAILING);
            reportButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ItemDao.generateProductsSoldReport(null);
                    JOptionPane.showMessageDialog(null, "Report Generated.", "Working on it..", JOptionPane.INFORMATION_MESSAGE);
                }
            });


            panel.add(reportButton);

            return panel;
        }
        private JPanel createSixthPanel() {
            JPanel panel = new JPanel(new GridLayout(1, 3));
            panel.setBackground(Color.darkGray);

            //Create username label
            JButton username = new JButton("Chips");
            username.setBackground(Color.darkGray);
            username.setForeground(Color.lightGray);
            username.setFocusable(false);
            username.setHorizontalAlignment(JLabel.CENTER);
            username.setVerticalAlignment(JLabel.CENTER);
            username.setFont(new Font("Calibri", Font.BOLD, 20));
            username.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    closeFrame();
                    new ItemTypePageGUI(controller, "Chips", 2);
                }
            });
            panel.add(username);

            panel.add(new JLabel(""));
            panel.add(new JLabel(""));

            return panel;
        }

        private JPanel createSeventhPanel() {
            JPanel panel = new JPanel(new GridLayout(1, 1));
            panel.setBackground(Color.darkGray);

            return panel;
        }

        private JPanel createEighthPanel() {
            JPanel panel = new JPanel(new GridLayout(1, 3));
            panel.setBackground(Color.darkGray);

            //Create username label
            JButton username = new JButton("Candies");
            username.setBackground(Color.darkGray);
            username.setForeground(Color.lightGray);
            username.setFocusable(false);
            username.setHorizontalAlignment(JLabel.CENTER);
            username.setVerticalAlignment(JLabel.CENTER);
            username.setFont(new Font("Calibri", Font.BOLD, 20));
            username.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    closeFrame();
                    new ItemTypePageGUI(controller, "Candies", 3);
                }
            });
            panel.add(username);

            panel.add(new JLabel(""));
            panel.add(new JLabel(""));

            return panel;
        }

        private JPanel createNinthPanel() {
            JPanel panel = new JPanel(new GridLayout(1, 3));
            panel.setBackground(Color.darkGray);
            //Create textField Panel


            JButton signOut = new JButton("Sign Out");
            signOut.setBackground(Color.darkGray);
            signOut.setForeground(Color.lightGray);
            signOut.setFocusable(false);
            signOut.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    controller.getUser().setName("guest");
                    closeFrame();
                    new SaleGUI(controller);
                }
            });


            // panel.add(new JLabel(""));
            panel.add(signOut);

            return panel;
        }
}
