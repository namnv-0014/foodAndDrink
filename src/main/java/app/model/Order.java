package app.model;

import java.util.List;

public class Order {
	
	private Integer id;
	private String nameReceiver;
	private String address;
	private String phone;
	private Float total;
	private String note;
	private Integer status;
	private User user;
	private List<OrderProduct> orderProducts;
	
	public Order() {
		
	}
	
	public Order(int id, 
			String name, 
			String address, 
			String phone, 
			Float total, 
			String note, 
			User user) {
		this.id = id;
		this.nameReceiver = name;
		this.address = address;
		this.phone = phone;
		this.total = total;
		this.note = note;
		this.status = 0;
		this.user = user;
	}
	
	public Order(int id, 
			String phone, 
			Float total, 
			String note, 
			User user) {
		this.id = id;
		this.nameReceiver = user.getName();
		this.address = user.getAddress();
		this.phone = phone;
		this.total = total;
		this.note = note;
		this.status = 0;
		this.user = user;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNameReceiver() {
		return nameReceiver;
	}
	
	public void setNameReceiver(String nameReceiver) {
		this.nameReceiver = nameReceiver;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public Float getTotal() {
		return total;
	}
	
	public void setTotal(Float total) {
		this.total = total;
	}
	
	public String getNote() {
		return note;
	}
	
	public void setNote(String note) {
		this.note = note;
	}
	
	public Integer getStatus() {
		return status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	public List<OrderProduct> getOrderProducts() {
		return orderProducts;
	}

	public void setOrderProducts(List<OrderProduct> orderProducts) {
		this.orderProducts = orderProducts;
	}
	
}
