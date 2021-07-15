package com.raghav.request;

import org.springframework.web.multipart.MultipartFile;

public class PlayerRequest {

	private String name;
	private String email;
	private int age;
	private String gender;
	private MultipartFile player_image;

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public MultipartFile getPlayer_image() {
		return player_image;
	}

	public void setPlayer_image(MultipartFile player_image) {
		this.player_image = player_image;
	}

	@Override
	public String toString() {
		return "PlayerRequest [name=" + name + ", email=" + email + ", age=" + age + ", gender=" + gender
				+ ", player_image=" + player_image + "]";
	}

	
}
