package com.raghav.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "players")
public class Player {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	String name;

//	@NotBlank(message = "Email is required")
	// @Email
	// @Column(unique = true)
	String email;

	String gender;

	// @NotBlank(message = "Age cannot be left blank")
	int age;

	@Column(nullable = true)
	String imagePath;

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Player(String name, @NotBlank(message = "Email is required") @Email String email, String gender,
			@NotBlank(message = "Age cannot be left blank") int age) {
		super();
		this.name = name;
		this.email = email;
		this.gender = gender;
		this.age = age;

	}

	public Player() {

	}

}
