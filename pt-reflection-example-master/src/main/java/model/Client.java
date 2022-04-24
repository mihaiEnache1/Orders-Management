package model;

public class Client {
    private int id;
    private String name;
    private String address;
    private String email;

    /**
     * This is a constructor used in the AbstractDAO to initialize a generic object
     */
    public Client() {
    }

    /**
     * @param id which represents the client's id
     * @param name which represents the client's name
     * @param address which represents the client's address
     * @param email which represents the client's email
     */
    public Client(int id, String name, String address, String email) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
    }

    /**
     * @param name which represents the client's name
     * @param address which represents the client's address
     * @param email which represents the client's email
     */
    public Client(String name, String address, String email) {
        this.name = name;
        this.address = address;
        this.email = email;
    }

    /**
     * @return int which represents the client's id value
     */
    public int getId() {
        return id;
    }

    /**
     * @param id which represents the new client's id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return String which represents the client's name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name which represents the new client's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return String which represents the client's address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address which represents the new client's address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return String which represents the client's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email which represents the new client's email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
