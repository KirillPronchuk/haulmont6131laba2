package dao;

import model.Order;

/**
 * Created by Admin on 19.12.2016.
 */
public class OrderDAO extends AbstractDAO<Order> {
    public OrderDAO(){
        super(Order.class);
    }
}
