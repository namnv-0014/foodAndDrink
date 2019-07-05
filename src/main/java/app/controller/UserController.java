package app.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;	
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import app.bean.UserInfo;
import app.model.User;
import app.service.UserService;
import validate.UserValidation;
import validate.UserValidationEdit;

@Controller
@PropertySource("classpath:messages.properties")
@PropertySource("classpath:constants.properties")
public class UserController {
	private static final Logger logger = Logger.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@Value("${messages.logout}")
	private String msg_logout;
	@Value("${messages.nouserfound}")
	private String msg_nouserfound;
	@Value("${messages.deleted}")
	private String msg_deleted;
	@Value("${messages.deletefail}")
	private String msg_deletefail;
	@Value("${messages.updated}")
	private String msg_updated;
	@Value("${messages.updatefail}")
	private String msg_updatefail;
	@Value("${messages.danger}")
	private String danger;
	@Value("${messages.error}")
	private String error;
	@Value("${messages.success}")
	private String success;
	@Value("${defaultUserPage}")
	private int defaultPage;
	@Value("${defaultUserPageSize}")
	private int defaultPageSize;
	@Value("${Login_error}")
	private String login_error;
	@Value("${Email_already}")
	private String email_already;

	public UserService getUserService() {
		return userService;
	}

	@PostMapping("/welcome")
	public String welcome(@RequestParam("email") String usermail, @RequestParam("password") String password,
			HttpServletRequest request, Model model, final RedirectAttributes redirectAttributes) {

		User user = getUserService().findByEmailAndPassword(usermail, password.trim());
		if (user != null) {
			HttpSession session = request.getSession();
			redirectAttributes.addFlashAttribute("msg", "Welcome "+user.getName().toUpperCase()+"!");
			session.setAttribute("currentUser", user.getId());
			return "redirect:/";
		} else {
			model.addAttribute("error", login_error);
		}
		return "redirect:login";
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("message", msg_logout);
		session.removeAttribute("currentUser");
		session.invalidate();
		return "redirect:login";
	}

	@GetMapping("users/{id}")
	public String show(@PathVariable("id") int id, Model model) {
		logger.info("detail user");
		User user = userService.findById(id);
		if (user != null) {
			model.addAttribute("user", user);
			return "users/user";
		}

		model.addAttribute("css", danger);
		model.addAttribute("msg", msg_nouserfound);
		return "error";
	}

	@PostMapping("/registerProcess")
	public String register(@ModelAttribute("userInfo") UserInfo userInfo, BindingResult result, Model model, final RedirectAttributes redirectAttributes) {
		UserValidation validation = new UserValidation();
		validation.validate(userInfo, result);
		if (result.hasErrors()) {
			return "register";
		}
		if (userService.isEmailExist(userInfo.getEmail())) {
			redirectAttributes.addFlashAttribute("errorMessage", email_already);
			return "register";
		}
		userService.createUser(userInfo.convertToUser());

		return "index";
	}

	@GetMapping("/register")
	public String register(ModelMap modelMap) {
		modelMap.addAttribute("userInfo", new UserInfo());
		return "register";
	}
	
	@GetMapping("/users")
	public String index(Model model, @RequestParam("search") Optional<String> search, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
		int currentPage = page.orElse(defaultPage);
		int pageSize = size.orElse(defaultPageSize);
		String userName = search.orElse("");

		Page<User> users = userService.loadUsers(userName, PageRequest.of(currentPage - 1, pageSize));

		model.addAttribute("search", userName);
		model.addAttribute("users", users);

		int totalPages = users.getTotalPages();

		if (totalPages > 0) {
			List<Integer> pages = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			model.addAttribute("pages", pages);
		}
		return "users/users";
	}
	
	@GetMapping("users/{id}/delete")
	public String deleteUser(@PathVariable("id") Integer id, final RedirectAttributes redirectAttributes) {
		logger.info("delete user");
		if (userService.deleteUser(id)) {
			redirectAttributes.addFlashAttribute("css", success);
			redirectAttributes.addFlashAttribute("msg", msg_deleted);
		} else {
			redirectAttributes.addFlashAttribute("css", error);
			redirectAttributes.addFlashAttribute("msg", msg_deletefail);
		}
		return "redirect:/users";
	}
	
	@GetMapping("users/{id}/edit")
	public String editUser(@PathVariable("id") Integer id, Model model) {
		logger.info("edit user");
		User user = userService.findById(id);
		if (user != null) {
			model.addAttribute("user", user);
			return "users/edit";
		}
		model.addAttribute("css", danger);
		model.addAttribute("msg", msg_nouserfound);
		return "error";
	}
	
	@PostMapping("/update")
	public String editUser(@ModelAttribute("user") User userView, BindingResult result, Model model, final RedirectAttributes redirectAttributes) {
		logger.info("edit user");
		UserValidationEdit validation = new UserValidationEdit();
		validation.validate(userView, result);
		if (result.hasErrors()) {
			return "users/edit";
		}
		User user = userService.findById(userView.getId());
		if (userService.updateUser(userView, user)) {
			redirectAttributes.addFlashAttribute("css", success);
			redirectAttributes.addFlashAttribute("msg", msg_updated);
		} else {
			redirectAttributes.addFlashAttribute("css", error);
			redirectAttributes.addFlashAttribute("msg", msg_updatefail);
		}
		return "redirect:/users/" + user.getId();
	}

}
