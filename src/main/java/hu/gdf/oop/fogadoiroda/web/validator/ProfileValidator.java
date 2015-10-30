package hu.gdf.oop.fogadoiroda.web.validator;

import hu.gdf.oop.fogadoiroda.model.User;
import hu.gdf.oop.fogadoiroda.service.UserService;
import hu.gdf.oop.fogadoiroda.web.model.Profile;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.Errors;
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
            if (!user.getPassword().equals(profile.getOldPassword())) {
                errors.rejectValue("oldPassword", null, "invalid password");
            }

            if (profile.getOldPassword().equals(profile.getNewPassword())) {
                errors.rejectValue("newPassword", null, "may not equals with the old password");
            }

            if (profile.getNewPassword() != null && !profile.getNewPassword().equals(profile.getNewPasswordCheck())) {
                errors.rejectValue("newPasswordCheck", null, "must equals with the new password");
            }
        }
    }
}
