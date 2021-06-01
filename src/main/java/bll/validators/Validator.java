package bll.validators;

/**
 * @author Borza Diana-Cristina
 * Interfata Validator
 */
public interface Validator<T> {

    public void validate(T t);
}