package bll;

import bll.validators.PriceValidator;
import bll.validators.Validator;
import dao.ProductDAO;
import model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author Borza Diana-Cristina
 * Clasa ce contine metodele specifice obiectelor de tip produs
 */
public class ProductBLL {
    private List<Validator<Product>> validatorList;
    private ProductDAO product;

    /**
     * Constructorul specific clasei
     */
    public ProductBLL(){
        validatorList = new ArrayList<>();
        validatorList.add(new PriceValidator());
        product = new ProductDAO();
    }

    /**
     * Metoda care gaseste un produs cu un id specificat
     * @param id id-ul produsului cautat
     * @return produsul cautat
     */
    public Product findProductWithSpecifiedID(int id){
        Product foundProduct = product.findById(id);
        if( foundProduct== null)
            throw new NoSuchElementException("The product with the id "+id+" was not found!");
        return foundProduct;
    }

    /**
     * Metoda care sterge un produs specificat din baza de date
     * @param id id-ul produsului pe care dorim sa il stergem
     * @return
     */
    public int deleteProductWithSpecifiedID(int id){
        int isDeleted = product.delete(id);
        if(!(isDeleted>0))
            try {
                throw new Exception("The product with id "+id+" was not deleted!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        return isDeleted;
    }

    /**
     * Metoda care modifica atributele unui produs din baza de date
     * @param product produsul cu noile atribute
     * @param id id-ul produsului pe care dorim sa il editam
     * @return
     */
    public int editProductWithSpecifiedId(Product product,int id){
        int isEdited = this.product.update(product,id);
        if(!(isEdited>0))
            try {
                throw new Exception("The product with id "+id+" was not edited!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        return isEdited;
    }

    /**
     * Metoda care insereaza un produs in baza de date
     * @param product produsul pe care doim sa il inseram
     * @return
     */
    public int insertProduct(Product product){
        int var = this.product.insert(product);
        if(!(var>0))
            try {
                throw new Exception("The product was not inserted!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        return var;
    }

    /**
     * Metoda care returneaza o lista cu toate produsele aflate in baza de date
     * @return
     */
    public List<Object> getProductsList(){
        List<Object> products = product.findAll();
        if(products.size()==0 || products==null)
            throw new NoSuchElementException("The list of products is empty!");
        return products;
    }

    /**
     * Metoda care retuneaza o lista cu numele tuturor produselor
     * @return
     */
    public List<String> getProductsName(){
        List<String>  products = product.getAllProductsName();
        if(products.size()==0 || products==null)
            throw new NoSuchElementException("The list of products is empty!");
        return products;
    }

    /**
     * Metoda care returneaza pretul unui produs specificat
     * @param id id-ul produsului al carui pret dorim sa il aflam
     * @return pretul produsului
     */
    public int getProductPrice(int id){
        int price = product.getProductPrice(id);
        return price;
    }

    /**
     * Metoda care returneaza stocul existent al unui produs din baza de date
     * @param id id-ul produsului
     * @return stocul existent
     */
    public int getProductStock(int id){
        int stock = product.getProductStock(id);
        return stock;
    }
}
