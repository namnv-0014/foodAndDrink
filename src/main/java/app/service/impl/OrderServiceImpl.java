package app.service.impl;

import app.service.OrderService;

import java.io.Serializable;
import java.util.List;

import app.model.Order;

public class OrderServiceImpl extends BaseServiceImpl implements OrderService {

	@Override
	public Order findById(Serializable key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order saveOrUpdate(Order order) {
		try {
			return getOrderDAO().saveOrUpdate(order);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public boolean delete(Order entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean createOrder(Order order) {
		try {
			order.setStatus(0);
			Order orderNew = saveOrUpdate(order);
			return orderNew != null;
		} catch (Exception e) {
			throw e;
		}
	}

}
