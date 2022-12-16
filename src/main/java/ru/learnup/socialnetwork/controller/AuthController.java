package ru.learnup.socialnetwork.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ru.learnup.socialnetwork.model.Image;
import ru.learnup.socialnetwork.model.Role;
import ru.learnup.socialnetwork.model.User;
import ru.learnup.socialnetwork.mapper.ImageMapper;
import ru.learnup.socialnetwork.mapper.RoleMapper;
import ru.learnup.socialnetwork.mapper.UserMapper;
import ru.learnup.socialnetwork.dto.UserDto;
import ru.learnup.socialnetwork.reposiory.RoleRepository;
import ru.learnup.socialnetwork.security.PersonDetails;
import ru.learnup.socialnetwork.service.ImageService;
import ru.learnup.socialnetwork.service.UserService;
import ru.learnup.socialnetwork.util.validation.UserValidator;
import ru.learnup.socialnetwork.view.UserView;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    private final UserValidator userValidator;
    private final RoleRepository roleRepository;
    private final ImageService imageService;
    private final UserService userService;

    @Autowired
    public AuthController(
                          UserValidator userValidator,
                          RoleRepository roleRepository,
                          ImageService imageService,
                          UserService userService) {
        this.userValidator = userValidator;
        this.roleRepository = roleRepository;
        this.imageService = imageService;
        this.userService = userService;

    }

    @GetMapping("/login")
    public ModelAndView index () {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("auth/login");
        return modelAndView;
    }

    @GetMapping("/hello")
    public ModelAndView sayHello() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("hello");
        return modelAndView;
    }


    @GetMapping("/registration")
    public ModelAndView registrationPage(@ModelAttribute("userView") UserView userView) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("auth/registration");
        return modelAndView;
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

    @PostMapping("/registration")
    public ModelAndView performRegistration(@ModelAttribute("userView") @Valid UserView userView,
                                            @RequestParam MultipartFile file,
                                            BindingResult bindingResult) throws IOException {

    userValidator.validate(userView, bindingResult);

    if (bindingResult.hasErrors()) {
        ModelAndView registration = new ModelAndView();
        registration.setViewName("auth/registration");
        return registration;
   }

    Role role = roleRepository.getReferenceById(2L);

    if (userView.getStatus().isEmpty()) {
        userView.setStatus("Empty status");
    }

    Image image = new Image();

    if (file.getSize() != 0) {
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
    } else {
        image = imageService.getImageById("c4ed5acf-1cf7-47ce-9889-749c7560fbb5");
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
            .image(ImageMapper.IMAGE_MAPPER.mapToDto(image))
            .build();

    userService.create(userDto);

    ModelAndView login = new ModelAndView();
    login.setViewName("auth/login");

    return login;


    }
}
