package presentation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OrdersView extends JFrame {
    private JPanel contentPane;
    private final JComboBox selectClient;
    private final JComboBox selectProduct;
    private JTextField clientTxt;
    private JTextField productTxt;
    private JTextField quantityTxt;
    private JTextField quantity;
    private final JTextArea textArea;
    private final JButton orderButton;
    private final JButton backButton;

    /**
     * This is a constructor for creating the orders view. All instance variables are initialized and added to the panel's view.
     */
    public OrdersView() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 997, 535);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        selectClient = new JComboBox();
        selectClient.setMaximumRowCount(20);
        selectClient.setBounds(287, 121, 174, 35);
        contentPane.add(selectClient);

        selectProduct = new JComboBox();
        selectProduct.setMaximumRowCount(20);
        selectProduct.setBounds(287, 219, 174, 35);
        contentPane.add(selectProduct);

        quantity = new JTextField();
        quantity.setBounds(287, 306, 174, 35);
        contentPane.add(quantity);
        quantity.setColumns(10);

        clientTxt = new JTextField();
        clientTxt.setHorizontalAlignment(SwingConstants.RIGHT);
        clientTxt.setFont(new Font("Tahoma", Font.PLAIN, 20));
        clientTxt.setText("Select client:");
        clientTxt.setColumns(10);
        clientTxt.setBounds(61, 121, 189, 35);
        contentPane.add(clientTxt);

        productTxt = new JTextField();
        productTxt.setHorizontalAlignment(SwingConstants.RIGHT);
        productTxt.setFont(new Font("Tahoma", Font.PLAIN, 20));
        productTxt.setText("Select product:");
        productTxt.setColumns(10);
        productTxt.setBounds(61, 219, 189, 35);
        contentPane.add(productTxt);

        quantityTxt = new JTextField();
        quantityTxt.setFont(new Font("Tahoma", Font.PLAIN, 20));
        quantityTxt.setHorizontalAlignment(SwingConstants.RIGHT);
        quantityTxt.setText("Introduce quantity:");
        quantityTxt.setColumns(10);
        quantityTxt.setBounds(61, 306, 189, 35);
        contentPane.add(quantityTxt);

        textArea = new JTextArea();
        textArea.setBounds(499, 10, 474, 478);
        contentPane.add(textArea);

        orderButton = new JButton("Make Order");
        orderButton.setFont(new Font("Tahoma", Font.BOLD, 20));
        orderButton.setBounds(171, 383, 174, 46);
        contentPane.add(orderButton);

        backButton = new JButton("<-");
        backButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        backButton.setBounds(10, 12, 67, 35);
        contentPane.add(backButton);
    }

    /**
     * @return JButton
     * This method will be used in the Controller class to know if the user has pressed it. If yes, the "Create order" operation will be executed
     */
    public JButton getOrderButton() {
        return orderButton;
    }

    /**
     * @param actionListener
     * This method will be used in the Controller class to implement an action listener for the "Make Order" button
     */
    public void orderListener(ActionListener actionListener) {
        orderButton.addActionListener(actionListener);
    }

    /**
     * @return int which represent the introduced quantity by the user in the text field
     */
    public int getQuantity(){
        return Integer.parseInt(quantity.getText());
    }

    /**
     * @param text which will be the order text build in the Controller class
     * This method will be used in the Controller class to display the orders
     */
    public void setTextArea(String text) {
        textArea.setText(textArea.getText() + "\n" + text);
    }

    /**
     * @param clients which represents the existing clients from database
     * This method will be used in the Controller class to modify the clients list
     */
    public void setClients(ArrayList<String> clients) {
        for(String s : clients) {
            selectClient.addItem(s);
        }
    }

    public void editClients(ArrayList<String> newClients) {
        selectClient.removeAllItems();
        setClients(newClients);
    }

    /**
     * @param products which represents the existing products from database
     * This method will be used in the Controller class to modify the products list
     */
    public void setProducts(ArrayList<String> products) {
        for(String s : products) {
            selectProduct.addItem(s);
        }
    }

    public void editProducts(ArrayList<String> newProducts) {
        selectProduct.removeAllItems();
        setProducts(newProducts);
    }

    /**
     * @param message which represents an error message
     * This method will display a pop-up with the given error message
     */
    public void errorMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
        refresh();
    }

    /**
     * This method will be used in the Controller class to refresh the quantity text field when an error occurs
     */
    public void refresh() {
        quantity.setText(null);
    }

    /**
     * This method will be used in the Controller class to refresh the text area
     */
    public void setTextArea() {
        textArea.setText(null);
    }

    /**
     * This method will be used in the Controller class to refresh the quantity text field when the used has pressed the back button
     */
    public void setQuantity() {
        quantity.setText(null);
    }

    /**
     * @return JButton
     * This method will be used in the Controller class to know if the user pressed the back button. If yes, the main page will be displayed and the order view will be hidden.
     */
    public JButton getBackButton() {
        return backButton;
    }

    /**
     * @param actionListener
     * This method will be used in the Controller class to implement an action listener for the back button
     */
    public void backListener(ActionListener actionListener) {
        backButton.addActionListener(actionListener);
    }

    /**
     * @return JComboBox
     * This method will be used in the Controller class to get a client from the clients list
     */
    public JComboBox getSelectClient() {
        return selectClient;
    }

    /**
     * @return JComboBox
     * This method will be used in the Controller class to get a product from the products list
     */
    public JComboBox getSelectProduct() {
        return selectProduct;
    }
}
