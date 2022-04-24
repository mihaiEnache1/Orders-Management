package presentation;

import model.Client;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class ClientView extends JFrame {
    private JPanel contentPane;
    private final JButton addClientButton;
    private final JButton editClientButton;
    private final JButton deleteClientButton;
    private final JButton viewAllClientsButton;
    private final JTable table;
    private final JTextField nameField;
    private final JTextField addressField;
    private final JTextField emailField;
    private JPanel panel;
    private final DefaultTableModel model;
    private final JButton backButton;
    private AbstractView<Client> abstractView;

    /**
     * This is a constructor used to initialize the instance variables and create the view
     */
    public ClientView() {
        abstractView = new AbstractView<>();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 880, 509);
        contentPane = new JPanel(new BorderLayout());
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        addClientButton = new JButton("Add Client");
        addClientButton.setBackground(Color.WHITE);
        addClientButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        addClientButton.setBounds(119, 212, 171, 38);
        contentPane.add(addClientButton);

        editClientButton = new JButton("Edit Client");
        editClientButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        editClientButton.setBackground(Color.WHITE);
        editClientButton.setBounds(119, 272, 171, 38);
        contentPane.add(editClientButton);

        deleteClientButton = new JButton("Delete Client");
        deleteClientButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        deleteClientButton.setBackground(Color.WHITE);
        deleteClientButton.setBounds(119, 336, 171, 38);
        contentPane.add(deleteClientButton);

        viewAllClientsButton = new JButton("View All Clients");
        viewAllClientsButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        viewAllClientsButton.setBackground(Color.WHITE);
        viewAllClientsButton.setBounds(119, 396, 171, 38);
        contentPane.add(viewAllClientsButton);

        backButton = new JButton("<-");
        backButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        backButton.setBounds(0, 20, 60, 25);
        contentPane.add(backButton);

        nameField = new JTextField();
        nameField.setHorizontalAlignment(SwingConstants.CENTER);
        nameField.setFont(new Font("Tahoma", Font.PLAIN, 18));
        nameField.setColumns(10);
        nameField.setBounds(119, 69, 171, 25);
        contentPane.add(nameField);

        addressField = new JTextField();
        addressField.setHorizontalAlignment(SwingConstants.CENTER);
        addressField.setFont(new Font("Tahoma", Font.PLAIN, 18));
        addressField.setColumns(10);
        addressField.setBounds(119, 117, 171, 25);
        contentPane.add(addressField);

        emailField = new JTextField();
        emailField.setHorizontalAlignment(SwingConstants.CENTER);
        emailField.setFont(new Font("Tahoma", Font.PLAIN, 18));
        emailField.setColumns(10);
        emailField.setBounds(119, 167, 171, 25);
        contentPane.add(emailField);

        panel = new JPanel(new BorderLayout());
        panel.setBounds(405, 38, 432, 394);
        contentPane.add(panel);

        String[] columns = abstractView.columns(new Client());

        model = new DefaultTableModel(columns, 0);

        table = new JTable(model);

        panel.add(new JScrollPane(table), BorderLayout.CENTER);
    }

    /**
     * @return JButton
     * This is a method which will be used in the Controller class to know if the user has pressed the back button. If yes, the main page will be displayed and the client view will be hidden.
     */
    public JButton getBackButton() {
        return backButton;
    }

    /**
     * @return JButton
     * This is a method which will be used in the Controller class to know if the user has pressed the "Insert Client" button. If yes, the "Insert" operation will be executed
     */
    public JButton getAddClientButton() {
        return addClientButton;
    }

    /**
     * @return JButton
     * This is a method which will be used in the Controller class to know if the user has pressed the "Delete Client" button. If yes, the "Delete Client" operation will be executed
     */
    public JButton getDeleteClientButton() {
        return deleteClientButton;
    }

    /**
     * @return JButton
     * This is a method which will be used in the Controller class to know if the user has pressed the "Edit Client" button. If yes, the "Update" operation will be executed
     */
    public JButton getEditClientButton() {
        return editClientButton;
    }

    /**
     * @return JButton
     * This is a method which will be used in the Controller class to know if the user has pressed the "View All Clients" button. If yes, the "Display all Clients" will be executed
     */
    public JButton getViewAllClientsButton() {
        return viewAllClientsButton;
    }

    /**
     * @param actionListener
     * This method will be used in the Controller class to implement an action listener for the back button
     */
    public void addBackListener(ActionListener actionListener) {
        backButton.addActionListener(actionListener);
    }

    /**
     * @param actionListener
     * This method will be used in the Controller class to implement an action listener for the "Add Client" button
     */
    public void insertClientListener(ActionListener actionListener) {
        addClientButton.addActionListener(actionListener);
    }

    /**
     * @param actionListener
     * This method will be used in the Controller class to implement an action listener for the "Delete Client" button
     */
    public void deleteButtonListener(ActionListener actionListener) {
        deleteClientButton.addActionListener(actionListener);
    }

    /**
     * @param actionListener
     * This method will be used in the Controller class to implement an action listener for the "Edit Client" button
     */
    public void editButtonListener(ActionListener actionListener) {
        editClientButton.addActionListener(actionListener);
    }

    /**
     * @param actionListener
     * This method will be used in the Controller class to implement an action listener for the "View All Clients" button
     */
    public void viewAllListener(ActionListener actionListener) {
        viewAllClientsButton.addActionListener(actionListener);
    }

    /**
     * @return String which represents the client's name introduced by the user in the specific text field
     */
    public String getName() {
        return nameField.getText();
    }

    /**
     * @return String which represents the client's address introduced by the user in the specific text field
     */
    public String getAddress() {
        return addressField.getText();
    }

    /**
     * @return String which represents the client's email introduced by the user in the specific text field
     */
    public String getEmail() {
        return emailField.getText();
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
     * This method will be used in the Controller class to get data from the table
     */
    public JTable getTable() {
        return table;
    }
}
