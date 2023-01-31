package ru.learnup.socialnetwork.util.validation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.learnup.socialnetwork.dto.UserDto;
import ru.learnup.socialnetwork.model.User;
import ru.learnup.socialnetwork.service.EqualService;
import ru.learnup.socialnetwork.view.UserView;

import java.util.Optional;

@Component
public class UserValidator implements Validator {

    private final EqualService equalService;

    @Autowired
    public UserValidator(EqualService equalService) {
        this.equalService = equalService;
    }


    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDto userDto = (UserDto) target;

        Optional<User> userForLogin = equalService.defineUserByNickname(userDto.getLogin());

//        Optional<User> userForEmail = equalService.defineUserByEmail(userDto.getEmail());
//        Optional<User> userForPhone = equalService.defineUserByPhone(userDto.getPhone());

        if (!userForLogin.isEmpty()) {
            errors.rejectValue("Email", "",
                    "A user with this email already exists, " +
                            "your email must be unique to register.");


        }
    }

//    @Override
//    public void validate(Object target, Errors errors) {
//        UserView user = (UserView)target;
//
//      try {
//          equalService.defineUserByNickname(user.getLogin());
//      } catch (UsernameNotFoundException ignored) {
//          return;
//      }
//
//        errors.rejectValue("login", "",
//                "A user with this username already exists, " +
//                "your username must be unique to register.");
//
//    }

}
