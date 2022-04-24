package presentation;

import bll.ClientBLL;
import bll.OrdersBLL;
import bll.ProductBLL;
import model.Client;
import model.Orders;
import model.Product;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private final ClientView clientView;
    private final MainView mainView;
    private final ProductView productView;
    private final OrdersView ordersView;

    private final ClientBLL clientBLL;
    private final ProductBLL productBLL;
    private final OrdersBLL ordersBLL;

    /**
     * @param mainView which represents the main view instantiated in the main method
     * @param clientView which represents the client view instantiated in the main method
     * @param productView which represents the product view instantiated in the main method
     * @param ordersView which represents the orders view instantiated in the main method
     * This constructor initializes the controller's frames and implements the action listeners for every button of each view
     */
    public Controller(MainView mainView, ClientView clientView, ProductView productView, OrdersView ordersView) {
        this.clientView = clientView;
        this.mainView = mainView;
        this.productView = productView;
        this.ordersView = ordersView;

        this.clientBLL = new ClientBLL();
        this.productBLL = new ProductBLL();
        this.ordersBLL = new OrdersBLL();

        this.mainView.setVisible(true);

        ArrayList<String> clientsList = new ArrayList<>();
        ArrayList<String> productsList = new ArrayList<>();

        for(Client client : clientBLL.findAllClients()) {
            clientsList.add(client.getName());
        }

        for(Product product : productBLL.findAllProducts()) {
            productsList.add(product.getName());
        }

        this.ordersView.setClients(clientsList);
        this.ordersView.setProducts(productsList);

        this.mainView.addClientListener(e -> {
            if(e.getSource() == this.mainView.getClientButton()) {
                this.mainView.setVisible(false);
                this.clientView.setVisible(true);
            }
        });

        this.mainView.addProductListener(e -> {
            if(e.getSource() == this.mainView.getProductButton()) {
                this.mainView.setVisible(false);
                this.productView.setVisible(true);
            }
        });

        this.mainView.addOrderListener(e -> {
            if(e.getSource() == this.mainView.getOrderButton()) {
                this.mainView.setVisible(false);
                this.ordersView.setVisible(true);
            }
        });

        this.clientView.addBackListener(e -> {
            if(e.getSource() == this.clientView.getBackButton()) {
                this.mainView.setVisible(true);
                this.clientView.setVisible(false);
            }
        });

        this.productView.addBackListener(e -> {
            if(e.getSource() == this.productView.getBackButton()) {
                this.mainView.setVisible(true);
                this.productView.setVisible(false);
            }
        });

        this.ordersView.backListener(e -> {
            if(e.getSource() == this.ordersView.getBackButton()) {
                this.mainView.setVisible(true);
                this.ordersView.setVisible(false);
                this.ordersView.setTextArea();
                this.ordersView.setQuantity();
            }
        });

        this.clientView.insertClientListener(e -> {
            if(e.getSource() == this.clientView.getAddClientButton()) {
                Client client = new Client(this.clientView.getName(), this.clientView.getAddress(), this.clientView.getEmail());
                clientBLL.insert(client);
                clientsList.add(client.getName());
                this.clientView.getModel().addRow(
                        new Object[]{
                                this.clientView.getName(),
                                this.clientView.getAddress(),
                                this.clientView.getEmail(),
                        }
                );
                this.ordersView.getSelectClient().addItem(client.getName());
            }
        });

        this.clientView.deleteButtonListener(e -> {
            if(e.getSource() == this.clientView.getDeleteClientButton()) {
                List<Client> clients = clientBLL.findAllClients();
                int i = this.clientView.getTable().getSelectedRow();
                Client client = clients.get(i);
                clientBLL.delete(client.getId());
                clientsList.remove(client.getName());
                this.clientView.getModel().removeRow(this.clientView.getTable().getSelectedRow());
                this.ordersView.getSelectClient().removeItem(client.getName());
            }
        });

        this.clientView.editButtonListener(e -> {
            if(e.getSource() == this.clientView.getEditClientButton()) {
                List<Client> clients = clientBLL.findAllClients();
                int i = this.clientView.getTable().getSelectedRow();
                Client client = clients.get(i);
                Client newClient = new Client();
                if(!this.clientView.getName().isEmpty()) {
                    newClient.setName(this.clientView.getName());
                    clientsList.set(i, newClient.getName());
                    this.ordersView.editClients(clientsList);
                } else {
                    newClient.setName(client.getName());
                }
                if(!this.clientView.getAddress().isEmpty()) {
                    newClient.setAddress(this.clientView.getAddress());
                } else {
                    newClient.setAddress(client.getAddress());
                }
                if(!this.clientView.getEmail().isEmpty()) {
                    newClient.setEmail(this.clientView.getEmail());
                } else {
                    newClient.setEmail(client.getEmail());
                }
                clientBLL.update(newClient, client.getId());
                this.clientView.getModel().setValueAt(newClient.getName(), i, 0);
                this.clientView.getModel().setValueAt(newClient.getAddress(), i, 1);
                this.clientView.getModel().setValueAt(newClient.getEmail(), i, 2);
            }
        });

        this.clientView.viewAllListener(e -> {
            if(e.getSource() == this.clientView.getViewAllClientsButton()) {
                List<Client> clients = clientBLL.findAllClients();
                if(this.clientView.getModel().getRowCount() > 0) {
                    for(int count = 0; count < this.clientView.getModel().getRowCount(); count++) {
                        if( !(this.clientView.getModel().getValueAt(count, 0).equals(clients.get(count).getName())))
                            this.clientView.getModel().addRow(
                                    new Object[]{
                                            clients.get(count).getName(),
                                            clients.get(count).getAddress(),
                                            clients.get(count).getEmail(),
                                    }
                                );
                        }
                    }
                else {
                    for(Client client : clients) {
                        this.clientView.getModel().addRow(
                                new Object[]{
                                        client.getName(),
                                        client.getAddress(),
                                        client.getEmail(),
                                }
                        );
                    }
                }

            }
        });

        this.productView.insertProductListener(e -> {
            if(e.getSource() == this.productView.getAddProductButton()) {
                Product product = new Product(this.productView.getName(), this.productView.getPrice(), this.productView.getStock());
                productBLL.insert(product);
                productsList.add(product.getName());
                this.productView.getModel().addRow(
                        new Object[]{
                                this.productView.getName(),
                                this.productView.getPrice(),
                                this.productView.getStock(),
                        }
                );
                this.ordersView.getSelectProduct().addItem(product.getName());
            }
        });

        this.productView.deleteProductListener(e -> {
            if(e.getSource() == this.productView.getDeleteProductButton()) {
                List<Product> products = productBLL.findAllProducts();
                int i = this.productView.getTable().getSelectedRow();
                Product product = products.get(i);
                productBLL.delete(product.getId());
                this.productView.getModel().removeRow(this.productView.getTable().getSelectedRow());
                productsList.remove(product.getName());
                this.ordersView.getSelectProduct().removeItem(product.getName());
            }

        });

        this.productView.editProductListener(e -> {
            if(e.getSource() == this.productView.getEditProductButton()) {
                List<Product> products = productBLL.findAllProducts();
                int i = this.productView.getTable().getSelectedRow();
                Product product = products.get(i);
                Product newProduct = new Product();
                if(!this.productView.getName().isEmpty()) {
                    newProduct.setName(this.productView.getName());
                    productsList.set(i, newProduct.getName());
                    this.ordersView.editProducts(productsList);
                } else {
                    newProduct.setName(product.getName());
                }
                if(!this.productView.getPriceField().isEmpty()) {
                    newProduct.setPrice(this.productView.getPrice());
                } else {
                    newProduct.setPrice(product.getPrice());
                }
                if(!this.productView.getStockField().isEmpty()) {
                    newProduct.setStock(this.productView.getStock());
                } else {
                    newProduct.setStock(product.getStock());
                }
                productBLL.update(newProduct, product.getId());
                this.productView.getModel().setValueAt(newProduct.getName(), i, 0);
                this.productView.getModel().setValueAt(newProduct.getPrice(), i, 1);
                this.productView.getModel().setValueAt(newProduct.getStock(), i, 2);
            }
        });

        this.productView.viewAllListener(e -> {
            if(e.getSource() == this.productView.getViewAllProductsButton()) {
                List<Product> products = productBLL.findAllProducts();
                if(this.productView.getModel().getRowCount() > 0) {
                    for(int count = 0; count < this.productView.getModel().getRowCount(); count++) {
                        if(!(this.productView.getModel().getValueAt(count, 0).equals(products.get(count).getName()))) {
                            this.productView.getModel().addRow(
                                    new Object[]{
                                            products.get(count).getName(),
                                            products.get(count).getPrice(),
                                            products.get(count).getStock(),
                                    }
                            );
                        }
                    }
                } else {
                    for(Product product : products) {
                        this.productView.getModel().addRow(
                                new Object[]{
                                        product.getName(),
                                        product.getPrice(),
                                        product.getStock(),
                                }
                        );
                    }
                }
            }
        });

        this.ordersView.orderListener(e -> {
            if(e.getSource() == this.ordersView.getOrderButton()) {
                Product product = productBLL.findByName((String) this.ordersView.getSelectProduct().getSelectedItem());
                Client client = clientBLL.findByName((String) this.ordersView.getSelectClient().getSelectedItem());
                Orders orders = new Orders(client.getId(), product.getId(), this.ordersView.getQuantity());
                if(product.getStock() > 0) {
                    if (product.getStock() - ordersView.getQuantity() > 0) {
                        String text = "Client name: " + this.ordersView.getSelectClient().getSelectedItem() + "\n" +
                                "Product name: " + product.getName() + "\n" +
                                "Product price: " + product.getPrice() + "\n" +
                                "Quantity: " + this.ordersView.getQuantity();
                        this.ordersView.setTextArea(text);
                        product.setStock(product.getStock() - ordersView.getQuantity());
                        productBLL.update(product, product.getId());
                        ordersBLL.insert(orders);
                        int i = ordersBLL.findAllOrders().get(ordersBLL.findAllOrders().size() - 1).getId();
                        WriteToFile logFile = new WriteToFile();
                        String fileName = "order" + i + ".txt";
                        System.out.println(fileName);
                        logFile.write(fileName, text);
                    } else {
                        ordersView.errorMessage("Not enough products for the chosen quantity");
                    }
                } else {
                    ordersView.errorMessage("Product out of stock");
                }
            }
        });
    }

    public static void main(String[] args) {
        MainView mainView = new MainView();
        ClientView clientView = new ClientView();
        ProductView productView = new ProductView();
        OrdersView ordersView = new OrdersView();
        new Controller(mainView, clientView, productView, ordersView);
    }
}
