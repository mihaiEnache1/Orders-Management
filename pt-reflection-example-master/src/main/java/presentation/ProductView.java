package presentation;

import model.Product;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class ProductView extends JFrame {
    private JPanel contentPane;
    private final JButton addProductButton;
    private final JButton editProductButton;
    private final JButton deleteProductButton;
    private final JButton viewAllProductsButton;
    private JPanel panel;
    private final JTextField nameField;
    private final JTextField priceField;
    private final JTextField stockField;
    private final JTable table;
    private final DefaultTableModel model;
    private final JButton backButton;
    private AbstractView<Product> abstractView;

    /**
     * This is a constructor for creating the product view. All instance variables are initialized and added to the panel's view.
     */
    public ProductView() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 880, 509);
        contentPane = new JPanel(new BorderLayout());
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        addProductButton = new JButton("Add Product");
        addProductButton.setBackground(Color.WHITE);
        addProductButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        addProductButton.setBounds(124, 230, 218, 38);
        contentPane.add(addProductButton);

        editProductButton = new JButton("Edit Product");
        editProductButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        editProductButton.setBackground(Color.WHITE);
        editProductButton.setBounds(124, 291, 218, 38);
        contentPane.add(editProductButton);

        deleteProductButton = new JButton("Delete Product");
        deleteProductButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        deleteProductButton.setBackground(Color.WHITE);
        deleteProductButton.setBounds(124, 349, 218, 38);
        contentPane.add(deleteProductButton);

        viewAllProductsButton = new JButton("View All Products");
        viewAllProductsButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        viewAllProductsButton.setBackground(Color.WHITE);
        viewAllProductsButton.setBounds(124, 409, 218, 38);
        contentPane.add(viewAllProductsButton);

        backButton = new JButton("<-");
        backButton.setBackground(Color.WHITE);
        backButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        backButton.setBounds(10, 22, 73, 32);
        contentPane.add(backButton);

        panel = new JPanel(new BorderLayout());
        panel.setBounds(405, 38, 432, 394);
        contentPane.add(panel);

        abstractView = new AbstractView<>();
        String[] columns = abstractView.columns(new Product());

        model = new DefaultTableModel(columns, 0);

        table = new JTable(model);

        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        nameField = new JTextField();
        nameField.setHorizontalAlignment(SwingConstants.CENTER);
        nameField.setFont(new Font("Tahoma", Font.PLAIN, 18));
        nameField.setColumns(10);
        nameField.setBounds(124, 74, 218, 32);
        contentPane.add(nameField);

        priceField = new JTextField();
        priceField.setHorizontalAlignment(SwingConstants.CENTER);
        priceField.setFont(new Font("Tahoma", Font.PLAIN, 18));
        priceField.setColumns(10);
        priceField.setBounds(124, 126, 218, 32);
        contentPane.add(priceField);

        stockField = new JTextField();
        stockField.setHorizontalAlignment(SwingConstants.CENTER);
        stockField.setFont(new Font("Tahoma", Font.PLAIN, 18));
        stockField.setColumns(10);
        stockField.setBounds(124, 177, 218, 32);
        contentPane.add(stockField);
    }

    /**
     * @return String which represents the name of the product
     */
    public String getName() {
        return nameField.getText();
    }

    /**
     * @return int which represents the price of the product
     */
    public int getPrice() {
        return Integer.parseInt(priceField.getText());
    }

    public String getPriceField() {
        return priceField.getText();
    }

    /**
     * @return int which represents the stock of the product
     */
    public int getStock() {
        return Integer.parseInt(stockField.getText());
    }

    public String getStockField() {
        return stockField.getText();
    }

    /**
     * @return JButton
     * This method will be used in the Controller class to know if the user has pressed it. If yes, the main page will be displayed and the product page will be hidden.
     */
    public JButton getBackButton() {
        return backButton;
    }

    /**
     * @return JButton
     * This method will be used in the Controller class to know if the user has pressed it so that the Insert operation will be executed
     */
    public JButton getAddProductButton() {
        return addProductButton;
    }

    /**
     * @return JButton
     * This method will be used in the Controller class to know if the user has pressed it. If yes, the Delete operation will be executed
     */
    public JButton getDeleteProductButton() {
        return deleteProductButton;
    }

    /**
     * @return JButton
     * This method will be used in the Controller class to know if the user has pressed it. If yes, the Update operation will be executed
     */
    public JButton getEditProductButton() {
        return editProductButton;
    }

    /**
     * @return JButton
     * This method will be used in the Controller class to know if the user has pressed it. If yes, all the existing products from database will be displayed
     */
    public JButton getViewAllProductsButton() {
        return viewAllProductsButton;
    }

    /**
     * @return DefaultTableModel
     * This method will be used in the Controller class to modify the table
     */
    public DefaultTableModel getModel() {
        return model;
    }

    /**
     * @return JTable
     * This method will be used in the Controller class to access data from the table
     */
    public JTable getTable() {
        return table;
    }

    /**
     * @param actionListener
     * This method will be used in the Controller class to implement an action listener for "Add product" button
     */
    public void insertProductListener(ActionListener actionListener) {
        addProductButton.addActionListener(actionListener);
    }

    /**
     * @param actionListener
     * This method will be used in the Controller class to implement an action listener for "Edit product" button
     */
    public void editProductListener(ActionListener actionListener) {
        editProductButton.addActionListener(actionListener);
    }

    /**
     * @param actionListener
     * This method will be used in the Controller class to implement an action listener for "Delete product" button
     */
    public void deleteProductListener(ActionListener actionListener) {
        deleteProductButton.addActionListener(actionListener);
    }

    /**
     * @param actionListener
     * This method will be used in the Controller class to implement an action listener for "View All Products" button
     */
    public void viewAllListener(ActionListener actionListener) {
        viewAllProductsButton.addActionListener(actionListener);
    }

    /**
     * @param actionListener
     * This method will be used in the Controller class to implement an action listener for back button
     */
    public void addBackListener(ActionListener actionListener) {
        backButton.addActionListener(actionListener);
    }
}
