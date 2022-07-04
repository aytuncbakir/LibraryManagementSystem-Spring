package com.aytuncbakir.lms.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aytuncbakir.lms.command.LoginCommand;
import com.aytuncbakir.lms.command.UserCommand;
import com.aytuncbakir.lms.dao.UserDAO;
import com.aytuncbakir.lms.domain.Book;
import com.aytuncbakir.lms.domain.Member;
import com.aytuncbakir.lms.domain.Novel;
import com.aytuncbakir.lms.domain.User;
import com.aytuncbakir.lms.exception.UserBlockedException;
import com.aytuncbakir.lms.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = {"/","/index"})
	public String index(Model model) {
		model.addAttribute("command", new LoginCommand());
		return "index";  // JSP   - //WEb-INF/view/index.jsp
	}
	
	
	@RequestMapping(value = {"/login"}, method=RequestMethod.POST)
	public String handleLogin(@ModelAttribute("command") LoginCommand command, Model model, HttpSession session ) {
		try {
			User loggedInUser =  userService.login(command.getUsername(), command.getPassword());
			
		if(loggedInUser == null) {
			//failed
			model.addAttribute("err","Login Failed! Enter valid credientials!");
			return "index";  // JSP - Login Form
		}else {
			// success
			if(loggedInUser.getType().equals(UserService.ROLE_ADMIN)) {  // chect the role and redirect a appropriate dashboard
				//TODO: add user detail in session (assign session to logged in user)
				addUserInSession(loggedInUser, session);
				return "redirect:admin/dashboard";
			}else if(loggedInUser.getType().equals(UserService.ROLE_LIBRARIAN)){
				addUserInSession(loggedInUser, session);
				return "redirect:librarian/dashboard";
			}else if(loggedInUser.getType().equals(UserService.ROLE_USER)){
				addUserInSession(loggedInUser, session);
				return "redirect:user/dashboard";
			}
			
			else {
				model.addAttribute("err","Invalid User Role");
				return "index";  // JSP - Login Form
			}
		}
			
		} catch (UserBlockedException ex) {
			// add error and go back to login form
			model.addAttribute("err", ex.getMessage());
			return "index";  // JSP - Login Form
		}
		
	}
	
	
	@RequestMapping(value = {"/librarian/dashboard"})
	public String librarianDashboard() {	
		return "dashboard_librarian";  // JSP   - //WEb-INF/view/dashboard_user.jsp
	}
	
	@RequestMapping(value = {"/user/dashboard"})
	public String userDashboard() {	
		return "dashboard_user";  // JSP   - //WEb-INF/view/dashboard_user.jsp
	}
	
	@RequestMapping(value = {"/admin/dashboard"})
	public String adminDashboard() {
		return "dashboard_admin";  // JSP   - //WEb-INF/view/dashboard_admin.jsp
	}
	
	@RequestMapping(value = {"/logout"})
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:index?act=lo";  // JSP   - //WEb-INF/view/dashboard_user.jsp
	}
	
	@RequestMapping(value = {"/reg_form"})
	public String registrationForm(Model model) {
		UserCommand cmd = new UserCommand();
		model.addAttribute("command", cmd);
		return "/reg_form";  // JSP  
	}
	

	@RequestMapping(value = {"/register"})
	public String registerUser(@ModelAttribute("command") UserCommand cmd, Model model) {
		
		
		try {
			User user = cmd.getUser();
			System.out.println("********"+user.getUsername());
			if(user.getUsername().equals("")) {
				model.addAttribute("err", "Username can not be empty!");
				return "reg_form";
			}else {
				user.setType(UserService.ROLE_USER);
				user.setStatus(UserService.LOGIN_STATUS_ACTIVE);
				userService.register(user);
				return "redirect:index?act=reg";  // JSP  Login page
			}
			
		
		} catch (DuplicateKeyException e) {
			e.printStackTrace();
			model.addAttribute("err", "Username is in use select another one.");
			return "reg_form";
		}
		
	}
	
	@RequestMapping(value="/admin/ulist")
	public String userList (Model model) {
		model.addAttribute("userList",userService.findAll());
		return "ulist";  // JSP page
		
	}
	

	@RequestMapping(value="/admin/user_search")  
	public String userSearch (Model model, HttpSession session, @RequestParam("freeText") String freeText) {
		model.addAttribute("userList",userService.findUser(freeText));
		return "ulist";  // JSP page
		
	}
	
	
	@RequestMapping(value="/admin/del_user")
	public String deleteUser (@RequestParam("user_id") Integer userId) {
		userService.delete(userId);
		return "redirect:ulist?act=del";  // JSP page
		
	}
	
	@RequestMapping(value="/admin/bulk_user_delete")
	public String deleteBulkBook (@RequestParam("user_id") Integer[] users) {
		userService.delete(users);
		
		return "redirect:ulist?act=del";  // JSP page
		
	}
	
	@RequestMapping(value="/admin/edit_user")
	public String prepareEditUser (Model model, @RequestParam("user_id") Integer userId, HttpSession session) {
		// session kim edit etti kullanilbilir session.setAttribete("abookId", bookId);
		session.setAttribute("auserId", userId);
		User user =	userService.find(userId);
		model.addAttribute("command", user);
		return "user_form";  // JSP page
		
	}
	
	@RequestMapping(value="/admin/user_save_form")
	public String contactForm (Model model) {
		
		User user = new User();
		model.addAttribute("command", user);
		
		return "user_save_form";  // JSP form view
		
	}
	
	@RequestMapping(value="/admin/save_user")
	public String saveUser (@ModelAttribute("command") User user, Model model, HttpSession session) {
		
			try {
			userService.save(user);		
			return "redirect:ulist?act=sv";  // redirect user to book list url
			}catch(Exception e){
				model.addAttribute("err","Failed to save user");
				e.printStackTrace();
				return "user_save_form"; 
				
			}
	
	}
	
	@RequestMapping(value="/admin/update_user")
	public String updateUser ( @ModelAttribute("command") Member user, Model model, HttpSession session) {
				
			try {
			userService.update(user);		
			return "redirect:ulist?act=ed";  // redirect user to book list url
			}catch(Exception e){
				model.addAttribute("err","Failed to edit user");
				e.printStackTrace();
				return "user_form"; 
				
			}
		
		
	}
	
	@RequestMapping(value = {"/admin/change_status"})
	@ResponseBody
	public String changeStatus(@RequestParam Integer userId, @RequestParam Integer status) {
				
		try {
			userService.changeUserStatus(userId, status);
			return "Success: Status Changed";
			
		}catch(Exception e) {
			e.printStackTrace();
			return "Error: Status can not be Changed";
		}
	
		
	}
	
	@RequestMapping(value = {"/admin/change_type"})
	@ResponseBody
	public String changeType(@RequestParam Integer userId, @RequestParam Integer type) {
				
		try {
			userService.changeUserType(userId, type);
			return "Success: Type Changed";
			
		}catch(Exception e) {
			e.printStackTrace();
			return "Error: Type can not be Changed";
		}
	
		
	}
	
	@RequestMapping(value = {"/check_availability"})
	@ResponseBody
	public String checkAvailability(@RequestParam String username) {
			if(username.equals(""))
				return "Username can not be empty";
			
			if(userService.isUsernameExist(username)) {
				return "Username in use! Choose another one.";
			}else {
				return "OK! Username can be taken.";
			}
			
		
	}
	
	
	private void addUserInSession(User user, HttpSession session) {
		
		session.setAttribute("user", user);
		session.setAttribute("userId", user.getUserId());
		session.setAttribute("userType", user.getType());
	}

}
