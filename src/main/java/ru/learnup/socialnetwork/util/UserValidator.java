package ru.learnup.socialnetwork.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.learnup.socialnetwork.entity.User;
import ru.learnup.socialnetwork.service.EqualService;

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
        User user = (User)target;

      try {
          equalService.defineUserByNickname(user.getNickname());
      } catch (UsernameNotFoundException ignored) {
          return;
      }


        errors.rejectValue("nickname", "",
                "A user with this username already exists, " +
                "your username must be unique to register.");
    }
}
