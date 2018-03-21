package jbr.springmvc.controller;

import jbr.springmvc.service.UserService;
import jbr.springmvc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Registration form using HttpServletRequest
 */
@Controller
public class RegistrationController {
    private final UserService userService;

    // we tell Spring Framework: hey find the correct match for this specific type and autowire it in.
    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView showRegister(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("register");
        mav.addObject("user", new User());

        return mav;
    }

    @RequestMapping(value = "/registerProcess", method = RequestMethod.POST)
    public ModelAndView addUser(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("user") User user) {
        userService.register(user);
        ModelAndView mav = null;

        if (null != user) {
            mav = new ModelAndView("welcome");
            mav.addObject("username", user.getUsername());
        } else {
            mav = new ModelAndView("register");
            mav.addObject("message", "Username/Password is wrong!");
        }

        assert user != null;
        return new ModelAndView("welcome", "username", user.getUsername());
    }
}
