package com.book.manage.bean;

import java.util.Date;

public class ManageInfo {
    private String adminId;

    private String name;

    private String password;

    private String effectTime;

    private String failTime;

    private String isValid;

    private String gender;

    private String phone;

    private String email;

    private String department;


    public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId == null ? null : adminId.trim();
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }


    public String getEffectTime() {
		return effectTime;
	}

	public void setEffectTime(String effectTime) {
		this.effectTime = effectTime == null ? null : effectTime.trim();;
	}

	public String getFailTime() {
		return failTime;
	}

	public void setFailTime(String failTime) {
		this.failTime = failTime == null ? null : failTime.trim();;
	}

	public String getIsValid() {
        return isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid == null ? null : isValid.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department == null ? null : department.trim();
    }
}