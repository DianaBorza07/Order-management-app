package dao;

import connection.ConnectionFactory;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Borza Diana-Cristina
 * Clasa care contine metodele pentru accesul la baza de date pentru obiecte de tip T folosind metoda reflexiei
 * @param <T>
 */
public abstract class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    private final Class<T> type;

    /**
     * Constructorul clasei
     */
    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * Metoda care creeaza un string sub forma unui query SQL pentru selectia obiectelor in functie de un anumit criteriu
     * @param field criteriu
     * @return
     */
    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append("`"+type.getSimpleName()+"`");
        sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }

    /**
     * Metoda care returneaza o lista cu toate obiectele de tipul T din baza de date
     * @return
     */
    public List<Object> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM "+"`"+type.getSimpleName()+"`";
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            return (List<Object>) createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * Metoda care returneaza un obiect de tipul T din baz de date cu un id specificat
     * @param id id-ul obiectului cautat
     * @return obiectul
     */
    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * Metoda care creaza o lista de obiecte de tipul T dupa ce acestea au fost preluate din baza de date
     * @param resultSet rezultatele preluate in urma interogarii
     * @return lista de obiecte de tipul T
     */
    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0) break; }
        try { while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = (T)ctor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value); }
                list.add(instance); }
        } catch (InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace(); }
        return list;
    }

    /**
     * Metoda care reurneaza field-urile unei clase(tabel) fara id-ul tabelului sub forma (field1,..,fieldn)
     * @return string-ul cu field-urile
     */
    private String getClassFieldsWithoutID(){
        String s = "(";
        for (Field f:type.getDeclaredFields()
             ) {if(!f.getName().equals("id")) {
            s += f.getName().toString();
            s += ","; }
        }
        s = s.substring(0,s.length()-1); // delete the last ','
        s += ")";
        return s;
    }

    /**
     * Metoda care insereaza un obiect de tipul T in baza de date
     * @param t obiectul ce va fi inserat
     * @return
     */
    public int insert(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = "INSERT INTO "+"`"+type.getSimpleName()+"`"+getClassFieldsWithoutID();
        if(type.getSimpleName().equals("Product")||type.getSimpleName().equals("Order"))
            query +="VALUES (?,?,?,?,?)";
        else  query += "VALUES (?,?,?,?)";
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            int idx=1;
            for (Field f: type.getDeclaredFields()
                 ) {if(!f.getName().equals("id")){
                f.setAccessible(true);
                statement.setObject(idx,f.get(t));
                 idx++;}
            }
            int result = statement.executeUpdate();
            return result;
        } catch (SQLException | IllegalAccessException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:insert" + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return 0;
    }

    /**
     * Metoda care creeaza un query pentru update a unui obiect din baza de date
     * @param id id-ul obiectului pe care dorim sa il actualizam
     * @return string-ul cu interogarea
     */
    private String createUpdateString(int id){
        StringBuilder query = new StringBuilder();
        query.append("UPDATE "+"`"+type.getSimpleName()+"`"+"SET ");
        for (Field f:type.getDeclaredFields()
             ) { if(!f.getName().equals("id"))
                 query.append(f.getName()+"=? ,");
        }
        String s = query.substring(0,query.length()-1);
        s = s + "WHERE id="+id;
        return s;
    }

    /**
     * Metoda ce realizeaza actualizarea unui obiect de tipul T in baza de date
     * @param t noile atribute ale obiectului
     * @param id id-ul obiectului
     * @return
     */
    public int update(T t, int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createUpdateString(id);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            int idx=1;
            for (Field f: type.getDeclaredFields()
            ) {if(!f.getName().equals("id")){
                f.setAccessible(true);
                statement.setObject(idx,f.get(t));
                idx++;}
            }
            int result = statement.executeUpdate();
            return result;
        } catch (SQLException | IllegalAccessException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:update" + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return 0;
    }

    /**
     * Metoda care strege un obiect de tipul T din baza de date
     * @param id id-ul obiectului ce va fi sters
     * @return
     */
    public int delete(int id){
        Connection connection = null;
        PreparedStatement statement = null;
        String query = "DELETE FROM "+"`"+type.getSimpleName()+"`"+"WHERE id="+id;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            int result = statement.executeUpdate();
            return result;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:delete " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return 0;
    }
}
