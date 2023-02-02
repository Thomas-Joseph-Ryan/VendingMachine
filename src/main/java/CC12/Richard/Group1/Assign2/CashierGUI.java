package CC12.Richard.Group1.Assign2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.LineBorder;

public class CashierGUI extends GUI {


    public CashierGUI(Controller controller) {

        super(controller);
        setLayout(new GridLayout(15, 1));
        setMinimumSize(new Dimension(1300, 900));
        getContentPane().setBackground(Color.darkGray);


        add(createFirstPanel());
        add(createButtonPanel());
        add(createSecondPanel());
        add(createThirdPanel());
        add(createspace());
        add(createFourthPanel());
        add(createspace());
        add(createFifthPanel());
        add(createspace());
        add(createSixthPanel());
        add(createspace());
        add(createSeventhPanel());
        add(createspace());
        add(createEighthPanel());
        add(createNinthPanel());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Cashier");
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


    private JPanel createspace(){
        JPanel panel = new JPanel(new GridLayout(1, 0));

        panel.setBackground(Color.darkGray);
        return panel;
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

        JLabel pageLabel = new JLabel("Cashier Home");
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

        JButton sellerButton = new JButton("Seller");
        sellerButton.setBackground(Color.darkGray);
        sellerButton.setForeground(Color.lightGray);
        sellerButton.setFocusable(false);
        if (!controller.getUser().getName().equals("owner")) {
            sellerButton.setEnabled(false);
        }
        sellerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeFrame();
                new SellerGUI(controller);
            }
        });


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
        JPanel panel = new JPanel(new GridLayout(1, 6));
        panel.setBackground(Color.darkGray);

        //Create username label
        JLabel CV1 = new JLabel("Value");
        CV1.setForeground(Color.lightGray);
        CV1.setHorizontalAlignment(JLabel.CENTER);
        CV1.setVerticalAlignment(JLabel.CENTER);
        CV1.setFont(new Font("Calibri", Font.BOLD, 20));
        panel.add(CV1);

        JLabel qty1 = new JLabel("Quantity");
        qty1.setForeground(Color.lightGray);
//        qty.setHorizontalAlignment(JLabel.CENTER);
        qty1.setVerticalAlignment(JLabel.CENTER);
        qty1.setFont(new Font("Calibri", Font.BOLD, 20));
        panel.add(qty1);

        //Create username label
        JLabel CV2 = new JLabel("Value");
        CV2.setForeground(Color.lightGray);
        CV2.setHorizontalAlignment(JLabel.CENTER);
        CV2.setVerticalAlignment(JLabel.CENTER);
        CV2.setFont(new Font("Calibri", Font.BOLD, 20));
        panel.add(CV2);

        JLabel qty2 = new JLabel("Quantity");
        qty2.setForeground(Color.lightGray);
//        qty.setHorizontalAlignment(JLabel.CENTER);
        qty2.setVerticalAlignment(JLabel.CENTER);
        qty2.setFont(new Font("Calibri", Font.BOLD, 20));
        panel.add(qty2);
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));

        return panel;
    }




    private JPanel createThirdPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 6));
        panel.setBackground(Color.darkGray);


        //Create textField Panel
        JPanel textPanel = new JPanel(new GridLayout(0, 3));
        //JLabel spacer = new JLabel("");
        //textPanel.add(spacer);
        textPanel.setBackground(Color.darkGray);

        /*textPanel.add(new JLabel(""));
        textPanel.add(new JLabel(""));*/
        JLabel v100 = new JLabel("$100");
        v100.setForeground(Color.lightGray);
        v100.setHorizontalAlignment(JLabel.RIGHT);
        v100.setVerticalAlignment(JLabel.CENTER);
        v100.setFont(new Font("Calibri", Font.BOLD, 15));


        int quant = DenomDao.getDenomQuantity("100.0");
        JLabel v100quant = new JLabel( String.valueOf(quant) + " units");
        if (quant > 1) {
            v100quant.setForeground(Color.white);
        }
        else {
            v100quant.setForeground(Color.red);
        }

        v100quant.setHorizontalAlignment(JLabel.RIGHT);
        v100quant.setVerticalAlignment(JLabel.CENTER);
        v100quant.setFont(new Font("Calibri", Font.BOLD, 15));



        JPanel buttonPanel = new JPanel(new GridLayout(2, 4));
        buttonPanel.setBackground(Color.darkGray);


        JButton buttonUp = new JButton("^");
        buttonUp.setSize(1, 1);
        buttonUp.setBackground(Color.darkGray);
        buttonUp.setForeground(Color.lightGray);

        buttonUp.setFocusable(false);
        buttonUp.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    DenomDao.setDenomQuantity("100.0", String.valueOf(DenomDao.getDenomQuantity("100.0")+1));

                    v100quant.setText(String.format(String.valueOf(DenomDao.getDenomQuantity("100.0")) + " units"));
                }
            }
        );

        JButton buttonDown = new JButton("v");
        buttonDown.setBackground(Color.darkGray);
        buttonDown.setForeground(Color.lightGray);
        buttonDown.setFocusable(false);
        buttonDown.addActionListener(e -> {
            if (DenomDao.getDenomQuantity("100.0")-1>-1) {
                DenomDao.setDenomQuantity("100.0", String.valueOf(DenomDao.getDenomQuantity("100.0") - 1));

                v100quant.setText(String.format(String.valueOf(DenomDao.getDenomQuantity("100.0")) + " units"));
            }
        }
        );
        textPanel.add(v100);
        textPanel.add(v100quant);
        buttonPanel.add(new JLabel(" "));


        buttonPanel.add(buttonUp);
        buttonPanel.add(new JLabel(""));
        buttonPanel.add(new JLabel(""));
        buttonPanel.add(buttonDown);
        buttonPanel.add(new JLabel(""));
        textPanel.add(buttonPanel);
        panel.add(textPanel);

        JPanel textPanel1 = new JPanel(new GridLayout(0, 3));
        textPanel1.setBackground(Color.darkGray);


        JLabel v0100 = new JLabel("$1.0");
        v0100.setForeground(Color.lightGray);
        v0100.setHorizontalAlignment(JLabel.RIGHT);
        v0100.setVerticalAlignment(JLabel.CENTER);
        v0100.setFont(new Font("Calibri", Font.BOLD, 15));
        textPanel1.add(v0100);

        int oneQuant = DenomDao.getDenomQuantity("1.0");
        JLabel v1quant = new JLabel(String.valueOf(oneQuant) + " units");
        if (oneQuant > 1) {
            v1quant.setForeground(Color.white);
        }
        else {
            v1quant.setForeground(Color.red);
        }
        v1quant.setHorizontalAlignment(JLabel.RIGHT);
        v1quant.setVerticalAlignment(JLabel.CENTER);
        v1quant.setFont(new Font("Calibri", Font.BOLD, 15));
        textPanel1.add(v1quant);
        JPanel buttonPanel1 = new JPanel(new GridLayout(2, 4));
        buttonPanel1.setBackground(Color.darkGray);
        buttonPanel1.add(new JLabel(" "));

        JButton buttonUp1 = new JButton("^");

        buttonUp1.setBackground(Color.darkGray);
        buttonUp1.setForeground(Color.lightGray);

        buttonUp1.setFocusable(false);
        buttonUp1.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               DenomDao.setDenomQuantity("1.0", String.valueOf(DenomDao.getDenomQuantity("1.0")+1));

               v1quant.setText(String.format(String.valueOf(DenomDao.getDenomQuantity("1.0")) + " units"));
           }
       }
        );

        JButton buttonDown1 = new JButton("v");
        buttonDown1.setBackground(Color.darkGray);
        buttonDown1.setForeground(Color.lightGray);
        buttonDown1.setFocusable(false);
        buttonDown1.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               if (DenomDao.getDenomQuantity("1.0")-1>-1) {
                   DenomDao.setDenomQuantity("1.0", String.valueOf(DenomDao.getDenomQuantity("1.0") - 1));

                   v1quant.setText(String.format(String.valueOf(DenomDao.getDenomQuantity("1.0")) + " units"));
               }
           }
       }
        );

        buttonPanel1.add(buttonUp1);
        buttonPanel1.add(new JLabel(""));
        buttonPanel1.add(new JLabel(""));
        buttonPanel1.add(buttonDown1);
        buttonPanel1.add(new JLabel(""));
        //buttonPanel.setHorizontalAlignment(JPanel.RIGHT);
        textPanel1.add(buttonPanel1);


        panel.add(textPanel1);


        JButton reportButton = new JButton("Generate Transaction Summary");
        reportButton.setBackground(Color.darkGray);
        reportButton.setForeground(Color.lightGray);
        reportButton.setFocusable(false);
        reportButton.setHorizontalAlignment(JLabel.TRAILING);
        reportButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Report Generated Successfully","Success", JOptionPane.INFORMATION_MESSAGE);
            DenomDao.generateTransactionsSummary(null);
        });


        panel.add(reportButton);

        return panel;
    }


    private JPanel createFourthPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 6));
        panel.setBackground(Color.darkGray);


        //Create textField Panel
        JPanel textPanel = new JPanel(new GridLayout(0, 3));
        textPanel.setBackground(Color.darkGray);


        JLabel v50 = new JLabel("$50");
        v50.setForeground(Color.lightGray);
        v50.setHorizontalAlignment(JLabel.RIGHT);
        v50.setVerticalAlignment(JLabel.CENTER);
        v50.setFont(new Font("Calibri", Font.BOLD, 15));
        textPanel.add(v50);

        int fiftyQuant = DenomDao.getDenomQuantity("50.0");

        JLabel v50quant = new JLabel(String.valueOf(fiftyQuant) + " units");

        if (fiftyQuant > 1) {
            v50quant.setForeground(Color.white);
        }
        else {
            v50quant.setForeground(Color.red);
        }
        v50quant.setHorizontalAlignment(JLabel.RIGHT);
        v50quant.setVerticalAlignment(JLabel.CENTER);
        v50quant.setFont(new Font("Calibri", Font.BOLD, 15));
        textPanel.add(v50quant);
        JPanel buttonPanel = new JPanel(new GridLayout(2, 4));
        buttonPanel.setBackground(Color.darkGray);
        buttonPanel.add(new JLabel(" "));

        JButton buttonUp = new JButton("^");
        buttonUp.setSize(1, 1);
        buttonUp.setBackground(Color.darkGray);
        buttonUp.setForeground(Color.lightGray);

        buttonUp.setFocusable(false);
        buttonUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DenomDao.setDenomQuantity("50.0", String.valueOf(DenomDao.getDenomQuantity("50.0")+1));

                v50quant.setText(String.format(String.valueOf(DenomDao.getDenomQuantity("50.0")) + " units"));
                }
            }
        );

        JButton buttonDown = new JButton("v");
        buttonDown.setBackground(Color.darkGray);
        buttonDown.setForeground(Color.lightGray);
        buttonDown.setFocusable(false);
        buttonDown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (DenomDao.getDenomQuantity("1.0")-1>-1) {
                    DenomDao.setDenomQuantity("50.0", String.valueOf(DenomDao.getDenomQuantity("50.0") - 1));
                    v50quant.setText(String.format(String.valueOf(DenomDao.getDenomQuantity("50.0")) + " units"));
                }
            }
            }
        );


        buttonPanel.add(buttonUp);
        buttonPanel.add(new JLabel(""));
        buttonPanel.add(new JLabel(""));
        buttonPanel.add(buttonDown);
        buttonPanel.add(new JLabel(""));
        //buttonPanel.setHorizontalAlignment(JPanel.RIGHT);
        textPanel.add(buttonPanel);
        panel.add(textPanel);

        JPanel textPanel1 = new JPanel(new GridLayout(0, 3));
        textPanel1.setBackground(Color.darkGray);


        JLabel v0500 = new JLabel("$0.50");
        v0500.setForeground(Color.lightGray);
        v0500.setHorizontalAlignment(JLabel.RIGHT);
        v0500.setVerticalAlignment(JLabel.CENTER);
        v0500.setFont(new Font("Calibri", Font.BOLD, 15));
        textPanel1.add(v0500);


        int fiftyCQuant = DenomDao.getDenomQuantity("0.5");
        JLabel v050quant = new JLabel(String.valueOf(fiftyCQuant) + " units");
        if (fiftyCQuant > 1) {
            v050quant.setForeground(Color.white);
        }
        else {
            v050quant.setForeground(Color.red);
        }
        v050quant.setHorizontalAlignment(JLabel.RIGHT);
        v050quant.setVerticalAlignment(JLabel.CENTER);
        v050quant.setFont(new Font("Calibri", Font.BOLD, 15));
        textPanel1.add(v050quant);
        JPanel buttonPanel1 = new JPanel(new GridLayout(2, 4));
        buttonPanel1.setBackground(Color.darkGray);
        buttonPanel1.add(new JLabel(" "));

        JButton buttonUp1 = new JButton("^");

        buttonUp1.setBackground(Color.darkGray);
        buttonUp1.setForeground(Color.lightGray);

        buttonUp1.setFocusable(false);
        buttonUp1.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                     DenomDao.setDenomQuantity("0.5", String.valueOf(DenomDao.getDenomQuantity("0.5") + 1));
                     v050quant.setText(String.format(String.valueOf(DenomDao.getDenomQuantity("0.5")) + " units"));
                 }
             }

        );

        JButton buttonDown1 = new JButton("v");
        buttonDown1.setBackground(Color.darkGray);
        buttonDown1.setForeground(Color.lightGray);
        buttonDown1.setFocusable(false);
        buttonDown1.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (DenomDao.getDenomQuantity("0.5")-1> -1) {
                DenomDao.setDenomQuantity("0.5", String.valueOf(DenomDao.getDenomQuantity("0.5") - 1));
                v050quant.setText(String.format(String.valueOf(DenomDao.getDenomQuantity("0.5")) + " units"));
            }
         }
            }
        );

        buttonPanel1.add(buttonUp1);
        buttonPanel1.add(new JLabel(""));
        buttonPanel1.add(new JLabel(""));
        buttonPanel1.add(buttonDown1);
        buttonPanel1.add(new JLabel(""));
        //buttonPanel.setHorizontalAlignment(JPanel.RIGHT);
        textPanel1.add(buttonPanel1);

        panel.add(textPanel1);

        panel.add(new JLabel(""));


        return panel;
    }

    private JPanel createFifthPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 6));
        panel.setBackground(Color.darkGray);


        //Create textField Panel
        JPanel textPanel = new JPanel(new GridLayout(0, 3));
        textPanel.setBackground(Color.darkGray);


        JLabel v20 = new JLabel("$20");
        v20.setForeground(Color.lightGray);
        v20.setHorizontalAlignment(JLabel.RIGHT);
        v20.setVerticalAlignment(JLabel.CENTER);
        v20.setFont(new Font("Calibri", Font.BOLD, 15));
        textPanel.add(v20);

        int twentyQuant = DenomDao.getDenomQuantity("20.0");
        JLabel v20quant = new JLabel(String.valueOf(twentyQuant) + " units");
        if (twentyQuant > 1) {
            v20quant.setForeground(Color.white);
        }
        else {
            v20quant.setForeground(Color.red);
        }
        v20quant.setHorizontalAlignment(JLabel.RIGHT);
        v20quant.setVerticalAlignment(JLabel.CENTER);
        v20quant.setFont(new Font("Calibri", Font.BOLD, 15));
        textPanel.add(v20quant);
        JPanel buttonPanel = new JPanel(new GridLayout(2, 4));
        buttonPanel.setBackground(Color.darkGray);
        buttonPanel.add(new JLabel(" "));

        JButton buttonUp = new JButton("^");
        buttonUp.setSize(1, 1);
        buttonUp.setBackground(Color.darkGray);
        buttonUp.setForeground(Color.lightGray);

        buttonUp.setFocusable(false);
        buttonUp.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                  DenomDao.setDenomQuantity("20.0", String.valueOf(DenomDao.getDenomQuantity("20.0") + 1));
                  v20quant.setText(String.format(String.valueOf(DenomDao.getDenomQuantity("20.0")) + " units"));

              }
          }
        );

        JButton buttonDown = new JButton("v");
        buttonDown.setBackground(Color.darkGray);
        buttonDown.setForeground(Color.lightGray);
        buttonDown.setFocusable(false);
        buttonDown.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                  if (DenomDao.getDenomQuantity("20.0")-1> -1) {
                      DenomDao.setDenomQuantity("20.0", String.valueOf(DenomDao.getDenomQuantity("20.0") - 1));
                      v20quant.setText(String.format(String.valueOf(DenomDao.getDenomQuantity("20.0")) + " units"));
                  }
              }
          }
        );

        buttonPanel.add(buttonUp);
        buttonPanel.add(new JLabel(""));
        buttonPanel.add(new JLabel(""));
        buttonPanel.add(buttonDown);
        buttonPanel.add(new JLabel(""));
        //buttonPanel.setHorizontalAlignment(JPanel.RIGHT);
        textPanel.add(buttonPanel);

        panel.add(textPanel);

        JPanel textPanel1 = new JPanel(new GridLayout(0, 3));
        textPanel1.setBackground(Color.darkGray);


        JLabel v0020 = new JLabel("$0.20");
        v0020.setForeground(Color.lightGray);
        v0020.setHorizontalAlignment(JLabel.RIGHT);
        v0020.setVerticalAlignment(JLabel.CENTER);
        v0020.setFont(new Font("Calibri", Font.BOLD, 15));
        textPanel1.add(v0020);

        int twentyCQuant = DenomDao.getDenomQuantity("0.20");
        JLabel v020quant = new JLabel(String.valueOf(twentyCQuant) + " units");
        if (twentyCQuant > 1) {
            v020quant.setForeground(Color.white);
        }
        else {
            v020quant.setForeground(Color.red);
        }
        v020quant.setHorizontalAlignment(JLabel.RIGHT);
        v020quant.setVerticalAlignment(JLabel.CENTER);
        v020quant.setFont(new Font("Calibri", Font.BOLD, 15));
        textPanel1.add(v020quant);

        JPanel buttonPanel1 = new JPanel(new GridLayout(2, 4));
        buttonPanel1.setBackground(Color.darkGray);
        buttonPanel1.add(new JLabel(" "));

        JButton buttonUp1 = new JButton("^");

        buttonUp1.setBackground(Color.darkGray);
        buttonUp1.setForeground(Color.lightGray);

        buttonUp1.setFocusable(false);
        buttonUp1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    DenomDao.setDenomQuantity("0.20", String.valueOf(DenomDao.getDenomQuantity("0.20") + 1));
                    v020quant.setText(String.format(String.valueOf(DenomDao.getDenomQuantity("0.20")) + " units"));

                }
            }
        );

        JButton buttonDown1 = new JButton("v");
        buttonDown1.setBackground(Color.darkGray);
        buttonDown1.setForeground(Color.lightGray);
        buttonDown1.setFocusable(false);
        buttonDown1.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 if (DenomDao.getDenomQuantity("0.20")-1> -1) {
                     DenomDao.setDenomQuantity("0.20", String.valueOf(DenomDao.getDenomQuantity("0.20") - 1));
                     v020quant.setText(String.format(String.valueOf(DenomDao.getDenomQuantity("0.20")) + " units"));
                 }
             }
         }
        );

        buttonPanel1.add(buttonUp1);
        buttonPanel1.add(new JLabel(""));
        buttonPanel1.add(new JLabel(""));
        buttonPanel1.add(buttonDown1);
        buttonPanel1.add(new JLabel(""));
        //buttonPanel.setHorizontalAlignment(JPanel.RIGHT);
        textPanel1.add(buttonPanel1);

        panel.add(textPanel1);

        JButton reportButton = new JButton("Generate Available Change Report");
        reportButton.setBackground(Color.darkGray);
        reportButton.setForeground(Color.lightGray);
        reportButton.setFocusable(false);
        reportButton.setHorizontalAlignment(JLabel.TRAILING);
        reportButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Report Generated Successfully","Success", JOptionPane.INFORMATION_MESSAGE);
            DenomDao.generateCashSummary(null);
        });
        panel.add(reportButton);
        return panel;
    }
    private JPanel createSixthPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 6));
        panel.setBackground(Color.darkGray);


        //Create textField Panel
        JPanel textPanel = new JPanel(new GridLayout(0, 3));
        textPanel.setBackground(Color.darkGray);


        JLabel v10 = new JLabel("$10");
        v10.setForeground(Color.lightGray);
        v10.setHorizontalAlignment(JLabel.RIGHT);
        v10.setVerticalAlignment(JLabel.CENTER);
        v10.setFont(new Font("Calibri", Font.BOLD, 15));
        textPanel.add(v10);

        int tenQuant = DenomDao.getDenomQuantity("10");
        JLabel v10quant = new JLabel(String.valueOf(tenQuant) + " units");
        if (tenQuant > 1) {
            v10quant.setForeground(Color.white);
        }
        else {
            v10quant.setForeground(Color.red);
        }
        v10quant.setHorizontalAlignment(JLabel.RIGHT);
        v10quant.setVerticalAlignment(JLabel.CENTER);
        v10quant.setFont(new Font("Calibri", Font.BOLD, 15));
        textPanel.add(v10quant);
        JPanel buttonPanel = new JPanel(new GridLayout(2, 4));
        buttonPanel.setBackground(Color.darkGray);
        buttonPanel.add(new JLabel(" "));

        JButton buttonUp = new JButton("^");
        buttonUp.setSize(1, 1);
        buttonUp.setBackground(Color.darkGray);
        buttonUp.setForeground(Color.lightGray);

        buttonUp.setFocusable(false);
        buttonUp.addActionListener(new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                      DenomDao.setDenomQuantity("10", String.valueOf(DenomDao.getDenomQuantity("10") + 1));
                      v10quant.setText(String.format(String.valueOf(DenomDao.getDenomQuantity("10")) + " units"));

                  }
              }
        );

        JButton buttonDown = new JButton("v");
        buttonDown.setBackground(Color.darkGray);
        buttonDown.setForeground(Color.lightGray);
        buttonDown.setFocusable(false);
        buttonDown.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                  if (DenomDao.getDenomQuantity("10")-1> -1) {
                      DenomDao.setDenomQuantity("10", String.valueOf(DenomDao.getDenomQuantity("10") - 1));
                      v10quant.setText(String.format(String.valueOf(DenomDao.getDenomQuantity("10")) + " units"));
                  }
              }
          }
        );

        buttonPanel.add(buttonUp);
        buttonPanel.add(new JLabel(""));
        buttonPanel.add(new JLabel(""));
        buttonPanel.add(buttonDown);
        buttonPanel.add(new JLabel(""));
        //buttonPanel.setHorizontalAlignment(JPanel.RIGHT);
        textPanel.add(buttonPanel);

        panel.add(textPanel);

        JPanel textPanel1 = new JPanel(new GridLayout(0, 3));
        textPanel1.setBackground(Color.darkGray);


        JLabel v0010 = new JLabel("$0.10");
        v0010.setForeground(Color.lightGray);
        v0010.setHorizontalAlignment(JLabel.RIGHT);
        v0010.setVerticalAlignment(JLabel.CENTER);
        v0010.setFont(new Font("Calibri", Font.BOLD, 15));
        textPanel1.add(v0010);

        int tenCQuant = DenomDao.getDenomQuantity("0.10");
        JLabel v010quant = new JLabel(String.valueOf(tenCQuant) + " units");
        if (tenCQuant > 1) {
            v010quant.setForeground(Color.white);
        }
        else {
            v010quant.setForeground(Color.red);
        }
        v010quant.setHorizontalAlignment(JLabel.RIGHT);
        v010quant.setVerticalAlignment(JLabel.CENTER);
        v010quant.setFont(new Font("Calibri", Font.BOLD, 15));
        textPanel1.add(v010quant);
        JPanel buttonPanel1 = new JPanel(new GridLayout(2, 4));
        buttonPanel1.setBackground(Color.darkGray);
        buttonPanel1.add(new JLabel(" "));

        JButton buttonUp1 = new JButton("^");

        buttonUp1.setBackground(Color.darkGray);
        buttonUp1.setForeground(Color.lightGray);

        buttonUp1.setFocusable(false);
        buttonUp1.addActionListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     DenomDao.setDenomQuantity("0.10", String.valueOf(DenomDao.getDenomQuantity("0.10") + 1));
                     v010quant.setText(String.format(String.valueOf(DenomDao.getDenomQuantity("0.10")) + " units"));

                 }
             }
        );

        JButton buttonDown1 = new JButton("v");
        buttonDown1.setBackground(Color.darkGray);
        buttonDown1.setForeground(Color.lightGray);
        buttonDown1.setFocusable(false);
        buttonDown1.addActionListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     if (DenomDao.getDenomQuantity("0.10")-1> -1) {
                         DenomDao.setDenomQuantity("0.10", String.valueOf(DenomDao.getDenomQuantity("0.10") - 1));
                         v010quant.setText(String.format(String.valueOf(DenomDao.getDenomQuantity("0.10")) + " units"));
                     }
                 }
             }
        );

        buttonPanel1.add(buttonUp1);
        buttonPanel1.add(new JLabel(""));
        buttonPanel1.add(new JLabel(""));
        buttonPanel1.add(buttonDown1);
        buttonPanel1.add(new JLabel(""));
        textPanel1.add(buttonPanel1);
        panel.add(textPanel1);

        panel.add(new JLabel(""));

        return panel;
    }

    private JPanel createSeventhPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 6));
        panel.setBackground(Color.darkGray);


        //Create textField Panel
        JPanel textPanel = new JPanel(new GridLayout(0, 3));
        textPanel.setBackground(Color.darkGray);

        JLabel v05 = new JLabel("$5");
        v05.setForeground(Color.lightGray);
        v05.setHorizontalAlignment(JLabel.RIGHT);
        v05.setVerticalAlignment(JLabel.CENTER);
        v05.setFont(new Font("Calibri", Font.BOLD, 15));
        textPanel.add(v05);

        int fiveQuant = DenomDao.getDenomQuantity("5.0");
        JLabel v5quant = new JLabel(String.valueOf(fiveQuant) + " units");
        if (fiveQuant > 1) {
            v5quant.setForeground(Color.white);
        }
        else {
            v5quant.setForeground(Color.red);
        }
        v5quant.setHorizontalAlignment(JLabel.RIGHT);
        v5quant.setVerticalAlignment(JLabel.CENTER);
        v5quant.setFont(new Font("Calibri", Font.BOLD, 15));
        textPanel.add(v5quant);
        JPanel buttonPanel = new JPanel(new GridLayout(2, 4));
        buttonPanel.setBackground(Color.darkGray);
        buttonPanel.add(new JLabel(" "));

        JButton buttonUp = new JButton("^");
        buttonUp.setSize(1, 1);
        buttonUp.setBackground(Color.darkGray);
        buttonUp.setForeground(Color.lightGray);

        buttonUp.setFocusable(false);
        buttonUp.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {

              DenomDao.setDenomQuantity("5.0", String.valueOf(DenomDao.getDenomQuantity("5.0") + 1));
              v5quant.setText(String.format(String.valueOf(DenomDao.getDenomQuantity("5.0")) + " units"));
                      }
                  }

        );

        JButton buttonDown = new JButton("v");
        buttonDown.setBackground(Color.darkGray);
        buttonDown.setForeground(Color.lightGray);
        buttonDown.setFocusable(false);
        buttonDown.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              if (DenomDao.getDenomQuantity("5.0")-1> -1) {
                  DenomDao.setDenomQuantity("5.0", String.valueOf(DenomDao.getDenomQuantity("5.0") - 1));
                  v5quant.setText(String.format(String.valueOf(DenomDao.getDenomQuantity("5.0")) + " units"));
              }
          }
      }
        );

        buttonPanel.add(buttonUp);
        buttonPanel.add(new JLabel(""));
        buttonPanel.add(new JLabel(""));
        buttonPanel.add(buttonDown);
        buttonPanel.add(new JLabel(""));
        //buttonPanel.setHorizontalAlignment(JPanel.RIGHT);
        textPanel.add(buttonPanel);

        panel.add(textPanel);

        JPanel textPanel1 = new JPanel(new GridLayout(0, 3));
        textPanel1.setBackground(Color.darkGray);


        JLabel v0005 = new JLabel("$0.05");
        v0005.setForeground(Color.lightGray);
        v0005.setHorizontalAlignment(JLabel.RIGHT);
        v0005.setVerticalAlignment(JLabel.CENTER);
        v0005.setFont(new Font("Calibri", Font.BOLD, 15));
        textPanel1.add(v0005);

        int fiveCQuant = DenomDao.getDenomQuantity("0.05");
        JLabel v05quant = new JLabel(String.valueOf(fiveCQuant) + " units");
        if (fiveCQuant > 1) {
            v05quant.setForeground(Color.white);
        }
        else {
            v05quant.setForeground(Color.red);
        }
        v05quant.setHorizontalAlignment(JLabel.RIGHT);
        v05quant.setVerticalAlignment(JLabel.CENTER);
        v05quant.setFont(new Font("Calibri", Font.BOLD, 15));
        textPanel1.add(v05quant);
        JPanel buttonPanel1 = new JPanel(new GridLayout(2, 4));
        buttonPanel1.setBackground(Color.darkGray);
        buttonPanel1.add(new JLabel(" "));

        JButton buttonUp1 = new JButton("^");

        buttonUp1.setBackground(Color.darkGray);
        buttonUp1.setForeground(Color.lightGray);

        buttonUp1.setFocusable(false);
        buttonUp1.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
                DenomDao.setDenomQuantity("0.05", String.valueOf(DenomDao.getDenomQuantity("0.05") + 1));
                v05quant.setText(String.format(String.valueOf(DenomDao.getDenomQuantity("0.05")) + " units"));
            }
        }

        );

        JButton buttonDown1 = new JButton("v");
        buttonDown1.setBackground(Color.darkGray);
        buttonDown1.setForeground(Color.lightGray);
        buttonDown1.setFocusable(false);
        buttonDown1.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (DenomDao.getDenomQuantity("0.05")-1> -1) {
                DenomDao.setDenomQuantity("0.05", String.valueOf(DenomDao.getDenomQuantity("0.05") - 1));
                v05quant.setText(String.format(String.valueOf(DenomDao.getDenomQuantity("0.05")) + " units"));
            }
        }
        }
        );

        buttonPanel1.add(buttonUp1);
        buttonPanel1.add(new JLabel(""));
        buttonPanel1.add(new JLabel(""));
        buttonPanel1.add(buttonDown1);
        buttonPanel1.add(new JLabel(""));
        //buttonPanel.setHorizontalAlignment(JPanel.RIGHT);
        textPanel1.add(buttonPanel1);

        panel.add(textPanel1);

        panel.add(new JLabel(""));

        return panel;    }

    private JPanel createEighthPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 6));
        panel.setBackground(Color.darkGray);


        //Create textField Panel
        JPanel textPanel = new JPanel(new GridLayout(0, 3));
        textPanel.setBackground(Color.darkGray);


        JLabel v02 = new JLabel("$2");
        v02.setForeground(Color.lightGray);
        v02.setHorizontalAlignment(JLabel.RIGHT);
        v02.setVerticalAlignment(JLabel.CENTER);
        v02.setFont(new Font("Calibri", Font.BOLD, 15));
        textPanel.add(v02);
        int twoQuant = DenomDao.getDenomQuantity("2.0");
        JLabel v2quant = new JLabel(String.valueOf(twoQuant) + " units");
        if (twoQuant > 1) {
            v2quant.setForeground(Color.white);
        }
        else {
            v2quant.setForeground(Color.red);
        }
        v2quant.setHorizontalAlignment(JLabel.RIGHT);
        v2quant.setVerticalAlignment(JLabel.CENTER);
        v2quant.setFont(new Font("Calibri", Font.BOLD, 15));
        textPanel.add(v2quant);
        JPanel buttonPanel = new JPanel(new GridLayout(2, 4));
        buttonPanel.setBackground(Color.darkGray);
        buttonPanel.add(new JLabel(" "));

        JButton buttonUp = new JButton("^");
        buttonUp.setSize(1, 1);
        buttonUp.setBackground(Color.darkGray);
        buttonUp.setForeground(Color.lightGray);

        buttonUp.setFocusable(false);
        buttonUp.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                     DenomDao.setDenomQuantity("2.0", String.valueOf(DenomDao.getDenomQuantity("2.0") + 1));
                     v2quant.setText(String.format(String.valueOf(DenomDao.getDenomQuantity("2.0")) + " units"));

             }
         }
        );

        JButton buttonDown = new JButton("v");
        buttonDown.setBackground(Color.darkGray);
        buttonDown.setForeground(Color.lightGray);
        buttonDown.setFocusable(false);
        buttonDown.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 if (DenomDao.getDenomQuantity("2.0")-1> -1) {
                     DenomDao.setDenomQuantity("2.0", String.valueOf(DenomDao.getDenomQuantity("2.0") - 1));
                     v2quant.setText(String.format(String.valueOf(DenomDao.getDenomQuantity("2.0")) + " units"));
                 }
             }
         }
        );
        buttonPanel.add(buttonUp);
        buttonPanel.add(new JLabel(""));
        buttonPanel.add(new JLabel(""));
        buttonPanel.add(buttonDown);
        buttonPanel.add(new JLabel(""));
        //buttonPanel.setHorizontalAlignment(JPanel.RIGHT);
        textPanel.add(buttonPanel);


        panel.add(textPanel);

        panel.add(new JLabel(""));
        panel.add(new JLabel(""));

        return panel;
    }

    private JPanel createNinthPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 3));
        panel.setBackground(Color.darkGray);


        panel.add(new JLabel(""));
        panel.add(new JLabel(""));


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
