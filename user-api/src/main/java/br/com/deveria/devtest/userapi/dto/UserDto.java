package br.com.deveria.devtest.userapi.dto;

import java.io.Serializable;
import java.util.Date;

import org.springframework.beans.BeanUtils;

import br.com.deveria.devtest.userapi.domain.User;

public class UserDto implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;

	private String login;

	private String password;

	private Date createdDate;

	private Date updatedDate;

	private String email;

	private Boolean admin;
	
	public UserDto() { }

	public UserDto(User u) {
		BeanUtils.copyProperties(u, this);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}
}
