package app.service.impl;

import java.io.Serializable;
import java.util.List;

import app.bean.CartItem;
import app.model.Order;
import app.model.OrderProduct;
import app.model.Product;
import app.service.OrderProductService;

public class OrderProductServiceImpl extends BaseServiceImpl implements OrderProductService {

	@Override
	public OrderProduct findById(Serializable key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderProduct saveOrUpdate(OrderProduct entity) {
		try {
			return getOrderProductDAO().saveOrUpdate(entity);
		} 
		catch (Exception e) {
			throw e;
		}

	}

	@Override
	public boolean delete(OrderProduct entity) {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean createOrderProduct(OrderProduct orderProduct) {
		try {
			OrderProduct orderProductNew = saveOrUpdate(orderProduct);
			return orderProductNew != null;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public boolean createListOP(List<CartItem> items, Order order) {
		try {
			for(CartItem item : items) {
				Product product = getProductDAO().findById(item.getProduct().getId());
				
				OrderProduct orderProduct = new OrderProduct();
				orderProduct.setProduct(product);
				orderProduct.setOrder(order);
				orderProduct.setPrice(product.getPrice());
				orderProduct.setQuantity(item.getQuantity());
				
				createOrderProduct(orderProduct);
			}
			return true;
		} catch (Exception e) {
			throw e;
		}
	}
	
}
