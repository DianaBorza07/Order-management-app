package model;

/**
 * @author Borza Diana-Cristina
 * Clasa ce defineste un obiect de tipul Order care contine atributele specifice tabelei din baza de date specifice unei comenzi
 * si care contine constructori si metode de set si get
 */
public class Order {
    private int id;
    private int productID;
    private int productPrice;
    private int productQuantity;
    private int clientID;
    private int totalPrice;

    public Order(){}

    public Order(int productID, int productPrice, int clientID, int productQuantity) {
        this.productID = productID;
        this.productPrice = productPrice;
        this.clientID = clientID;
        this.productQuantity = productQuantity;
    }

    public Order(int orderID, int productID, int productPrice, int clientID, int productQuantity) {
        this.id = orderID;
        this.productID = productID;
        this.productPrice = productPrice;
        this.clientID = clientID;
        this.productQuantity = productQuantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void generateTotalPrice(){
        this.totalPrice = this.productPrice * this.productQuantity;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
