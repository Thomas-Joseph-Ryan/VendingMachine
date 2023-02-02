package CC12.Richard.Group1.Assign2;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;

public class ItemTypePageGUI extends GUI {

    private String categoryName;

    private int category;
    private int categoryCount = 0;
    private JPanel centrePanel;



    public ItemTypePageGUI(Controller controller, String categoryName, int category) {
        super(controller);
        this.categoryName = categoryName;
        this.category = category;
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(900, 700));
        getContentPane().setBackground(Color.darkGray);

        add(createHeader(), BorderLayout.PAGE_START);
        add(createCenter(), BorderLayout.CENTER);
//        add(createFooter(), BorderLayout.PAGE_END);

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

    private JPanel createHeader() {

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
                closeFrame();
                new SellerGUI(controller);
            }
        });
        panel.add(back);

        //Create title
        JLabel title = new JLabel(categoryName);
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

    private JPanel createCenter() {
        centrePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centrePanel.setBackground(Color.darkGray);
        controller.getItemsList().sort(new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                return Integer.compare(o1.getCode(), o2.getCode());
            }
        });

        for (Item item : controller.getItemsList()) {
            if (item.getCategory() != category) {
                continue;
            }
            JPanel itemPanel = new JPanel();
            itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS));
            itemPanel.setPreferredSize(new Dimension(250, 150));
            itemPanel.setBackground(new Color(0x525252));

            JPanel name = new JPanel(new GridLayout(1, 2));
            name.setBackground(new Color(0x525252));
            JLabel nameLabel = new JLabel("Name: ");
            nameLabel.setForeground(Color.lightGray);
            nameLabel.setHorizontalAlignment(JLabel.TRAILING);
            name.add(nameLabel);

            JTextField nameText = new JTextField(item.getName());
            name.add(nameText);

            itemPanel.add(name);

            JPanel code = new JPanel(new GridLayout(1, 2));
            code.setBackground(new Color(0x525252));
            JLabel codeLabel = new JLabel("Code: ");
            codeLabel.setForeground(Color.lightGray);
            codeLabel.setHorizontalAlignment(JLabel.TRAILING);
            code.add(codeLabel);

            JTextField codeText = new JTextField(String.valueOf(item.getCode()));
            code.add(codeText);

            itemPanel.add(code);

            JPanel category = new JPanel(new GridLayout(1, 2));
            category.setBackground(new Color(0x525252));
            JLabel categoryLabel = new JLabel("Category: ");
            categoryLabel.setForeground(Color.lightGray);
            categoryLabel.setHorizontalAlignment(JLabel.TRAILING);
            category.add(categoryLabel);


            JTextField categoryText = new JTextField(String.valueOf(item.getCategory()));
            category.add(categoryText);

            itemPanel.add(category);

            JPanel quantity = new JPanel(new GridLayout(1, 2));
            quantity.setBackground(new Color(0x525252));
            JLabel quantityLabel = new JLabel("Quantity: ");
            quantityLabel.setForeground(Color.lightGray);
            quantityLabel.setHorizontalAlignment(JLabel.TRAILING);
            quantity.add(quantityLabel);

            JTextField quantityText = new JTextField(String.valueOf(item.getQty()));
            quantity.add(quantityText);

            itemPanel.add(quantity);

            JPanel price = new JPanel(new GridLayout(1, 2));
            price.setBackground(new Color(0x525252));
            JLabel priceLabel = new JLabel("Price: ");
            priceLabel.setForeground(Color.lightGray);
            priceLabel.setHorizontalAlignment(JLabel.TRAILING);
            price.add(priceLabel);

            JTextField priceText = new JTextField(String.valueOf(item.getPrice()));
            price.add(priceText);

            itemPanel.add(price);

            JPanel bottomPanel = new JPanel();
            bottomPanel.setBackground(new Color(0x525252));

//            JButton remove = new JButton("Remove");
//            remove.setBackground(new Color(0x525252));
//            remove.setForeground(Color.lightGray);
//            remove.setFocusable(false);
//            remove.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    //TODO: Change depending on text field
//                }
//            });
//
//            bottomPanel.add(remove);


            JButton confirmChanges = new JButton("Confirm");
            confirmChanges.setBackground(new Color(0x525252));
            confirmChanges.setForeground(Color.lightGray);
            confirmChanges.setFocusable(false);
            confirmChanges.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!checkName(item, nameText.getText())) {
                        return;
                    }
                    if (!checkCode(item, codeText.getText())) {
                        return;
                    }
                    if (!checkCategory(categoryText.getText())) {
                        return;
                    }
                    if (!checkPrice(priceText.getText())) {
                        return;
                    }
                    if (!checkQty(quantityText.getText())) {
                        return;
                    }
                    int oldCode = item.getCode();
                    item.setName(nameText.getText());
                    item.setCode(Integer.parseInt(codeText.getText()));
                    item.setCategory(Integer.parseInt(categoryText.getText()));
                    item.setPrice(Double.parseDouble(priceText.getText()));
                    item.setQty(Integer.parseInt(quantityText.getText()));
                    ItemDao.updateProduct(oldCode, item);
                    JOptionPane.showMessageDialog(null, "Item Updated.", "Nice!", JOptionPane.INFORMATION_MESSAGE);
                }
            });

            bottomPanel.add(confirmChanges);
            itemPanel.add(bottomPanel);
            centrePanel.add(itemPanel);
            categoryCount ++;
        }

        return centrePanel;
    }

//    public JPanel createFooter() {
//        JPanel panel = new JPanel();
//        panel.setBackground(Color.darkGray);
//
//        JButton addNewProduct = new JButton("Add new product");
//        addNewProduct.setBackground(Color.darkGray);
//        addNewProduct.setForeground(Color.lightGray);
//        addNewProduct.setFocusable(false);
//        addNewProduct.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (categoryCount < 15) {
//                    JPanel itemPanel = new JPanel();
//                    itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS));
//                    itemPanel.setPreferredSize(new Dimension(250, 150));
//                    itemPanel.setBackground(new Color(0x525252));
//
//                    JPanel name = new JPanel(new GridLayout(1, 2));
//                    name.setBackground(new Color(0x525252));
//                    JLabel nameLabel = new JLabel("Name: ");
//                    nameLabel.setForeground(Color.lightGray);
//                    nameLabel.setHorizontalAlignment(JLabel.TRAILING);
//                    name.add(nameLabel);
//
//                    JTextField nameText = new JTextField();
//                    name.add(nameText);
//
//                    itemPanel.add(name);
//
//                    JPanel code = new JPanel(new GridLayout(1, 2));
//                    code.setBackground(new Color(0x525252));
//                    JLabel codeLabel = new JLabel("Code: ");
//                    codeLabel.setForeground(Color.lightGray);
//                    codeLabel.setHorizontalAlignment(JLabel.TRAILING);
//                    code.add(codeLabel);
//
//                    JTextField codeText = new JTextField();
//                    code.add(codeText);
//
//                    itemPanel.add(code);
//
//                    JPanel category = new JPanel(new GridLayout(1, 2));
//                    category.setBackground(new Color(0x525252));
//                    JLabel categoryLabel = new JLabel("Category: ");
//                    categoryLabel.setForeground(Color.lightGray);
//                    categoryLabel.setHorizontalAlignment(JLabel.TRAILING);
//                    category.add(categoryLabel);
//
//                    JTextField categoryText = new JTextField();
//                    category.add(categoryText);
//
//                    itemPanel.add(category);
//
//                    JPanel quantity = new JPanel(new GridLayout(1, 2));
//                    quantity.setBackground(new Color(0x525252));
//                    JLabel quantityLabel = new JLabel("Quantity: ");
//                    quantityLabel.setForeground(Color.lightGray);
//                    quantityLabel.setHorizontalAlignment(JLabel.TRAILING);
//                    quantity.add(quantityLabel);
//
//                    JTextField quantityText = new JTextField();
//                    quantity.add(quantityText);
//
//                    itemPanel.add(quantity);
//
//                    JPanel price = new JPanel(new GridLayout(1, 2));
//                    price.setBackground(new Color(0x525252));
//                    JLabel priceLabel = new JLabel("Price: ");
//                    priceLabel.setForeground(Color.lightGray);
//                    priceLabel.setHorizontalAlignment(JLabel.TRAILING);
//                    price.add(priceLabel);
//
//                    JTextField priceText = new JTextField();
//                    price.add(priceText);
//
//                    itemPanel.add(price);
//
//                    JPanel bottomPanel = new JPanel();
//                    bottomPanel.setBackground(new Color(0x525252));
//
//                    JButton remove = new JButton("Remove");
//                    remove.setBackground(new Color(0x525252));
//                    remove.setForeground(Color.lightGray);
//                    remove.setFocusable(false);
//                    remove.addActionListener(new ActionListener() {
//                        @Override
//                        public void actionPerformed(ActionEvent e) {
//                            //TODO: Change depending on text field
//                        }
//                    });
//
//                    bottomPanel.add(remove);
//
//
//                    JButton confirmChanges = new JButton("Confirm");
//                    confirmChanges.setBackground(new Color(0x525252));
//                    confirmChanges.setForeground(Color.lightGray);
//                    confirmChanges.setFocusable(false);
//                    confirmChanges.addActionListener(new ActionListener() {
//                        @Override
//                        public void actionPerformed(ActionEvent e) {
//                            //TODO: Change depending on text field
//                        }
//                    });
//
//                    bottomPanel.add(confirmChanges);
//                    itemPanel.add(bottomPanel);
//
//                    centrePanel.add(itemPanel);
//                    centrePanel.updateUI();
//                    categoryCount ++;
//                }
//            }
//        });
//
//        panel.add(addNewProduct);
//        return panel;
//    }


//                    priceText;
//                    quantityText;

    private boolean checkName(Item thisItem, String nameText) {
        boolean valid = true;

        for (Item item : controller.getItemsList()) {

            if (item.equals(thisItem)) {
                continue;
            }

            if (item.getName().equalsIgnoreCase(nameText)) {
                JOptionPane.showMessageDialog(null, "There is already another product with this name.", "Uh Oh", JOptionPane.WARNING_MESSAGE);
                valid = false;
            }
        }

        return valid;
    }

    private boolean checkCode(Item thisItem, String codeText) {
        boolean valid = true;

        try {
            Integer.parseInt(codeText);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Code must be made of only digits.", "Uh Oh", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        for (Item item : controller.getItemsList()) {

            if (item.equals(thisItem)) {
                continue;
            }

            if (item.getCode() == Integer.parseInt(codeText)) {
                JOptionPane.showMessageDialog(null, "There is already another product with this code.", "Uh Oh", JOptionPane.WARNING_MESSAGE);
                valid = false;
            }
        }

        return valid;
    }

    private boolean checkCategory(String categoryText) {
        boolean valid = true;

        try {
            int cat = Integer.parseInt(categoryText);
            if (cat != 0 && cat != 1 && cat != 2 && cat !=3 ) {
                JOptionPane.showMessageDialog(null, "Category must be from 0 - 3 inclusive.", "Uh Oh", JOptionPane.WARNING_MESSAGE);
                valid = false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Category must be a digit from 0 - 3 inclusive.", "Uh Oh", JOptionPane.WARNING_MESSAGE);
            valid = false;
        }

        return valid;
    }

    private boolean checkPrice(String priceText) {
        boolean valid = true;

        try {
            double price = Double.parseDouble(priceText);
            if (price <= 0 ) {
                JOptionPane.showMessageDialog(null, "Price must be greater then 0.", "Uh Oh", JOptionPane.WARNING_MESSAGE);
                valid = false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Price must only contain digits.", "Uh Oh", JOptionPane.WARNING_MESSAGE);
            valid = false;
        }

        return valid;
    }

    private boolean checkQty(String qtyText) {
        boolean valid = true;

        try {
            int qty = Integer.parseInt(qtyText);
            if (qty <= 0 || qty > 15) {
                JOptionPane.showMessageDialog(null, "Qty must be greater then 0 and 15 or less.", "Uh Oh", JOptionPane.WARNING_MESSAGE);
                valid = false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Qty must only contain digits.", "Uh Oh", JOptionPane.WARNING_MESSAGE);
            valid = false;
        }

        return valid;
    }
}
