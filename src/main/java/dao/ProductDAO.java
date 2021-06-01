package dao;

import connection.ConnectionFactory;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/**
 * @author Borza Diana-Cristina
 * Clasa care extinde clasa AbstractDAO si mosteneste metodele implementate de aceasta si impelmenteaza metode specifice obicetelor de tip Produs
 */
public class ProductDAO extends AbstractDAO<Product> {
    /**
     * Metoda care returneaza o lista cu numele si id-ul tuturor produselor idn baza de date
     * @return
     */
    public List<String> getAllProductsName(){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<String> products = new ArrayList<>();
        String query = "SELECT id,productName FROM product";
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                String s = resultSet.getString("id")+" "+resultSet.getString("productName");
                products.add(s);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,  "ProductDAO:findAll " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return products;
    }

    /**
     * Metoda care returneaza pretul unui produs
     * @param id id-ul produsului
     * @return pretul produsului
     */
    public int getProductPrice(int id ){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT price FROM product where id="+id;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            if(resultSet.next()) return Integer.parseInt(resultSet.getString("price"));

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,  "ProductDAO:getProductPrice " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return 0;
    }

    /**
     * Metoda care retuneaza stocul actual al unui produs
     * @param id id-ul produsului
     * @return stocul produsului
     */
    public int getProductStock(int id){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT stock FROM product where id="+id;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            if(resultSet.next())return Integer.parseInt(resultSet.getString("stock"));

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,  "ProductDAO:getProductStock " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return 0;

    }
}
