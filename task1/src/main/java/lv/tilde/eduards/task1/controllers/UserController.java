package lv.tilde.eduards.task1.controllers;

import lv.tilde.eduards.task1.DTOs.ViewUserDTO;
import lv.tilde.eduards.task1.DTOs.NewUserDTO;
import lv.tilde.eduards.task1.enums.ResponseStatus;
import lv.tilde.eduards.task1.exceptions.CustomBadRequestException;
import lv.tilde.eduards.task1.objects.SystemUser;
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
    public ResponseStatus newUser (@RequestBody @Valid NewUserDTO newUserDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new CustomBadRequestException("Wrong Input. Please try again.");
        }

        return userService.addNewUser(newUserDTO);
    }

    @PostMapping (value = "/view-user", produces = MediaType.APPLICATION_JSON_VALUE)
    public SystemUser viewUser (@RequestBody @Valid ViewUserDTO viewUserDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new CustomBadRequestException("Wrong Input. Please try again.");
        }

        return userService.viewUser(viewUserDTO);
    }

    @GetMapping (value = "/user-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SystemUser> userList () {

        return userService.displayAllUsers();
    }
}
