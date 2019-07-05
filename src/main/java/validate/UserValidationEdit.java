package validate;

import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import app.model.User;

public class UserValidationEdit implements Validator {
	
	@Override
	public boolean supports(Class<?> type) {
		return User.class.isAssignableFrom(type);
	}

	@Override
	public void validate(Object object, Errors errors) {
		User user = (User) object;

		if (user.getName().length() == 0) {
			errors.rejectValue("name", "NotEmpty");
		}
		
		if (user.getAddress().length() == 0) {
			errors.rejectValue("address", "NotEmpty");
		}

		if (user.getEmail().length() == 0) {
			errors.rejectValue("email", "NotEmpty");
		} else if (new EmailValidator().isValid(user.getEmail(), null) == false) {
			errors.rejectValue("email", "Error.Email.Format");
		}
		
	}

}