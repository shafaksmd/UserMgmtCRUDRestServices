package com.egentest.usermgmt.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {

	private Long userId = null;
	
	private String firstName = null;
	private String middleName = null;
	private String lastName = null;
	
	private Integer age = 0;
	
	private String userGender;
	
	private Double phoneNumber = null;
	private Double zipCode = null;

	public User(){
		userId=(long) 0;
	}
	
	
	
	public User(Long id, String fName,String mName,String lName, Integer age, String gender, Double phone, Double zip){
		this.userId = id;
		this.firstName = fName;
		this.middleName = mName;
		this.lastName = lName;
		this.age = age;
		this.userGender = gender;
		this.phoneNumber = phone;
		this.zipCode = zip;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getUserGender() {
		return userGender;
	}

	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}

	public Double getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Double phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Double getZipCode() {
		return zipCode;
	}

	public void setZipCode(Double zipCode) {
		this.zipCode = zipCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (userId ^ (userId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (userId != other.userId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + userId + ", fname=" + firstName + ", mname=" + middleName +", lname=" + lastName +  ", age=" + age
				+ ", gender=" + userGender + ", phone=" + phoneNumber +", zipCode=" + zipCode +  "]";
	}


}
