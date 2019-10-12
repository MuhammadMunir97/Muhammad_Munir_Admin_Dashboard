package com.muhammad.admin_dashboard.controllers;

import java.security.Principal;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.muhammad.admin_dashboard.entities.User;
import com.muhammad.admin_dashboard.services.UserService;
import com.muhammad.admin_dashboard.validations.UserValidator;

@Controller
public class UsersController {
	
	private final UserService userService;
	
	private UserValidator userValidator;
    
    // NEW
    public UsersController(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }

	@RequestMapping("/registration")
    public String registerForm(@Valid @ModelAttribute("user") User user) {
        return "/view/registrationPage.jsp";
    }
    
    @PostMapping("/registration")
    public String registration(@Valid @ModelAttribute("user") User user, BindingResult result, Model model, HttpSession session) {
    	userValidator.validate(user, result);
    	if (result.hasErrors()) {
            return "/view/registrationPage.jsp";
        }
    	userService.saveUserWithAdminRolecopy(user);
        return "redirect:/login";
    }
    
    @RequestMapping("/login")
    public String login(@RequestParam(value="error", required=false) String error, @RequestParam(value="logout", required=false) String logout, Model model) {
        if(error != null) {
            model.addAttribute("errorMessage", "Invalid Credentials, Please try again.");
        }
        if(logout != null) {
            model.addAttribute("logoutMessage", "Logout Successful!");
        }
        return "/view/loginPage.jsp";
    }
    @RequestMapping(value = {"/", "/home"})
    public String home(Principal principal, Model model) {
        String username = principal.getName();
        model.addAttribute("currentUser", userService.findByUsername(username));
        return "/view/homePage.jsp";
    }
    
    @RequestMapping("/admin")
    public String adminPage(Principal principal, Model model) {
        String username = principal.getName();
        model.addAttribute("currentUser", userService.findByUsername(username));
        return "/view/adminPage.jsp";
    }
}
