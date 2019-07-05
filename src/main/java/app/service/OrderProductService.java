package app.service;

import java.util.List;

import app.bean.CartItem;
import app.model.Order;
import app.model.OrderProduct;

public interface OrderProductService extends BaseService<Integer, OrderProduct> {
	
	public boolean createListOP(List<CartItem> items, Order order);
	
}
