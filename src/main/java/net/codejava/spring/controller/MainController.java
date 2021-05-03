package net.codejava.spring.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import net.codejava.spring.dao.ContactDAO;
import net.codejava.spring.model.Contact;

@Controller
public class MainController {
	
	@Autowired
  private ContactDAO contactDAO;

  // handler methods go here...
	@RequestMapping(value="/")
	public ModelAndView listContact(ModelAndView model) throws IOException{
	      List<Contact> listContact = contactDAO.list();
	      model.addObject("listContact", listContact);
	      model.setViewName("index");
	   
	      return model;
	}
	 
  
  	@RequestMapping(value = "/newContact", method = RequestMethod.GET)
	public ModelAndView newContact(ModelAndView model) {
	    Contact newContact = new Contact();
	    model.addObject("contact", newContact);
	    model.setViewName("contactForm");
	    return model;
	}
  	@RequestMapping(value = "/save", method = RequestMethod.POST)
	  public ModelAndView saveContact(@ModelAttribute Contact contact) {
	      contactDAO.saveOrUpdate(contact);
	      return new ModelAndView("redirect:/");
	  }
  	
  	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	  public ModelAndView editContact(HttpServletRequest request) {
	      int contactId = Integer.parseInt(request.getParameter("id"));
	      Contact contact = contactDAO.get(contactId);
	      ModelAndView model = new ModelAndView("contactForm");
	      model.addObject("contact", contact);
	   
	      return model;
	  }
  	
  	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	  public ModelAndView deleteContact(HttpServletRequest request) {
	      int contactId = Integer.parseInt(request.getParameter("id"));
	      contactDAO.delete(contactId);
	      return new ModelAndView("redirect:/");
	  }

}
