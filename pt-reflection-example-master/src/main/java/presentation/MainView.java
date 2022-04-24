package presentation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainView extends JFrame {
    private JPanel contentPane;
    private final JButton clientButton;
    private final JButton productButton;
    private final JButton orderButton;

    /**
     * This is a constructor for creating the main view. All instance variables are initialized and added to the panel's view.
     */
    public MainView() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 574, 457);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        clientButton = new JButton("Client View");
        clientButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        clientButton.setBounds(203, 106, 145, 40);
        contentPane.add(clientButton);

        productButton = new JButton("Product View");
        productButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        productButton.setBounds(203, 187, 145, 40);
        contentPane.add(productButton);

        orderButton = new JButton("Order View");
        orderButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        orderButton.setBounds(203, 266, 145, 40);
        contentPane.add(orderButton);
    }

    /**
     * @return JButton
     * This method will be used in the Controller class to know if the user has pressed the "Client View" button. If yes, the main page will be hidden and the client page will be displayed.
     */
    public JButton getClientButton() {
        return clientButton;
    }

    /**
     * @return JButton
     * This method will be used in the Controller class to know if the user has pressed the "Product View" button. If yes, the main page will be hidden and the product page will be displayed.
     */
    public JButton getProductButton() {
        return productButton;
    }

    /**
     * @return JButton
     * This method will be used in the Controller class to know if the user has pressed the "Order View" button. If yes, the main page will be hidden and the order page will be displayed.
     */
    public JButton getOrderButton() {
        return orderButton;
    }

    /**
     * @param action
     * This method will be used in the Controller class to implement an action listener for the "Client View" button
     */
    public void addClientListener(ActionListener action) {
        clientButton.addActionListener(action);
    }

    /**
     * @param action
     * This method will be used in the Controller class to implement an action listener for the "Product View" button
     */
    public void addProductListener(ActionListener action) {
        productButton.addActionListener(action);
    }

    /**
     * @param action
     * This method will be used in the Controller class to implement an action listener for the "Order View" button
     */
    public void addOrderListener(ActionListener action) {
        orderButton.addActionListener(action);
    }
}
