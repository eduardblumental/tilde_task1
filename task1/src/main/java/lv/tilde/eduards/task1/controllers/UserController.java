package lv.tilde.eduards.task1.controllers;

import lv.tilde.eduards.task1.DTOs.ViewUserDTO;
import lv.tilde.eduards.task1.DTOs.NewUserDTO;
import lv.tilde.eduards.task1.exceptions.CustomBadRequestException;
import lv.tilde.eduards.task1.objects.User;
import lv.tilde.eduards.task1.services.UserService;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping (value = "/new-user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseStatus addNewUser (@RequestBody @Valid NewUserDTO newUserDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new CustomBadRequestException("Wrong Input. Please try again.");
        }

//        TODO Service
    }

    @PostMapping (value = "/view-user", produces = MediaType.APPLICATION_JSON_VALUE)
    public User viewUser (@RequestBody @Valid ViewUserDTO viewUserDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new CustomBadRequestException("Wrong Input. Please try again.");
        }

//        TODO Service
    }

    @GetMapping(value = "/user-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> displayAllUsers () {

//        TODO Service
    }
}
