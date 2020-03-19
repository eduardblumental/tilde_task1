package lv.tilde.eduards.task1.services;

import lv.tilde.eduards.task1.DAOs.UserDAO;
import lv.tilde.eduards.task1.DTOs.NewUserDTO;
import lv.tilde.eduards.task1.DTOs.ViewUserDTO;
import lv.tilde.eduards.task1.controllers.UserController;
import lv.tilde.eduards.task1.enums.ResponseStatus;
import lv.tilde.eduards.task1.exceptions.CustomBadRequestException;
import lv.tilde.eduards.task1.objects.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public Long findUserIdByUsername (String username){
        Optional<User> optionalUser = userDAO.findByUsername(username);
        if(optionalUser.isEmpty()){
            throw new CustomBadRequestException("User with username: \"" + username + "\" doesn't exist.");
        }
        User user = optionalUser.get();
        return user.getId();
    }

    public void usernameAlreadyExists (String username) {
        if(userDAO.existsByUsername(username)){
            throw new CustomBadRequestException("User with username: \"" + username + "\" already exists.");
        }
    }

    public void usernameDoesNotExist (String username) {
        if(!userDAO.existsByUsername(username)){
            throw new CustomBadRequestException("User with username: \"" + username + "\" doesn't exist.");
        }
    }

    public ResponseStatus addNewUser (NewUserDTO newUserDTO){
        usernameAlreadyExists(newUserDTO.getUsername());
        User user = new User();
        user.setUsername(newUserDTO.getUsername());
        user.setGrossDebtors(newUserDTO.getGrossDebtors());
        user.setGrossCreditors(newUserDTO.getGrossCreditors());
        user.setNetDebtors(newUserDTO.getNetDebtors());
        user.setNetCreditors(newUserDTO.getNetCreditors());
        user.setBalance(user.getNetDebtors() - user.getNetCreditors());
        userDAO.save(user);
        LOGGER.info(user.getUsername() + " has been added to the system.");
        return ResponseStatus.OK;
    }

    public User viewUser (ViewUserDTO viewUserDTO){
        Optional<User> optionalUser = userDAO.findById(findUserIdByUsername(viewUserDTO.getUsername()));
        User user = optionalUser.get();
        LOGGER.info("Request to view " + user.getUsername() + " has been submitted.");
        return user;
    }

    public List<User> displayAllUsers (){
        List<User> list = new ArrayList<>();

        for (User user : userDAO.findAll()){
            list.add(user);
        }

        LOGGER.info("Request to view all users has been submitted.");
        return list;
    }

}
