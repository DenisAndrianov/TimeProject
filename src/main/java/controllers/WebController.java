package controllers;


import models.Offer;
import models.OfferForm;
import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;
import services.OfferService;
import services.UserService;

import javax.servlet.ServletContext;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Controller
@RequestMapping("/")
public class WebController {
    @Autowired
    OfferService offerService;

    @Autowired
    UserService userService;


    @GetMapping(path = "/")
    public String index ()  {
        return "index";
    }

    @GetMapping(path = "/registration")
    public String getRegistration (Model model)  {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping(path = "/registration")
    public String postRegistration (Model model, @Valid final User user, final BindingResult result)  {
        if (result.hasErrors()){
            return "registration";
        }
        if (userService.save(user).equals("Error")) {
            result.addError(new ObjectError("login","логин уже существует"));
        }   else    {
            result.addError(new ObjectError("login","Successfully"));
        }
        return "registration";
    }

    @GetMapping(path = "/login")
    public String getLogin (Model model, HttpServletRequest request)  {
        if(userService.existUserByToken(request.getCookies()[0].getValue()))    {
            return "redirect:/home";
        }
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping(path = "/login")
    public String postLogin (Model model, final User user, final BindingResult result, HttpServletResponse response)  {
        String token = userService.authentication(user);
        if (token.equals("Error"))  {
            result.addError(new ObjectError("error","связка логин пароль не найдена"));
            return "login";
        }

        response.addCookie(new Cookie("token",token));
        return "redirect:/home";
    }

    @GetMapping (path = "home")
    public String getHome (Model model, HttpServletRequest request)    {
        Cookie cookie = request.getCookies()[0];
        model.addAttribute("user", userService.getUserByToken(cookie.getValue()));
        return "home";
    }

    @PostMapping (path = "home")
    public String postHome (Model model, @Valid User user, final BindingResult result, HttpServletRequest request)    {
        if (result.hasErrors()){
            return "home";
        }
        if (userService.save(user).equals("Error")) {
            result.addError(new ObjectError("login","Error"));
        }   else    {
            result.addError(new ObjectError("login","Successfully"));
        }
        return "home";
    }

    @GetMapping(path = "/offerreg")
    public String getOfferReg (Model model)  {
        model.addAttribute("offerForm", new OfferForm());
        return "offerreg";
    }

    @PostMapping(path = "/offerreg")
    public String postOfferReg (Model model, @Valid final OfferForm offerForm, final BindingResult result, HttpServletRequest request)  {
        if (result.hasErrors()){
            return "offerreg";
        }
        offerForm.setOwner(userService.getUserByToken(request.getCookies()[0].getValue()));
        if (offerService.registration(offerForm).equals("Error")) {
            result.addError(new ObjectError("login","ошибка"));
        }   else    {
            result.addError(new ObjectError("login","Successfully"));
        }
        return "offerreg";
    }

    @GetMapping(path = "/offerslist")
    public String getOffersList (Model model, HttpServletRequest request)   {
        model.addAttribute("map",offerService.getAllOffersByUser(userService.getUserByToken(request.getCookies()[0].getValue())));
        return "/offerslist";
    }

    


}
