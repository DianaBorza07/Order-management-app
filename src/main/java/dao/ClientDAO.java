package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;
import model.Client;

/**
 * @author Borza Diana-Cristina
 * Clasa care extinde AbstractDAO si implementeaza toate metodele definite de aceasta clasa, dar si metode specifice clientului
 */
public class ClientDAO extends AbstractDAO<Client> {
    /**
     * Metoda care returneaza o lista cu numele si id-ul tuturor clientilor din baza de date
     * @return
     */
    public List<String> getAllClientsName(){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<String> clients = new ArrayList<>();
        String query = "SELECT id,clientName FROM `client`";
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                String s = resultSet.getString("id")+" "+resultSet.getString("clientName");
                clients.add(s);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,  "ClientDAO:findAll " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return clients;
    }
}
