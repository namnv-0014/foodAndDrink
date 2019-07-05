package app.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import app.bean.CartItem;
import app.model.Order;
import app.model.User;
import app.service.OrderProductService;
import app.service.OrderService;
import app.service.UserService;

@Controller
@RequestMapping("orders")
public class OrderController {
	
	@Autowired
	OrderService orderService;
	@Autowired
	UserService userService;
	@Autowired
	OrderProductService orderProductService;
	
	@GetMapping(value = "/new")
	public String newOrder(Model model) {
		model.addAttribute("order", new Order());
		return "orders/new";
	}
	
	@PostMapping(value = "/create")
	public String create(@ModelAttribute("order") Order order, HttpSession session) {
		
		User user = userService.findById(Integer.parseInt(session.getAttribute("currentUser").toString()));
		List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
		
		order.setUser(user);
		order.setTotal(CartController.totalCart(cart));
		
		orderService.createOrder(order);
		orderProductService.createListOP(cart, order);

		return "redirect:/";
	}
 
}
