package app.bean;

import app.model.User;

public class UserInfo {

	private Integer id;
	private String name;
	private String email;
	private String password;
	private String confirmPassword;
	private String address;
	private String avatar;

	public UserInfo(Integer id, String name, String email, String password, String confirmPassword, String address,
			String avatar) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.address = address;
		this.avatar = avatar;
	}

	public UserInfo(String name, String email, String password, String address, String avatar) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.address = address;
		this.avatar = avatar;
	}

	public UserInfo() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public User convertToUser() {
		User user = new User();
		user.setId(this.getId());
		user.setName(this.getName());
		user.setEmail(this.getEmail());
		user.setAddress(this.getAddress());
		user.setPassword(this.getPassword().trim());
		return user;
	}
	
}
