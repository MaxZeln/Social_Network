package ru.learnup.socialnetwork.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import ru.learnup.socialnetwork.util.passayPhoneValidation.annotation.ValidPassword;
import ru.learnup.socialnetwork.util.phoneNumberValidation.annotation.PhoneNumber;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class UserView {

    @JsonProperty
    private Long id;
    @JsonProperty
    private String login;
    @JsonProperty
    @NotEmpty(message = "Enter your email")
    @Email(message = "Enter a valid email address",
            regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
    private String email;

    @JsonProperty
    @NotEmpty(message = "Enter your phone")
    @PhoneNumber(message = "Please enter a valid phone number")
    @Size(min = 10, max = 20, message = "phone number must be between 10 and 20 characters long")
    private String phone;

    @JsonProperty
    @NotEmpty(message = "Enter your password")
    @ValidPassword
    private String password;

    @JsonProperty
    @NotEmpty(message = "Confirm your password")
    @ValidPassword
    private String confirmPassword;

    @JsonProperty
    private String status;

    @JsonProperty
    private Boolean enabled;

    @JsonProperty
    private ImageView image;

    @JsonProperty
    private RoleView role;

    @Override
    public String toString() {
        return "UserView{" +
                "id=" + id +
                ", nickname='" + login + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                ", status='" + status + '\'' +
                ", enabled=" + enabled +
                ", image=" + image +
                ", role=" + role +
                '}';
    }

}
