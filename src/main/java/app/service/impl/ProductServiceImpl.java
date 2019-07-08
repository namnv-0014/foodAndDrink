package app.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import app.model.Product;
import app.service.ProductService;

public class ProductServiceImpl extends BaseServiceImpl implements ProductService {

	@Override
	public Product findById(Serializable key) {
		// TODO Auto-generated method stub
		return getProductDAO().findById(key);
	}

	@Override
	public Product saveOrUpdate(Product entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Product entity) {
		// TODO Auto-generated method stub
		return false;
	}

	public Page<Product> findPaginated(Pageable pageable, List<Product> products, String productName) {
		try {
	        Page<Product> productPage
	          = new PageImpl<Product>(products, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()), productCount(productName));
	        return productPage;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public int productCount(String productName) {
		try {
			return getProductDAO().productCount(productName);
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public Page<Product> loadProducts(int categoryId, String productName, int orderBy, Pageable pageable) {
		try {
			List<Product> products = getProductDAO().loadProducts(categoryId, productName, orderBy, pageable.getPageSize(), pageable.getPageNumber());
			return findPaginated(pageable, products, productName);
		} catch (Exception e) {
			return null;
		}
	}
	
}
