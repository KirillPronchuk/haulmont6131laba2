package dao;

import model.Customer;

/**
 * Created by Admin on 19.12.2016.
 */
public class CustomerDAO extends AbstractDAO<Customer> {
    public CustomerDAO(){
        super(Customer.class);
    }
}
