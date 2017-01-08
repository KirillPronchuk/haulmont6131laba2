package controller;

import model.Order;
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
import service.OrderService;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * Created by Kirill on 08.01.2017.
 */
@Controller("/orders")
public class OrderController {

    private OrderService orderService;

    @Autowired
    @Qualifier(value = "orderService")
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }


    @RequestMapping(value = "orders", method = RequestMethod.GET)
    public String allOrders(Model model){
        model.addAttribute("orders", new Order());
        model.addAttribute("allOrders", this.orderService.getAll());

        return "orders";
    }

    @RequestMapping(value = "/orders/add", method = RequestMethod.POST)
    public String addOrder(@ModelAttribute("order") Order order,
                              BindingResult result, SessionStatus status,
                              Model model){
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        Set<ConstraintViolation<Order>> violations = validator.validate(order);

        for (ConstraintViolation<Order> violation : violations)
        {
            String propertyPath = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            result.addError(new FieldError("orders",propertyPath,message));
        }

        if (result.hasErrors()) {
            model.addAttribute("allOrders", this.orderService.getAll());
            return "orders";
        }

        if(order.getId() == null){
            this.orderService.add(order);
        }else {
            this.orderService.update(order);
        }

        return "redirect:/orders";
    }

    @RequestMapping("/orders/remove/{id}")
    public String removeOrder(@PathVariable("id") Long id){
        this.orderService.remove(id);

        return "redirect:/orders";
    }

    @RequestMapping("/orders/edit/{id}")
    public String editOrder(@PathVariable("id") Long id, Model model){
        model.addAttribute("order", this.orderService.get(id));
        model.addAttribute("allOrders", this.orderService.getAll());

        return "orders";
    }
}
