package bll;

import dao.ClientDAO;
import model.Client;
import java.util.List;
import java.util.NoSuchElementException;

public class ClientBLL {
    private final ClientDAO clientDAO;

    /**
     * This is a constructor used for initializing the clientDAO variable
     */
    public ClientBLL() {
        clientDAO = new ClientDAO();
    }

    /**
     * @param id the client's id that is used for finding the client with the given id
     * @return a client object
     * This method is used to retrieve the object with the given id from database
     * If the client is null, it means that no client has been found and the method will throw an exception
     */
    public Client findById(int id) {
        Client client = clientDAO.findById(id);
        if(client == null) {
            throw new NoSuchElementException("Could not find client by the given id");
        }
        return client;
    }

    /**
     * @param name the client's name that is used for finding the product with the given name
     * @return a client object
     * This method is used to retrieve the object with the given name from database
     * If the client is null, it means that no client has been found and the method will throw an exception
     */
    public Client findByName(String name) {
        Client client = clientDAO.findByName(name);
        if(client == null) {
            throw new NoSuchElementException("Could not find product by name");
        }
        return client;
    }

    /**
     * @return List
     * This method returns a list of client retrieved by the query from database
     * If no data has been found, the method will throw an exception
     */
    public List<Client> findAllClients() {
        List<Client> clients = clientDAO.findAll();
        if(clients == null){
            throw new NoSuchElementException("Could not list clients");
        }
        return clients;
    }

    /**
     * @param client - the client that we want to insert into database
     * This method tries to insert a client into database. If the insert operation is not executed successfully, an exception will be thrown
     *
     */
    public void insert(Client client) {
        int ok =  clientDAO.insert(client);
        if(ok == 0) {
            throw new NoSuchElementException("Could not insert client");
        }
    }

    /**
     * @param id - client's id that we want to delete from database
     * This method tries to delete a client from database. If the delete operation is not executed successfully, an exception will be thrown
     */
    public void delete(int id) {
        int ok = clientDAO.delete(id);
        if(ok == 0) {
            throw new NoSuchElementException("Could not delete client");
        }
    }

    /**
     * @param client the client whose information we want to change
     * @param id client's id
     * This method tries to change some properties of the given object. The object will be searched using its id.
     * If the update operation is not executed successfully, an exception will be thrown
     */
    public void update(Client client, int id) {
        int ok = clientDAO.update(client, id);
        if(ok == 0) {
            throw new NoSuchElementException("Could not update client");
        }
    }
}
