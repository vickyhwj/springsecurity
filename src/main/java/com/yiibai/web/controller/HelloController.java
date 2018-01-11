package com.yiibai.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import model.User;
import securitysecurityservice.UserService;

@Controller
public class HelloController {
    @Autowired
    UserService userService;

	@RequestMapping(value = { "/yy", "/welcome**" }, method = RequestMethod.GET)
	public ModelAndView welcomePage() {

		final ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security Hello World");
		model.addObject("message", "This is welcome page!");
		model.setViewName("hello");
		return model;

	}

	@RequestMapping(value = "/admin**", method = RequestMethod.GET)
	public ModelAndView adminPage() {

		final ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security Hello World");
		model.addObject("message", "This is protected page!");
		model.setViewName("admin");

		final User user=new User();
		user.setId(88L);
		user.setName("jenny");
		userService.addUser(user);
		return model;

	}


	@RequestMapping(value = "/delUser", method = RequestMethod.GET)
    public ModelAndView delUser() {

        final ModelAndView model = new ModelAndView();
        model.addObject("title", "deluser");
        model.setViewName("admin");

        final User user=new User();
        user.setId(88L);
        user.setName("jenny");
        userService.delUser(user);
        return model;

    }
	@RequestMapping("/outlogin")
	public void outLogin(final HttpServletRequest request,final HttpServletResponse response){

	}

}