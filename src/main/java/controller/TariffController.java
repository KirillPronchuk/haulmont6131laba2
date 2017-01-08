package controller;

import model.Tariff;
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
import service.TariffService;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * Created by Kirill on 08.01.2017.
 */
@Controller("/tariffs")
public class TariffController {

    private TariffService tariffService;

    @Autowired
    @Qualifier(value = "tariffService")
    public void setTariffService(TariffService tariffService) {
        this.tariffService = tariffService;
    }


    @RequestMapping(value = "tariffs", method = RequestMethod.GET)
    public String allTariffs(Model model){
        model.addAttribute("tariff", new Tariff());
        model.addAttribute("allTariffs", this.tariffService.getAll());

        return "tariffs";
    }

    @RequestMapping(value = "/tariffs/add", method = RequestMethod.POST)
    public String addTariff(@ModelAttribute("tariff") Tariff tariff,
                           BindingResult result, SessionStatus status,
                           Model model){
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        Set<ConstraintViolation<Tariff>> violations = validator.validate(tariff);

        for (ConstraintViolation<Tariff> violation : violations)
        {
            String propertyPath = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            result.addError(new FieldError("tariffs",propertyPath,message));
        }

        if (result.hasErrors()) {
            model.addAttribute("allTariffs", this.tariffService.getAll());
            return "tariffs";
        }

        if(tariff.getId() == null){
            this.tariffService.add(tariff);
        }else {
            this.tariffService.update(tariff);
        }

        return "redirect:/tariffs";
    }

    @RequestMapping("/tariffs/remove/{id}")
    public String removeTariff(@PathVariable("id") Long id){
        this.tariffService.remove(id);

        return "redirect:/tariffs";
    }

    @RequestMapping("/tariffs/edit/{id}")
    public String editTariff(@PathVariable("id") Long id, Model model){
        model.addAttribute("tariff", this.tariffService.get(id));
        model.addAttribute("allTariffs", this.tariffService.getAll());

        return "tariffs";
    }
}
