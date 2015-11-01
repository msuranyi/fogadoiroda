package hu.gdf.oop.fogadoiroda.web.validator;

import hu.gdf.oop.fogadoiroda.model.User;
import hu.gdf.oop.fogadoiroda.service.UserService;
import hu.gdf.oop.fogadoiroda.web.model.Profile;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class ProfileValidator implements Validator {

    private UserService userService;

    public ProfileValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Profile.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Profile profile = (Profile) target;

        if (profile.getOldPassword() != null ||
                profile.getNewPassword() != null ||
                profile.getNewPasswordCheck() != null) {

            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = userService.findByName(username);

            ValidationUtils.rejectIfEmpty(errors, "oldPassword", "NotNull");
            ValidationUtils.rejectIfEmpty(errors, "newPassword", "NotNull");
            ValidationUtils.rejectIfEmpty(errors, "newPasswordCheck", "NotNull");

            if (!user.getPassword().equals(profile.getOldPassword())) {
                errors.rejectValue("oldPassword", "errors.invalid.password");
            }

            if (profile.getOldPassword() != null
                    && profile.getNewPassword() != null
                    && profile.getOldPassword().equals(profile.getNewPassword())) {
                errors.rejectValue("newPassword", "errors.password.equals");
            }

            if (profile.getNewPassword() != null
                    && profile.getNewPasswordCheck() != null
                    && !profile.getNewPassword().equals(profile.getNewPasswordCheck())) {
                errors.rejectValue("newPasswordCheck", "errors.password.mismatch");
            }
        }
    }
}
