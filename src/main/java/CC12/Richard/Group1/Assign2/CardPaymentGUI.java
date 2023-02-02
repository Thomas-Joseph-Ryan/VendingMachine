package CC12.Richard.Group1.Assign2;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CardPaymentGUI extends PurchaseGUI {

    private JButton pay;
    private JLabel amountBeingPaid;
    private JPanel saveCardRow;

    private JPanel updatedSaveCardRow;
    private String cardNumberSaved;
    private String cardName;
    private String savedCardString = "";
    private String savedNumberString = "";
    private JPanel cardButtonPanel;
    private JTextField textFieldNumber;
    private JTextField textField;
    private JLabel astericksCard;
    private JLabel savedCardHolder;
    private Boolean pressChangeCard = false;



    public CardPaymentGUI(Controller controller) {
        super(controller);
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(800, 500));
        getContentPane().setBackground(Color.darkGray);

        add(createFirstPanel(), BorderLayout.PAGE_START);
        add(createSecondPanel(), BorderLayout.CENTER);

        add(createThirdPanel(), BorderLayout.PAGE_END);


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
                backFunction();
            }
        });
        panel.add(back);

        //Create title
        JLabel title = new JLabel("Payment");
        title.setForeground(Color.lightGray);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setFont(new Font("Calibri", Font.BOLD, 25));
        panel.add(title);

        //Create Icon
        JLabel image = new JLabel();
        image.setHorizontalAlignment(JLabel.TRAILING);
        image.setIcon(new ImageIcon("src/main/resources/LiteSnacks.png"));
        panel.add(image);

        return panel;
    }


    private JLabel defaultTextSetter(JLabel label) {
        label.setForeground(Color.lightGray);
        return label;
    }

    private JPanel createSecondPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 2, 30, 0));
        panel.setBackground(Color.darkGray);

        //LEFT RECEIPT PANEL

        int receiptRowCount = 1;
        for (Item item : controller.getItemsList()) {
            if (item.getQtyInBag() > 0) {
                receiptRowCount++;
            }
        }

//        int receiptRowCount = 1;
//        for (Item item : controller.getItemsList()) {
//            if (item.getQtyInBag() > 0) {
//                receiptRowCount ++;
//            }
//        }


        JPanel receiptPanel = new JPanel();
        JScrollPane scrollPane = new JScrollPane(receiptPanel);
        receiptPanel.setLayout(new BoxLayout(receiptPanel, BoxLayout.Y_AXIS));
        receiptPanel.setBackground(Color.darkGray);

        JPanel receiptLabelRow = new JPanel(new GridLayout(1, 3, 5, 0));
        receiptLabelRow.setBackground(Color.darkGray);


        JLabel temp;

        temp = new JLabel("Item");
        defaultTextSetter(temp);
        temp.setVerticalAlignment(JLabel.TOP);
        temp.setHorizontalAlignment(JLabel.CENTER);
        receiptLabelRow.add(temp);

        temp = new JLabel("QTY");
        defaultTextSetter(temp);
        temp.setVerticalAlignment(JLabel.TOP);
        temp.setHorizontalAlignment(JLabel.CENTER);
        receiptLabelRow.add(temp);

        temp = new JLabel("Total");
        defaultTextSetter(temp);
        temp.setVerticalAlignment(JLabel.TOP);
        temp.setHorizontalAlignment(JLabel.CENTER);
        receiptLabelRow.add(temp);


        receiptPanel.add(receiptLabelRow);

        for (Item item : controller.getItemsList()) {
            if (item.getQtyInBag() > 0) {
                JPanel itemRow = new JPanel(new GridLayout(1, 3, 5, 0));
                itemRow.setBackground(Color.darkGray);

                temp = new JLabel(item.getName());
                defaultTextSetter(temp);
                temp.setVerticalAlignment(JLabel.TOP);
                temp.setHorizontalAlignment(JLabel.CENTER);
                itemRow.add(temp);

                temp = new JLabel(String.valueOf(item.getQtyInBag()));
                defaultTextSetter(temp);
                temp.setVerticalAlignment(JLabel.TOP);
                temp.setHorizontalAlignment(JLabel.CENTER);
                itemRow.add(temp);

                temp = new JLabel(String.valueOf(item.getQtyInBag() * item.getPrice()));
                defaultTextSetter(temp);
                temp.setVerticalAlignment(JLabel.TOP);
                temp.setHorizontalAlignment(JLabel.CENTER);
                itemRow.add(temp);

                receiptPanel.add(itemRow);
            }
        }
        receiptPanel.add(Box.createVerticalGlue());

        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setBackground(Color.darkGray);
//        scrollPane.getHorizontalScrollBar().setForeground(Color.darkGray);

        scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                thumbColor = Color.gray;
            }
        });
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.lightGray));

        panel.add(scrollPane);

        //RIGHT CARD PAYMENT PANEL
        cardButtonPanel = new JPanel(new GridLayout(6, 1));
        cardButtonPanel.setBackground(Color.darkGray);


        //Create cardHolder label
        JPanel cardHolderRow = new JPanel(new GridLayout(1, 2, 0, 0));
        cardHolderRow.setBackground(Color.darkGray);
        JLabel cardHolder = new JLabel("Card Holder:   ");
        cardHolder.setForeground(Color.lightGray);
        cardHolder.setHorizontalAlignment(JLabel.CENTER);
        cardHolder.setFont(new Font("Calibri", Font.BOLD, 20));
        cardHolderRow.add(cardHolder);

        //Create textField Panel
        JPanel textFieldPanel = new JPanel(new GridLayout(3, 1));
        textFieldPanel.setBackground(Color.darkGray);

        if (controller.getUser().getCardName().equals("")) {
            textField = new JTextField(controller.getUser().getCardName());
            textFieldPanel.add(new JLabel(""));
            textFieldPanel.add(textField);
            textFieldPanel.add(new JLabel(""));
            cardHolderRow.add(textFieldPanel);

        } else {
            savedCardHolder = new JLabel(controller.getUser().getCardName());
            savedCardString = controller.getUser().getCardName();
            savedCardHolder.setForeground(Color.lightGray);
            savedCardHolder.setHorizontalAlignment(JLabel.CENTER);
            savedCardHolder.setFont(new Font("Calibri", Font.BOLD, 15));
            textFieldPanel.add(new JLabel(""));
            textFieldPanel.add(savedCardHolder);
            textFieldPanel.add(new JLabel(""));
            cardHolderRow.add(textFieldPanel);
        }


        cardButtonPanel.add(cardHolderRow);


        //Create cardNumber label
        JPanel cardNumberRow = new JPanel(new GridLayout(1, 2, 0, 0));
        cardNumberRow.setBackground(Color.darkGray);
        JLabel cardNumber = new JLabel("Card Number:   ");
        cardNumber.setForeground(Color.lightGray);
        cardNumber.setHorizontalAlignment(JLabel.CENTER);
        cardNumber.setFont(new Font("Calibri", Font.BOLD, 20));
        cardNumberRow.add(cardNumber);

        //Create textFieldNumber Panel
        JPanel textFieldNumberPanel = new JPanel(new GridLayout(3, 1));
        textFieldNumberPanel.setBackground(Color.darkGray);
        if (controller.getUser().getCardNumber().equals("")) {
            textFieldNumber = new JTextField(controller.getUser().getCardNumber());
            textFieldNumberPanel.add(new JLabel(""));
            textFieldNumberPanel.add(textFieldNumber);
            textFieldNumberPanel.add(new JLabel(""));
            cardNumberRow.add(textFieldNumberPanel);
            cardButtonPanel.add(cardNumberRow);


            //Create save card question
            saveCardRow = new JPanel(new GridLayout(2, 1, 0, 0));
            saveCardRow.setBackground(Color.darkGray);
            saveCardRow.setVisible(false);

            JLabel saveCardQuestion = new JLabel("Save user card details");
            saveCardQuestion.setForeground(Color.lightGray);
            saveCardQuestion.setHorizontalAlignment(JLabel.CENTER);
            saveCardQuestion.setFont(new Font("Calibri", Font.BOLD, 20));
            saveCardRow.add(saveCardQuestion);

            //Create yes button
            JPanel saveCardPanel = new JPanel(new GridLayout(1, 2, 40, 0));
            saveCardPanel.setBackground(Color.darkGray);

            JButton Yes = new JButton("Yes");
            Yes.setBackground(Color.darkGray);
            Yes.setForeground(Color.lightGray);
            Yes.setFocusable(false);

            Yes.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cardName = textField.getText();
                    cardNumberSaved = textFieldNumber.getText();
                    UserDao.saveCardDetails(controller.getUser().getName(), cardName, cardNumberSaved);
                    controller.getUser().setCardName(cardName);
                    controller.getUser().setCardNumber(cardNumberSaved);

                    JOptionPane.showMessageDialog(null, "Your card has been saved.", "Success", JOptionPane.WARNING_MESSAGE);

                    closeFrame();
                    purchaseItemsCard();
                }
            });


            //Create no button
            JButton No = new JButton("No");
            No.setBackground(Color.darkGray);
            No.setForeground(Color.lightGray);
            No.setFocusable(false);

            No.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    closeFrame();
                    purchaseItemsCard();
                }
            });


            saveCardPanel.add(Yes);
            saveCardPanel.add(No);
            saveCardRow.add(saveCardPanel);
            cardButtonPanel.add(saveCardRow);

            panel.add(cardButtonPanel);


        } else {
            astericksCard = new JLabel("*****");
            savedNumberString = controller.getUser().getCardNumber();
            astericksCard.setForeground(Color.lightGray);
            astericksCard.setHorizontalAlignment(JLabel.CENTER);
            astericksCard.setFont(new Font("Calibri", Font.BOLD, 15));
            textFieldNumberPanel.add(new JLabel(""));
            textFieldNumberPanel.add(astericksCard);
            textFieldNumberPanel.add(new JLabel(""));

            cardNumberRow.add(textFieldNumberPanel);

            // Create change saved card question
            JPanel changeSavedCardRow = new JPanel(new GridLayout(1, 1, 0, 0));
            changeSavedCardRow.setBackground(Color.darkGray);

//            JLabel changeSavedCardQuestion = new JLabel("Change card details");
//            changeSavedCardQuestion.setForeground(Color.lightGray);
//            changeSavedCardQuestion.setHorizontalAlignment(JLabel.CENTER);
//            changeSavedCardQuestion.setFont(new Font("Calibri", Font.BOLD, 20));
//            changeSavedCardRow.add(changeSavedCardQuestion);

            //Create change details button
            JPanel ChangeSavedCardPanel = new JPanel(new GridLayout(3, 1, 40, 0));
            ChangeSavedCardPanel.setBackground(Color.darkGray);

            JButton yesChange = new JButton("Change card details");
            yesChange.setBackground(Color.darkGray);
            yesChange.setForeground(Color.lightGray);
            yesChange.setFocusable(false);

            yesChange.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    pressChangeCard = true;
                    yesChange.setVisible(false);
                    JPanel updateCardHolderRow = new JPanel(new GridLayout(1, 2, 0, 0));
                    updateCardHolderRow.setBackground(Color.darkGray);
                    JLabel updateCardHolder = new JLabel("Card Holder:   ");
                    updateCardHolder.setForeground(Color.lightGray);
                    updateCardHolder.setHorizontalAlignment(JLabel.CENTER);
                    updateCardHolder.setFont(new Font("Calibri", Font.BOLD, 20));
                    updateCardHolderRow.add(updateCardHolder);
//
                    //Create updateTextField Panel
                    JPanel updateTextFieldPanel = new JPanel(new GridLayout(3, 1));
                    updateTextFieldPanel.setBackground(Color.darkGray);

                    textField = new JTextField("");
                    updateTextFieldPanel.add(new JLabel(""));
                    updateTextFieldPanel.add(textField);
                    updateTextFieldPanel.add(new JLabel(""));
                    updateCardHolderRow.add(updateTextFieldPanel);
//
                    //Create updateCardNumber label
                    JPanel updateCardNumberRow = new JPanel(new GridLayout(1, 1, 0, 0));
                    updateCardNumberRow.setBackground(Color.darkGray);
                    JLabel updateCardNumber = new JLabel("Card Number:   ");
                    updateCardNumber.setForeground(Color.lightGray);
                    updateCardNumber.setHorizontalAlignment(JLabel.CENTER);
                    updateCardNumber.setFont(new Font("Calibri", Font.BOLD, 20));
                    updateCardNumberRow.add(updateCardNumber);

                    //Create textFieldNumber Panel
                    JPanel updateTextFieldNumberPanel = new JPanel(new GridLayout(3, 1));
                    updateTextFieldNumberPanel.setBackground(Color.darkGray);

                    textFieldNumber = new JTextField("");
                    updateTextFieldNumberPanel.add(new JLabel(""));
                    updateTextFieldNumberPanel.add(textFieldNumber);
                    updateTextFieldNumberPanel.add(new JLabel(""));
                    updateCardNumberRow.add(updateTextFieldNumberPanel);

                    //Create updated save card question
                    updatedSaveCardRow = new JPanel(new GridLayout(2, 1, 0, 0));
                    updatedSaveCardRow.setBackground(Color.darkGray);
                    updatedSaveCardRow.setVisible(false);

                    JLabel saveCardQuestion = new JLabel("Save user card details");
                    saveCardQuestion.setForeground(Color.lightGray);
                    saveCardQuestion.setHorizontalAlignment(JLabel.CENTER);
                    saveCardQuestion.setFont(new Font("Calibri", Font.BOLD, 20));
                    updatedSaveCardRow.add(saveCardQuestion);

                    //Create yes button
                    JPanel saveCardPanel = new JPanel(new GridLayout(1, 2, 40, 0));
                    saveCardPanel.setBackground(Color.darkGray);

                    JButton Yes = new JButton("Yes");
                    Yes.setBackground(Color.darkGray);
                    Yes.setForeground(Color.lightGray);
                    Yes.setFocusable(false);

                    Yes.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            cardName = textField.getText();
                            cardNumberSaved = textFieldNumber.getText();
                            UserDao.saveCardDetails(controller.getUser().getName(), cardName, cardNumberSaved);
                            controller.getUser().setCardName(cardName);
                            controller.getUser().setCardNumber(cardNumberSaved);

                            JOptionPane.showMessageDialog(null, "Your card has been saved.", "Success", JOptionPane.WARNING_MESSAGE);

                            closeFrame();
                            purchaseItemsCard();
                        }
                    });


                    //Create no button
                    JButton No = new JButton("No");
                    No.setBackground(Color.darkGray);
                    No.setForeground(Color.lightGray);
                    No.setFocusable(false);

                    No.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            closeFrame();
                            purchaseItemsCard();
                        }
                    });

                    saveCardPanel.add(Yes);
                    saveCardPanel.add(No);
                    updatedSaveCardRow.add(saveCardPanel);
                    cardButtonPanel.add(updateCardHolderRow);
                    cardButtonPanel.add(updateCardNumberRow);
                    cardButtonPanel.add(updatedSaveCardRow);

                }
            });

            ChangeSavedCardPanel.add(yesChange);
            ChangeSavedCardPanel.add(new JLabel(""));
            ChangeSavedCardPanel.add(new JLabel(""));
            changeSavedCardRow.add(ChangeSavedCardPanel);
            cardButtonPanel.add(cardNumberRow);
            cardButtonPanel.add(changeSavedCardRow);

            panel.add(cardButtonPanel);

        }


        return panel;
    }



    private JPanel createThirdPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 2, 40, 0));
        panel.setBackground(Color.darkGray);

        //Create paid panel
        JPanel paidPanel = new JPanel(new GridLayout(1, 2));
        paidPanel.setBackground(Color.darkGray);

        JLabel totalLabel = new JLabel("Grand Total: $" + controller.getUser().getTotalCost());
        totalLabel.setForeground(Color.lightGray);
        totalLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        totalLabel.setHorizontalAlignment(JLabel.TRAILING);
        paidPanel.add(totalLabel);

        amountBeingPaid = new JLabel("Being Paid: $" + controller.getUser().getTotalCost());
        amountBeingPaid.setForeground(Color.lightGray);
        amountBeingPaid.setFont(new Font("Arial", Font.PLAIN, 20));
        amountBeingPaid.setHorizontalAlignment(JLabel.TRAILING);
        paidPanel.add(amountBeingPaid);

        panel.add(paidPanel);

        //Create transaction panel
        JPanel transacationPanel = new JPanel(new GridLayout(1, 2, 40, 0));
        transacationPanel.setBackground(Color.darkGray);

        JButton cancelTransaction = new JButton("Cancel");
        cancelTransaction.setBackground(Color.darkGray);
        cancelTransaction.setForeground(Color.lightGray);
        cancelTransaction.setFocusable(false);
        transacationPanel.add(cancelTransaction);

        cancelTransaction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                cancelTransactionFunction("User Cancelled");

            }
        });

        pay = new JButton("Pay");
        pay.setBackground(Color.darkGray);
        pay.setForeground(Color.lightGray);
        pay.setFocusable(false);
        transacationPanel.add(pay);

        pay.addActionListener(e -> {

            if (controller.getUser().getName().equalsIgnoreCase("guest")) {
                cardName = textField.getText();
                cardNumberSaved = textFieldNumber.getText();
                if (UserDao.creditCardCheck(cardName, cardNumberSaved) == 1) {
                    purchaseItemsCard();
                    closeFrame();
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect card details. Please try again.", "Uh Oh", JOptionPane.WARNING_MESSAGE);
                }
            } else {

                if (UserDao.creditCardCheck(cardName, cardNumberSaved) == 1) {
                    if (controller.getUser().getCardName().equals(cardName) && controller.getUser().getCardNumber().equals(cardNumberSaved)) {
                        purchaseItemsCard();
                        closeFrame();

                    } else {
                        saveCardRow.setVisible(true);

                    }
                }

                if (savedCardString.equals("")) {
                    cardName = textField.getText();
                    cardNumberSaved = textFieldNumber.getText();
                    if (UserDao.creditCardCheck(cardName, cardNumberSaved) == 1) {
                        if (controller.getUser().getCardName().equals(cardName) && controller.getUser().getCardNumber().equals(cardNumberSaved)) {
                            purchaseItemsCard();
                            closeFrame();


                        } else {
                            saveCardRow.setVisible(true);
                        }
                    }

                }

                if (savedCardString.equals("") || pressChangeCard == true) {
                    cardName = textField.getText();
                    cardNumberSaved = textFieldNumber.getText();
                    if (UserDao.creditCardCheck(cardName, cardNumberSaved) == 1) {
                        if (controller.getUser().getCardName().equals(cardName) && controller.getUser().getCardNumber().equals(cardNumberSaved)) {
                            closeFrame();
                            purchaseItemsCard();
                        } else {
                            if (pressChangeCard == false) {
                                saveCardRow.setVisible(true);
                            } else {
                                updatedSaveCardRow.setVisible(true);
                            }
                        }


                    } else {
                        JOptionPane.showMessageDialog(null, "Incorrect card details. Please try again.", "Uh Oh", JOptionPane.WARNING_MESSAGE);

                    }

                } else {
                    closeFrame();
                    purchaseItemsCard();
                }
            }

        });

        panel.add(transacationPanel);

        return panel;
    }
}
