package com.isn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.isn.model.Person;
import com.isn.service.PersonService;

import java.util.Map;

@Controller
public class PersonController {

	
	 
    @Autowired
    private PersonService personService;

    @RequestMapping("/")
    public String listPeople(Map<String, Object> map) {
    	
        map.put("person", new Person());
       
        return "people";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addPerson(@ModelAttribute("person") Person person, BindingResult result) {

        personService.addPerson(person);

        return "redirect:/online/";
    }

    @RequestMapping("/delete/{personId}")
    public String deletePerson(@PathVariable("personId") Integer personId) {

        personService.removePerson(personId);

        return "redirect:/online/";
    }
    
    @RequestMapping("/list")// make url as "online/validate"
    public String regisPeople(Map<String, Object> map) {
    	 map.put("peopleList", personService.listPeople());
         return "data";
    }
}
