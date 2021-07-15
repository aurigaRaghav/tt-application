package com.raghav.controller;

import java.util.List;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.raghav.dao.UserRepository;
import com.raghav.entity.User;

@Controller
@RequestMapping("/")
public class HomeController {
	@Autowired
	UserRepository userRepository;
	@Autowired
	OTPController otpcnt;

	@GetMapping("/")
	public String displayHome() {

		return "main/home";

	}

	@GetMapping("/signup")
	public String signUp(Model model) {
		User userObj = new User();
		model.addAttribute("user", userObj);
		return "main/signup";

	}

	@GetMapping("/login")
	public String login() {

		return "main/login";

	}

	@GetMapping("/otp")
	public String otp() {

		return "main/otp";

	}

	@PostMapping("/register-user")

	public String registerUser(@Valid User user, BindingResult result, Model model, @ModelAttribute User frmData)
			throws MessagingException {

		if (result.hasErrors()) {
			return "/signup";
		}
		userRepository.save(user);
		String userEmail = frmData.getEmail();
		int otp = otpcnt.generateOTP(userEmail);
		this.updateUserOtp(userEmail, otp);
		return "redirect:/otp?email=" + userEmail;

	}
	
	public String validateOpt(@Valid User user, BindingResult result, Model model, @ModelAttribute User frmData) {
		return null;
	}

	public void updateUserOtp(String email, int otp) {
		User userFromDb = userRepository.updateUserByEmail(email);
		userFromDb.setOneTimePassword(otp);
		userRepository.save(userFromDb);
	}
}
