package bll;

import dao.ProductDAO;
import model.Product;
import java.util.List;
import java.util.NoSuchElementException;

public class ProductBLL {
    private final ProductDAO productDAO;

    /**
     * This is a constructor used for initializing the productDao variable
     */
    public ProductBLL() {
        productDAO = new ProductDAO();
    }

    /**
     * @param id the product's id that is used for finding the product with the given id
     * @return a product object
     * This method is used to retrieve the object with the given id from database
     * If the product is null, it means that no product has been found and the method will throw an exception
     */
    public Product findById(int id) {
        Product product = productDAO.findById(id);
        if(product == null) {
            throw new NoSuchElementException("Could not find product by the given id");
        }
        return product;
    }

    /**
     * @param name the product's name that is used for finding the product with the given name
     * @return a product object
     * This method is used to retrieve the object with the given name from database
     * If the product is null, it means that no product has been found and the method will throw an exception
     */
    public Product findByName(String name) {
        Product product = productDAO.findByName(name);
        if(product == null) {
            throw new NoSuchElementException("Could not find product by name");
        }
        return product;
    }

    /**
     * @return List
     * This method returns a list of products retrieved by the query from database
     * If no data has been found, the method will throw an exception
     */
    public List<Product> findAllProducts() {
        List<Product> products = productDAO.findAll();
        if(products == null){
            throw new NoSuchElementException("Could not list products");
        }
        return products;
    }

    /**
     * @param product - the product that we want to insert into database
     * This method tries to insert a product into database. If the insert operation is not executed successfully, an exception will be thrown
     *
     */
    public void insert(Product product) {
        int ok =  productDAO.insert(product);
        if(ok == 0) {
            throw new NoSuchElementException("Could not insert product");
        }
    }

    /**
     * @param id - product's id that we want to delete from database
     * This method tries to delete a product from database. If the delete operation is not executed successfully, an exception will be thrown
     */
    public void delete(int id) {
        int ok = productDAO.delete(id);
        if(ok == 0) {
            throw new NoSuchElementException("Could not delete product");
        }
    }

    /**
     * @param product the product whose information we want to change
     * @param id product's id
     * This method tries to change some properties of the given object. The object will be searched using its id.
     * If the update operation is not executed successfully, an exception will be thrown
     */
    public void update(Product product, int id) {
        int ok = productDAO.update(product, id);
        if(ok == 0) {
            throw new NoSuchElementException("Could not update product");
        }
    }
}
