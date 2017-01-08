package controller;

import model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import service.CustomerService;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * Created by Kirill on 08.01.2017.
 */
@Controller("/customers")
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    @Qualifier(value = "customerService")
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping(value = "customers", method = RequestMethod.GET)
    public String allCustomers(Model model){
        model.addAttribute("customers", new Customer());
        model.addAttribute("allCustomers", this.customerService.getAll());

        return "projects";
    }

    @RequestMapping(value = "/customers/add", method = RequestMethod.POST)
    public String addCustomer(@ModelAttribute("customer") Customer customer,
                             BindingResult result, SessionStatus status,
                             Model model){
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);

        for (ConstraintViolation<Customer> violation : violations)
        {
            String propertyPath = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            result.addError(new FieldError("customers",propertyPath,message));
        }

        if (result.hasErrors()) {
            model.addAttribute("allCustomers", this.customerService.getAll());
            return "customers";
        }

        if(customer.getId() == null){
            this.customerService.add(customer);
        }else {
            this.customerService.update(customer);
        }

        return "redirect:/customers";
    }

    @RequestMapping("/customers/remove/{id}")
    public String removeCustomer(@PathVariable("id") Long id){
        this.customerService.remove(id);

        return "redirect:/customers";
    }

    @RequestMapping("/customers/edit/{id}")
    public String editCustomer(@PathVariable("id") Long id, Model model){
        model.addAttribute("customers", this.customerService.get(id));
        model.addAttribute("allCustomers", this.customerService.getAll());

        return "customers";
    }
}
