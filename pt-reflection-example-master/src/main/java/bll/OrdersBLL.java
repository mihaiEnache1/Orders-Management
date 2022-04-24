package bll;

import dao.OrdersDAO;
import model.Client;
import model.Orders;

import java.util.List;
import java.util.NoSuchElementException;

public class OrdersBLL {
    private final OrdersDAO ordersDAO;

    /**
     * This is a constructor used for initializing the ordersDAO variable
     */
    public OrdersBLL() {
        ordersDAO = new OrdersDAO();
    }

    /**
     * @param order - the order that we want to insert into database
     * This method tries to insert an order into database. If the insert operation is not executed successfully, an exception will be thrown
     *
     */
    public void insert(Orders order) {
        int ok =  ordersDAO.insert(order);
        if(ok == 0) {
            throw new NoSuchElementException("Could not insert order");
        }
    }

    public List<Orders> findAllOrders() {
        List<Orders> orders = ordersDAO.findAll();
        if(orders == null){
            throw new NoSuchElementException("Could not list clients");
        }
        return orders;
    }
}
