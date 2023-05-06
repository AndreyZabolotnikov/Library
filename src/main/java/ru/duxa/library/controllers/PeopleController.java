package ru.duxa.library.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.duxa.library.Util.PersonValidator;
import ru.duxa.library.dao.PersonDAO;
import ru.duxa.library.models.Person;

import javax.validation.Valid;

@Controller
public class PeopleController {

    private final PersonDAO personDAO;
    private final PersonValidator personValidator;

    public PeopleController(PersonDAO personDAO, PersonValidator personValidator) {
        this.personDAO = personDAO;
        this.personValidator = personValidator;
    }


    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("people", personDAO.index());
        return "people/index";
    }

    @GetMapping("/people/new")
    public String addPerson(Model model) {
        model.addAttribute("person", new Person());
        return "/people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors())
            return "/people/new";

        personDAO.save(person);
        return "redirect:/";
    }

    @GetMapping("/people/{id}/edit")
    public String editPerson(Model model, @PathVariable(value = "id")int id) {
        model.addAttribute("person", personDAO.show(id));
        return "/people/edit";
    }

    @PatchMapping("people/{id}")
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult, @PathVariable("id") int id) {
       if(bindingResult.hasErrors())
           return "people/edit";
       personDAO.update(id, person);
       return "redirect:/";
    }

    @DeleteMapping("people/{id}")
    public String delete(@PathVariable("id") int id) {
        personDAO.delete(id);
        return "redirect:/";
    }

    @GetMapping("/people/{id}")
    public String show(Model model,@PathVariable("id") int id){
        model.addAttribute("person", personDAO.show(id));
        model.addAttribute("books", personDAO.getBooksByPersonId(id));
        return "people/person";
    }

}
