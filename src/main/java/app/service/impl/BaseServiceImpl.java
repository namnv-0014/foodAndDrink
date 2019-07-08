package app.service.impl;

import app.dao.CategoryDAO;
import app.dao.OrderDAO;
import app.dao.OrderProductDAO;
import app.dao.ProductDAO;
import app.dao.UserDAO;

public class BaseServiceImpl {
	
	protected ProductDAO productDAO;
	protected UserDAO userDAO;
	protected CategoryDAO categoryDAO;
	protected OrderDAO orderDAO;
	protected OrderProductDAO orderProductDAO;
	
	public ProductDAO getProductDAO() {
		return productDAO;
	}

	public void setProductDAO(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}
	
	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public CategoryDAO getCategoryDAO() {
		return categoryDAO;
	}

	public void setCategoryDAO(CategoryDAO categoryDAO) {
		this.categoryDAO = categoryDAO;
	}

	public OrderDAO getOrderDAO() {
		return orderDAO;
	}

	public void setOrderDAO(OrderDAO orderDAO) {
		this.orderDAO = orderDAO;
	}

	public OrderProductDAO getOrderProductDAO() {
		return orderProductDAO;
	}

	public void setOrderProductDAO(OrderProductDAO orderProductDAO) {
		this.orderProductDAO = orderProductDAO;
	}
	
}
