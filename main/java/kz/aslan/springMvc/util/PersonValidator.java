package kz.aslan.springMvc.util;

import kz.aslan.springMvc.models.Person;
import kz.aslan.springMvc.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
@Component
public class PersonValidator implements Validator {

    private final PeopleService peopleService;

    @Autowired
    public PersonValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        if(peopleService.findOne(person.getEmail())!=null && !peopleService.findOne(person.getEmail()).equals(person)){
            errors.rejectValue("email","","This email already taken!");
        }
    }
}
