package bll.validators;

import model.Client;

import java.util.regex.Pattern;

/**
 * @author Borza Diana-Cristina
 * Clasa care verifica daca numarul de telefon al unui client respecta formatul de nr de telefon
 */

public class PhoneNumberValidator implements Validator<Client>{
    public static String PHONE_PATTERN ="(\\d){10}";
    @Override
    /**
     * Functie de validare a numarului de telefon
     * @param client Clientul al carui numar de telefon se verifica
     */
    public void validate(Client client) {
        Pattern pattern = Pattern.compile(PHONE_PATTERN);
        if (!pattern.matcher(client.getPhoneNumber()).matches()) {
            throw new IllegalArgumentException("Phone number is not a valid phone number!");
        }
    }
}
