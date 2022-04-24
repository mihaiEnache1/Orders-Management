package model;

public class Product {
    private int id;
    private String name;
    private int price;
    private int stock;

    /**
     * This is a constructor which will be used in the AbstractDAO class in the method createObjects
     */
    public Product(){
    }

    /**
     * @param id which represents the product's id
     * @param name which represents the product's name
     * @param price which represents the product's price
     * @param stock which represents the product's stock
     */
    public Product(int id, String name, int price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    /**
     * @param name which represents the product's name
     * @param price which represents the product's price
     * @param stock which represents the product's stock
     */
    public Product(String name, int price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    /**
     * @return int which represent the product's id value
     */
    public int getId() {
        return id;
    }

    /**
     * @param id which is the new product's id value
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return String which represents the product's name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name which represents the new product's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return int which represents the product's price
     */
    public int getPrice() {
        return price;
    }

    /**
     * @param price which represents the new product's price
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * @return int which represents the product's stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * @param stock which represents the new product's stock
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * @return String which represents all the information of a product
     */
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                '}';
    }
}
