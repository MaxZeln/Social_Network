package ru.learnup.socialnetwork.controller;

import org.postgresql.shaded.com.ongres.scram.common.bouncycastle.base64.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import ru.learnup.socialnetwork.dto.ImageDto;
import ru.learnup.socialnetwork.model.Role;
import ru.learnup.socialnetwork.model.User;
import ru.learnup.socialnetwork.mapper.RoleMapper;
import ru.learnup.socialnetwork.dto.UserDto;
import ru.learnup.socialnetwork.reposiory.RoleRepository;
import ru.learnup.socialnetwork.security.PersonDetails;
import ru.learnup.socialnetwork.service.ImageService;
import ru.learnup.socialnetwork.service.UserService;
import ru.learnup.socialnetwork.util.validation.UserValidator;
import ru.learnup.socialnetwork.view.UserView;

import javax.validation.Valid;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
//@Slf4j
public class AuthController {

    private final UserValidator userValidator;
    private final RoleRepository roleRepository;
    private final UserService userService;

    @Autowired
    public AuthController(
                          UserValidator userValidator,
                          RoleRepository roleRepository,
                          UserService userService) {
        this.userValidator = userValidator;
        this.roleRepository = roleRepository;
        this.userService = userService;

    }

    @GetMapping("/login")
    public ModelAndView index () {
        ModelAndView login = new ModelAndView("/auth/login");
        return login;
    }

    @GetMapping("/welcome")
    public ModelAndView welcomePage() throws UsernameNotFoundException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        try {

            Optional<User> user = userService.defineUserByNickname(auth.getName());

            if (user.isEmpty()) throw new UsernameNotFoundException("Пользователь не найден");

            String name = (user.get().getLogin());

            ModelAndView welcome = new ModelAndView("/welcome");
            welcome.addObject("name", name);

        if (user.get().getImage() != null) {

            byte[] encode = Base64.encode(user.get().getImage().getBytes());

            welcome.addObject("image", new String(encode, "UTF-8"));
        }

            return welcome;

        } catch (UsernameNotFoundException exception) {
            ModelAndView login = new ModelAndView("/auth/login");
            return login;

        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

    }


    @GetMapping("/registration")
    public ModelAndView registrationPage(@ModelAttribute("userView") UserView userView) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/auth/registration");
        return modelAndView;
    }

    @PostMapping("/registration")
    public ModelAndView performRegistration(@ModelAttribute("userView") @Valid UserView userView,
                                            @RequestParam MultipartFile file,
                                            BindingResult bindingResult) throws IOException {

    Role role = roleRepository.getReferenceById(2L);

        ImageDto image = new ImageDto();

        if (file.getSize() != 0) {
            image.setName(file.getName());
            image.setOriginalFileName(file.getOriginalFilename());
            image.setContentType(file.getContentType());
            image.setSize(file.getSize());
            image.setBytes(file.getBytes());
        }

    UserDto userDto = UserDto.builder()
            .id(1L)
            .login(userView.getLogin())
            .email(userView.getEmail())
            .phone(userView.getPhone())
            .password(userView.getPassword())
            .confirmPassword(userView.getPassword())
            .status(userView.getStatus())
            .enabled(false)
            .role(RoleMapper.ROLE_MAPPER.mapToDto(role))
            .build();


        if (image.getSize() != null) {
            userDto.setImage(image);
        }

        userValidator.validate(userDto, bindingResult);

        if (bindingResult.hasErrors()) {
            ModelAndView registration = new ModelAndView();
            registration.setViewName("/auth/registration");
            return registration;
        }

        userService.create(userDto);

    ModelAndView login = new ModelAndView();
    login.setViewName("/auth/login");

    return login;

    }

    @GetMapping("/authentication")
    public User getAuthentication() {
        PersonDetails userPrincipal = null;
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Object principal = securityContext.getAuthentication().getPrincipal();
        System.out.println(principal);
        System.out.println("");
        System.out.println("");
        System.out.println("");

        if (principal instanceof UserDetails) {
            userPrincipal =((PersonDetails) principal);
            System.out.println(userPrincipal);
        }

        return userPrincipal.getPerson();
    }

}
