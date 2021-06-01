package bll.validators;

import model.Product;

/**
 * @author Borza Diana-Cristina
 * Clasa care valideaza pretul unui produs (numar pozitiv)
 */
public class PriceValidator implements Validator<Product> {
    /**
     * Functie de validare a pretului  unui produs
     * @param product produsul al carei pret este validat
     */
    @Override
    public void validate(Product product) {
        if(product.getPrice()<0)
            throw new IllegalArgumentException("Price is not a valid price!");
    }
}
