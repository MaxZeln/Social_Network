package ru.learnup.socialnetwork.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ru.learnup.socialnetwork.entity.Image;
import ru.learnup.socialnetwork.entity.Role;
import ru.learnup.socialnetwork.entity.User;
import ru.learnup.socialnetwork.mapper.ImageMapper;
import ru.learnup.socialnetwork.mapper.RoleMapper;
import ru.learnup.socialnetwork.mapper.UserMapper;
import ru.learnup.socialnetwork.model.UserDto;
import ru.learnup.socialnetwork.reposiory.RoleRepository;
import ru.learnup.socialnetwork.security.PersonDetails;
import ru.learnup.socialnetwork.service.ImageService;
import ru.learnup.socialnetwork.service.UserService;
import ru.learnup.socialnetwork.util.UserValidator;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    private final UserMapper userMapper;
    private final RoleMapper roleMapper;
    private final ImageMapper imageMapper;
    private final UserValidator userValidator;
    private final RoleRepository roleRepository;
    private final ImageService imageService;
    private final UserService userService;

    @Autowired
    public AuthController(UserMapper userMapper,
                          RoleMapper roleMapper,
                          ImageMapper imageMapper,
                          UserValidator userValidator,
                          RoleRepository roleRepository,
                          ImageService imageService,
                          UserService userService) {

        this.userMapper = userMapper;
        this.roleMapper = roleMapper;
        this.imageMapper = imageMapper;
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
    public ModelAndView registrationPage(@ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("auth/registration");
        return modelAndView;
    }

//    @GetMapping("/authentication")
//    public Object getAuthentication(@CurrentSecurityContext(expression = "user")
//                                            Authentication authentication) {
//        return authentication.getDetails();
//    }

    @GetMapping("/authentication")
    public User getAuthentication() {
        PersonDetails userPrincipal = null;
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Object principal = securityContext.getAuthentication().getPrincipal();
//        if (principal instanceof User) {
//            userPrincipal = ((User) principal);
//        }
//        return userPrincipal;
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
    public ModelAndView performRegistration(@ModelAttribute("user") @Valid User user,
                                            @RequestParam MultipartFile file,
                                            BindingResult bindingResult) throws IOException {

    userValidator.validate(user, bindingResult);

    if (bindingResult.hasErrors()) {
        ModelAndView registration = new ModelAndView();
        registration.setViewName("auth/registration");
        return registration;
   }

    Role role = roleRepository.getReferenceById(2L);

    if (user.getStatus().isEmpty()) {
        user.setStatus("Empty status");
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
            .nickname(user.getNickname())
            .password(user.getPassword())
            .status(user.getStatus())
            .enabled(false)
            .role(roleMapper.mapToDto(role))
            .image(imageMapper.mapToDto(image))
            .build();

    userService.create(userDto);

    ModelAndView login = new ModelAndView();
    login.setViewName("auth/login");

    return login;


    }
}
