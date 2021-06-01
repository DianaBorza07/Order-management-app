package bll;

import dao.OrderDAO;
import model.Order;

import java.util.List;

/**
 * @author Borza Diana-Cristina
 */
public class OrderBLL {
    private OrderDAO order;

    /**
     * Constructorul specific clasei
     */
    public OrderBLL(){
        order = new OrderDAO();
    }

    /**
     * Metoda care insereaza o noua comanda in baza de date
     * @param order comanda pe care dorim sa o plasam
     * @return
     */
    public int createOrder(Order order){
        int inserted = this.order.insert(order);
        if(!(inserted >0))
            try {
                throw new Exception("The order was not created!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        return inserted;
    }

    /**
     * Metoda care returneaza o lista cu toate comenzile aflate in baza de date
     * @return
     */
    public List<Object> getOrdersList(){
        List<Object> orders = order.findAll();
        if(orders.size()==0 || orders==null)
            try {
                throw new Exception("Order list is empty!");
            } catch (Exception e) {
                e.printStackTrace();
            }
       return orders;
    }

}
