package app.bean;

public class CartItem {
	
	private ProductInfo product;
	private Integer quantity;
	
	public CartItem() {
		
	}
	
	public CartItem(ProductInfo product, int quantity) {
		this.product = product;
		this.quantity = quantity;
	}
	
	public ProductInfo getProduct() {
		return product;
	}
	
	public void setProduct(ProductInfo product) {
		this.product = product;
	}
	
	public Integer getQuantity() {
		return quantity;
	}
	
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	public float totalItem() {
		return this.product.getPrice() * this.quantity;
	}
	
}
