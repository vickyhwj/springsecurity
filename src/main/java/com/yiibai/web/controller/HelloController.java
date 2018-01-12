package com.yiibai.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionInformation;
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
	public ModelAndView adminPage(final HttpSession session) {

		final ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security Hello World");
		model.addObject("message", "This is protected page!");
		model.setViewName("admin");

		final List<SessionInformation> list=userService.getSessionRegistry().getAllSessions(SecurityContextHolder.getContext().getAuthentication().getPrincipal(),false);
		for(final SessionInformation si:list){
		    si.expireNow();
		}
		userService.getSessionRegistry().registerNewSession(session.getId(),SecurityContextHolder.getContext().getAuthentication().getPrincipal());

	/*	final User user=new User();
		user.setId(88L);
		user.setName("jenny");
		userService.addUser(user);*/
		return model;

	}
//

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
	@RequestMapping("/getUserDetail")
	public void getUserDetail(final HttpServletRequest request,final HttpServletResponse response) throws IOException{
	    userService.getUserDetail();
	    response.getWriter().print("ok");
	}
	@RequestMapping("/outlogin")
	public void outLogin(final HttpServletRequest request,final HttpServletResponse response){

	}

}