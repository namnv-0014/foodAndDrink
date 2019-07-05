package app.service;

import app.model.Order;

public interface OrderService extends BaseService<Integer, Order> {
	
	public boolean createOrder(Order order);
	
}
