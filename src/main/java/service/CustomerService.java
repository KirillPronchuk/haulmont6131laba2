package service;

import dao.AbstractDAO;
import dao.CustomerDAO;
import model.Customer;
import org.springframework.stereotype.Service;

/**
 * Created by Kirill on 07.01.2017.
 */
@Service
public class CustomerService extends AbstractService<Customer> {
    public CustomerService(){
        super();
    }
}
