package model;

/**
 * @author Borza Diana-Cristina
 *   Clasa ce defineste un obiect de tipul Product care contine atributele specifice tabelei din baza de date specifice unui produs
 *   si care contine constructori si metode de set si get
 */
public class Product {
    private int id;
    private String productName;
    private int price;
    private int stock;
    private float weight;
    private boolean returnable;

    public Product(){}

    public Product(int productID, String productName, int price, int stock, float weight, boolean returnable) {
        this.id = productID;
        this.productName = productName;
        this.price = price;
        this.stock = stock;
        this.weight = weight;
        this.returnable = returnable;
    }

    public Product(String productName, int price, int stock, float weight, boolean returnable) {
        this.productName = productName;
        this.price = price;
        this.stock = stock;
        this.weight = weight;
        this.returnable = returnable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public boolean isReturnable() {
        return returnable;
    }

    public void setReturnable(boolean returnable) {
        this.returnable = returnable;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productID=" + id +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", weight=" + weight +
                ", isReturnable=" + returnable +
                '}';
    }
}
