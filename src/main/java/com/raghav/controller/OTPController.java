package com.raghav.controller;

import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.raghav.services.EmailService;
import com.raghav.services.EmailTemplate;
import com.raghav.services.OTPService;

@Controller
public class OTPController {

	@Autowired
	public OTPService otpService;

	@Autowired
	public EmailService emailService;

	//@GetMapping("/generateOtp")
	public int generateOTP(String userEmail) throws MessagingException{
		
//			Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 
//			//String username = auth.getName();
			int otp = otpService.generateOTP();
			//Generate The Template to send OTP 
//			EmailTemplate template = new EmailTemplate("otp/SendOtp.html");
//			Map<String,String> replacements = new HashMap<String,String>();
////			replacements.put("user", username);
//			replacements.put("otpnum", String.valueOf(otp));
			String message ="Hi User, <br/>This is your one time password - "+otp+". <br/><br/> <b>Thanks <br/><br/>Team Auriga</b>";

			emailService.sendOtpMessage(userEmail, "OTP -SpringBoot", message);
			return otp;
			
	//return "main/otp";
	}

	@RequestMapping(value ="/validateOtp", method = RequestMethod.GET)
	public @ResponseBody String validateOtp(@RequestParam("otpnum") int otpnum){
		
			final String SUCCESS = "Entered Otp is valid";
			final String FAIL = "Entered Otp is NOT valid. Please Retry!";
			Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 
			String username = auth.getName();
			//Validate the Otp 
			if(otpnum >= 0){
				
			  int serverOtp = otpService.getOtp(username);
			    if(serverOtp > 0){
			      if(otpnum == serverOtp){
			          otpService.clearOTP(username);
			
	                  return ("Entered Otp is valid");
	                } 
			        else {
	                    return SUCCESS;
	                   }
	               }else {
	              return FAIL;
	               }
	             }else {
	                return FAIL;
	         }
	      }
	}