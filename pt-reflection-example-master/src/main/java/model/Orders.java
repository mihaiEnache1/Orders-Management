package model;

public class Orders {
    private int id;
    private int idClient;
    private int idProduct;
    private int quantity;

    /**
     * This is a constructor which will be used in the AbstractDAO in the createObjects method to instantiate a generic object
     */
    public Orders() {
    }

    /**
     * @param id which represents the order's id
     * @param idClient which represents the client's id
     * @param idProduct which represents the product's id
     * @param quantity which represents the product's quantity
     */
    public Orders(int id, int idClient, int idProduct, int quantity) {
        this.id = id;
        this.idClient = idClient;
        this.idProduct = idProduct;
        this.quantity = quantity;
    }

    /**
     * @param idClient which represents the client's id
     * @param idProduct which represents the product's id
     * @param quantity which represents the product's quantity
     */
    public Orders(int idClient, int idProduct, int quantity) {
        this.idClient = idClient;
        this.idProduct = idProduct;
        this.quantity = quantity;
    }

    /**
     * @return int which represents the order's id value
     */
    public int getId() {
        return id;
    }

    /**
     * @param id which represents the new order's id value
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return int which represents the client's id value
     */
    public int getIdClient() {
        return idClient;
    }

    /**
     * @param idClient which represents the new client's id value
     */
    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    /**
     * @return int which represents the product's id value
     */
    public int getIdProduct() {
        return idProduct;
    }

    /**
     * @param idProduct which represents the new product's id value
     */
    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    /**
     * @return int which represents the product's quantity value
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity which represents the new product's quantity value
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return String which all the information of an order
     */
    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", idClient=" + idClient +
                ", idProduct=" + idProduct +
                ", quantity=" + quantity +
                '}';
    }
}
