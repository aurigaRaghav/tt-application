 package com.raghav.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.raghav.enums.Gender;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	 private static final long OTP_VALID_DURATION = 5 * 60 * 1000;   // 5 minutes
	@NotBlank(message = "Name is required")
	private String name;
	@NotBlank(message = "Email is required")
	@Email
	@Column(unique=true)
	private String email;

	@Column(name = "one_time_password")
    private int oneTimePassword;
     
	private int is_enabled;
	
	private Gender gender;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date created_on;
	
	private int age;
	
    public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getIs_enabled() {
		return is_enabled;
	}

	public void setIs_enabled(int is_enabled) {
		this.is_enabled = is_enabled;
	}

	public static long getOtpValidDuration() {
		return OTP_VALID_DURATION;
	}

	public int getOneTimePassword() {
		return oneTimePassword;
	}

	public void setOneTimePassword(int otp) {
		this.oneTimePassword = otp;
	}

	public Date getOtpRequestedTime() {
		return otpRequestedTime;
	}

	public void setOtpRequestedTime(Date otpRequestedTime) {
		this.otpRequestedTime = otpRequestedTime;
	}

	@Column(name = "otp_requested_time")
    private Date otpRequestedTime;
	
	public User() {
		super();
		
	}

	public User(String name, String email) {
		super();
		
		this.name = name;
		this.email = email;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Date getCreated_on() {
		return created_on;
	}

	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + "]";
	}
	
	public boolean isOTPRequired() {
        if (this.getOneTimePassword() == 0) {
            return false;
        }
         
        long currentTimeInMillis = System.currentTimeMillis();
        long otpRequestedTimeInMillis = this.otpRequestedTime.getTime();
         
        if (otpRequestedTimeInMillis + OTP_VALID_DURATION < currentTimeInMillis) {
            // OTP expires
            return false;
        }
         
        return true;
    }
     

}
