//find/replace instances of 'java22displaydate' with trueName of project
package com.jonfriend.java59motherchildparttwo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;


import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.jonfriend.java59motherchildparttwo.models.LoginUserMdl;

import com.jonfriend.java59motherchildparttwo.models.TwinoneMdl;
import com.jonfriend.java59motherchildparttwo.models.TwintwoMdl;
import com.jonfriend.java59motherchildparttwo.models.UserMdl;

import com.jonfriend.java59motherchildparttwo.services.TwinoneSrv;
import com.jonfriend.java59motherchildparttwo.services.TwintwoSrv;
import com.jonfriend.java59motherchildparttwo.services.UserSrv;

@Controller
public class HomeController {
	
	@Autowired
	private UserSrv userSrv;
	
	@Autowired
	private TwinoneSrv twinoneSrv;
	
	@Autowired
	private TwintwoSrv twintwoSrv;
	
// ********************************************************************
// AUTHENTICATION METHODS
// ********************************************************************
	
	@GetMapping("/")
	public String index(
			Model model
			, HttpSession session) {
		
		// *** REDIRECT AUTH USERS TO /home METHOD -- DON'T EXPOSE REG/LOGIN index page TO ALREADY AUTH'ED USERS ***
//		if(session.getAttribute("userId") != null) {return "redirect:/home";}

		// login/reg form items: putting a new empty model for reg (and one for login too) on the index page, so user can shove data onto using the form. 
        model.addAttribute("newUser", new UserMdl());
        model.addAttribute("newLogin", new LoginUserMdl());
        
//      Begin: counter stuff
        Integer currentCount = 0; 
        // if session count hasn't started yet, i.e. first visit, set it to '1'
        if (session.getAttribute("count") == null) {
        	session.setAttribute("count", 1);
        // if session already running, get the current count and add '1' to it.
        } else { 
        	currentCount = (Integer) session.getAttribute("count");
        	currentCount ++; 
        	session.setAttribute("count", currentCount); 
        }
        model.addAttribute("countToShow", currentCount);
//        End: counter stuff
        
        System.out.println("Page Display: login/reg"); 
		return "index.jsp"; 
	}

    @PostMapping("/register")
    public String register(
    		@Valid @ModelAttribute("newUser") UserMdl newUser
    		, BindingResult result
    		, Model model
    		, HttpSession session
    		) {
        
    	UserMdl user = userSrv.register(newUser, result);
    	
    	// TO-DO Later -- call a register method in the service 
        // to do some extra validations and create a new user!
        
        if(result.hasErrors()) {
            // Below sends in the empty LoginUser before re-rendering the reg/login page; the reg model will maintain the incoming values to this method
            model.addAttribute("newLogin", new LoginUserMdl());
            return "index.jsp";
        }
        
        // No errors?  Store their ID from the DB in session, log in.
        session.setAttribute("userId", user.getId());
   	 
	    return "redirect:/home";
        // Above is our default dashboard location, which can be embraced if/when the app shall be expanded to have a "dashboard"/"home page".
	    // This "home/dashboard" page can display content from numerous sub apps, as well as links to those apps.  
	    // This general landing page, in other words, helps the app be readily scalable.  
	    // At present, the /home route that we are directing to above will itself redirect to the presently-targeted app, 
	    // thereby side-stepping the multi-faceted dashboard/home page.
	    // When we are ready to have a mult-faceted webapp, just change the redirect on the /home method to instead render the home.jsp page. 
    }
    
    @PostMapping("/login")
    public String login(
    		@Valid @ModelAttribute("newLogin") LoginUserMdl newLogin
    		, BindingResult result
    		, Model model
    		, HttpSession session) {
    	
    	UserMdl user = userSrv.login(newLogin, result);
    	
        if(result.hasErrors() || user==null ) {
            model.addAttribute("newUser", new UserMdl());
            return "index.jsp";
        }
    
        session.setAttribute("userId", user.getId());
   	 
	    return "redirect:/home";
        // see comment in /register method regarding above redirect. Same thing applies.   
    }

     
    @GetMapping("/logout")
	public String logout(HttpSession session) {
		session.setAttribute("userId", null);
	    return "redirect:/";
	}

//********************************************************************
// HOME/PROFILE/ETC METHODS
//********************************************************************
		
	    @GetMapping("/home")
		public String home(
				Model model
				, HttpSession session) {
		 
			// Redirect all non-auth users to /logout method.
			if(session.getAttribute("userId") == null) {return "redirect:/logout";}
			
			// create userId object from session.userid value.  We then use this object to display on page, and use for various validations. 
			Long userId = (Long) session.getAttribute("userId");
			model.addAttribute("user", userSrv.findById(userId));
			
			List<TwinoneMdl> intVar3 = twinoneSrv.returnAll();
			model.addAttribute("twinoneList", intVar3);

			List<TwintwoMdl> intVar4 = twintwoSrv.returnAll();
			model.addAttribute("twintwoList", intVar4);
			
			// JRF 724
			List<UserMdl> intVar5 = userSrv.returnAll();
			model.addAttribute("userList", intVar5);
			
		    return "home.jsp";  
		}

		// view user profile
		@GetMapping("/profile/{id}")
		public String showProfile(
				@PathVariable("id") Long id
				, Model model
				, HttpSession session
				) {
			
			// If no userId is found in session, redirect to logout.  JRF: put this on basically all methods now, except the login/reg pages
			if(session.getAttribute("userId") == null) {return "redirect:/logout";}
			
			// We get the userId from our session (we need to cast the result to a Long as the 'session.getAttribute("userId")' returns an object
			Long userId = (Long) session.getAttribute("userId");
			model.addAttribute("user", userSrv.findById(userId));
			
			UserMdl intVar = userSrv.findById(id);
			
			model.addAttribute("userProfile", intVar);
//			model.addAttribute("assignedCategories", twintwoSrv.getAssignedTwinones(intVar));
//			model.addAttribute("unassignedCategories", twintwoSrv.getUnassignedTwinones(intVar));
			
			return "profile/record.jsp";
		}
// end of methods
}
