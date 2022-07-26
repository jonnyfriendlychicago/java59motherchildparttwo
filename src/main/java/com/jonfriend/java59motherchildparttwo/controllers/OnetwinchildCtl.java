package com.jonfriend.java59motherchildparttwo.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.jonfriend.java59motherchildparttwo.models.OnetwinchildMdl;
import com.jonfriend.java59motherchildparttwo.services.OnetwinchildSrv;
import com.jonfriend.java59motherchildparttwo.services.UserSrv;

@Controller
//public class OnetwinchildCtl {
public class OnetwinchildCtl {

	@Autowired
	private OnetwinchildSrv onetwinchildSrv;
//	
//	@Autowired
//	private TwintwoSrv twintwoSrv;
	
	@Autowired
	private UserSrv userSrv;
	
	// display create-new page
	@GetMapping("/onetwinchild/new")
	public String newOnetwinchild(
			@ModelAttribute("onetwinchild") OnetwinchildMdl onetwinchildMdl
			, Model model
			, HttpSession session
			) {
		 
		// If no userId is found in session, redirect to logout.  JRF: put this on basically all methods now, except the login/reg pages
		if(session.getAttribute("userId") == null) {return "redirect:/logout";}
		
		// We get the userId from our session (we need to cast the result to a Long as the 'session.getAttribute("userId")' returns an object
		Long userId = (Long) session.getAttribute("userId");
		model.addAttribute("user", userSrv.findById(userId));
		
		return "onetwinchild/create.jsp";
	}
	 
	// process the create-new  
	@PostMapping("/onetwinchild/new")
	public String addNewOnetwinchild(
			@Valid @ModelAttribute("onetwinchild") OnetwinchildMdl onetwinchildMdl
			, BindingResult result
			, Model model
			, HttpSession session
			) {
		
		// If no userId is found in session, redirect to logout.  JRF: put this on basically all methods now, except the login/reg pages
		if(session.getAttribute("userId") == null) {return "redirect:/logout";}
		
		// We get the userId from our session (we need to cast the result to a Long as the 'session.getAttribute("userId")' returns an object
		Long userId = (Long) session.getAttribute("userId");
		model.addAttribute("user", userSrv.findById(userId));
		
		if(result.hasErrors()) {
			return "onetwinchild/create.jsp";
		}else {
			onetwinchildSrv.create(onetwinchildMdl);
			return "redirect:/home";
		}
	}
	
	// view record
	@GetMapping("/onetwinchild/{id}")
	public String showOnetwinchild(
			@PathVariable("id") Long id
			, Model model
			, HttpSession session
			) {
		
		// If no userId is found in session, redirect to logout.  JRF: put this on basically all methods now, except the login/reg pages
		if(session.getAttribute("userId") == null) {return "redirect:/logout";}
		
		// We get the userId from our session (we need to cast the result to a Long as the 'session.getAttribute("userId")' returns an object
		Long userId = (Long) session.getAttribute("userId");
		model.addAttribute("user", userSrv.findById(userId));
		
		OnetwinchildMdl intVar = onetwinchildSrv.findById(id);
		
		model.addAttribute("onetwinchild", intVar);
//		model.addAttribute("assignedCategories", twintwoSrv.getAssignedOnetwinchilds(intVar));
//		model.addAttribute("unassignedCategories", twintwoSrv.getUnassignedOnetwinchilds(intVar));
		
		return "onetwinchild/record.jsp";
	}
	
	// display edit page
	@GetMapping("/onetwinchild/{id}/edit")
	public String editOnetwinchild(
			@PathVariable("id") Long onetwinchildId
			, Model model
			, HttpSession session
			) {
		
		// If no userId is found in session, redirect to logout.  JRF: put this on basically all methods now, except the login/reg pages
		if(session.getAttribute("userId") == null) {return "redirect:/logout";}

		// We get the userId from our session (we need to cast the result to a Long as the 'session.getAttribute("userId")' returns an object
		Long userId = (Long) session.getAttribute("userId");
		model.addAttribute("user", userSrv.findById(userId));
		
		// pre-populates the values in the management interface
		OnetwinchildMdl intVar = onetwinchildSrv.findById(onetwinchildId);
		
		model.addAttribute("onetwinchild", intVar);
//		model.addAttribute("assignedCategories", twintwoSrv.getAssignedOnetwinchilds(intVar));
//		model.addAttribute("unassignedCategories", twintwoSrv.getUnassignedOnetwinchilds(intVar));
		
		// records in 'manage-one' interface dropdown
//		List<DojoMdl> intVar3 = dojoSrvIntVar.returnAll();
//		model.addAttribute("dojoList", intVar3); 
		
		return "onetwinchild/edit.jsp";
	}
	
	// process the edit(s)
//	@PostMapping("/onetwinchild/{id}/edit")
	// line above replaced by below.  we used to include the path variable id of the record, but we've side stepped that with the ModAtt approach.
	@PostMapping("/onetwinchild/edit")
	public String PostTheEditOnetwinchild(
			@Valid 
			@ModelAttribute("onetwinchild") OnetwinchildMdl onetwinchildMdl 
			, BindingResult result
			, Model model
//			, @PathVariable("id") Long onetwinchildId
			// above no longer needed, b/c we are using modAtt approach.
			, HttpSession session
			, RedirectAttributes redirectAttributes
			) {
		
		// If no userId is found in session, redirect to logout.  JRF: put this on basically all methods now, except the login/reg pages
		if(session.getAttribute("userId") == null) {return "redirect:/logout";}
		
		// trying here to stop someone from forcing this method when not creator; was working, now no idea.... sigh 7/19 2pm
//		 Long userId = (Long) session.getAttribute("userId"); 
//		 PublicationMdl intVar = onetwinchildSrv.findById(onetwinchildId);
		
		// System.out.println("in the postMapping for edit..."); 
		// System.out.println("intVar.getUserMdl().getId(): " + intVar.getUserMdl().getId()); 
		// System.out.println("userId: " + userId); 
		
//		if(intVar.getUserMdl().getId() != userId) {
//			redirectAttributes.addFlashAttribute("mgmtPermissionErrorMsg", "Only the creator of a record can edit it.");
//			return "redirect:/publication";
//		}
		
		// below now setting up intVar object by using the getID on the modAtt thing. 
		OnetwinchildMdl intVar = onetwinchildSrv.findById(onetwinchildMdl.getId());
		
		if (result.hasErrors()) { 
			
            Long userId = (Long) session.getAttribute("userId");
            model.addAttribute("user", userSrv.findById(userId));            
//            model.addAttribute("onetwinchild", intVar);
//            model.addAttribute("assignedCategories", twintwoSrv.getAssignedOnetwinchilds(intVar));
//            model.addAttribute("unassignedCategories", twintwoSrv.getUnassignedOnetwinchilds(intVar));

			return "onetwinchild/edit.jsp";
		} else {
			
			// this merely returns the joined twintwo records list
//			onetwinchildMdl.setTwintwoMdl(twintwoSrv.getAssignedOnetwinchilds(intVar));
			
//			onetwinchildMdl.setUserMdl(intVar.getUserMdl());
			// translation of line above: we are reSETTING on the onetwinchild model object/record the createbyid to that which is GETTING the creatingbyid from the DB... NO LONGER from that silly hidden input. 

			// translation of above: we are always setting/resetting/maintaining the parent record id. 
			onetwinchildMdl.setTwinoneMdl(intVar.getTwinoneMdl());
			
			onetwinchildSrv.update(onetwinchildMdl);
			
			// below now setting up intVar object by using the getID on the modAtt thing. 
			return "redirect:/onetwinchild/" + intVar.getId();
		}
	}
//					
//					// process new joins for that one onetwinchild
//					@PostMapping("/onetwinchild/{id}/editTwintwoJoins")
//					public String postOnetwinchildTwintwoJoin(
//				//			@PathVariable("id") Long id
//							@PathVariable("id") Long onetwinchildId
//							, @RequestParam(value="twintwoId") Long twintwoId // requestParam is only used with regular HTML form 
//							,  Model model
//							, HttpSession session
//							) {
//						
//						// If no userId is found in session, redirect to logout.  JRF: put this on basically all methods now, except the login/reg pages
//						if(session.getAttribute("userId") == null) {return "redirect:/logout";}
//						
//						// We get the userId from our session (we need to cast the result to a Long as the 'session.getAttribute("userId")' returns an object
//						Long userId = (Long) session.getAttribute("userId");
//						model.addAttribute("user", userSrv.findById(userId));
//						
//						OnetwinchildMdl onetwinchild = onetwinchildSrv.findById(onetwinchildId);
//						TwintwoMdl twintwo = twintwoSrv.findById(twintwoId);
//						
//						onetwinchild.getTwintwoMdl().add(twintwo);
//						
//						onetwinchildSrv.update(onetwinchild);
//						
//						// need these two below so that the returned page has this dropdown/table info populated.
//						model.addAttribute("assignedCategories", twintwoSrv.getAssignedOnetwinchilds(onetwinchild));
//						model.addAttribute("unassignedCategories", twintwoSrv.getUnassignedOnetwinchilds(onetwinchild));
//				//		return "redirect:/onetwinchild/" + id;
//						return "redirect:/onetwinchild/" + onetwinchildId + "/edit";
//					}
//					
//					@DeleteMapping("/removeOnetwinchildTwintwoJoin")
//				    public String removeOnetwinchildTwintwoJoin(
//				    		@RequestParam(value="twintwoId") Long twintwoId // requestParam is only used with regular HTML form
//				    		, @RequestParam(value="onetwinchildId") Long onetwinchildId // requestParam is only used with regular HTML form
//				    		// below removed, outmoded design
//				 //    		, @RequestParam(value="origin") Long originPath // requestParam is only used with regular HTML form
//				    		, HttpSession session
//				    		, RedirectAttributes redirectAttributes
//				    		) {
//				
//				    	// If no userId is found in session, redirect to logout.  JRF: put this on basically all methods now, except the login/reg pages
//						if(session.getAttribute("userId") == null) {return "redirect:/logout";}
//						
//						OnetwinchildMdl onetwinchildObject = onetwinchildSrv.findById(onetwinchildId);
//						TwintwoMdl twintwoObject  = twintwoSrv.findById(twintwoId);
//						
//						onetwinchildSrv.removeOnetwinchildTwintwoJoin(twintwoObject, onetwinchildObject); 
//				
//				//		if (originPath == 1) {
//				//			return "redirect:/onetwinchild/" + onetwinchildId + "/edit";
//				//		} else {
//				//			return "redirect:/twintwo/" + twintwoId;
//				//		}
//						
//						// above replaced by below, above design outdated.
//						
//						return "redirect:/onetwinchild/" + onetwinchildId + "/edit";
//					}
	
	// delete onetwinchild
    @DeleteMapping("/onetwinchild/{id}")
    public String deleteOnetwinchild(
    		@PathVariable("id") Long onetwinchildId
    		, HttpSession session
    		, RedirectAttributes redirectAttributes
    		) {
		// If no userId is found in session, redirect to logout.  JRF: put this on basically all methods now, except the login/reg pages
		if(session.getAttribute("userId") == null) {return "redirect:/logout";}
		
		// trying here to stop someone from forcing this method when not creator
		Long userId = (Long) session.getAttribute("userId"); 
		OnetwinchildMdl intVar = onetwinchildSrv.findById(onetwinchildId);
		
		// below is to prevent non-creator from deleting record
//		if(intVar.getUserMdl().getId() != userId) {
//			redirectAttributes.addFlashAttribute("mgmtPermissionErrorMsg", "Only the creator of a record can delete it.");
//			return "redirect:/publication";
//		}

		onetwinchildSrv.delete(intVar);
        return "redirect:/home";
    }
	

// end of ctl
}
