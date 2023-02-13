package ru.learnup.socialnetwork.util.phoneNumberValidation;

import org.springframework.beans.factory.annotation.Configurable;
import ru.learnup.socialnetwork.util.phoneNumberValidation.annotation.PhoneNumber;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Configurable
public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {

    private static final String PHONE_NUMBER_REGEX = "^(\\+7|7|8)[\\s(]*\\d{3}[)\\s]*\\d{3}[\\s-]?\\d{2}[\\s-]?\\d{2}$";

    private static final Pattern PHONE_NUMBER_PATTERN =Pattern.compile(PHONE_NUMBER_REGEX);

    @Override
    public void initialize(PhoneNumber constraintAnnotation) {

    }

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext context) {

        Matcher matcher = PHONE_NUMBER_PATTERN.matcher(phoneNumber);

        return matcher.matches();

    }

}