package lv.tilde.eduards.task1.services;

import lv.tilde.eduards.task1.DAOs.UserDAO;
import lv.tilde.eduards.task1.DTOs.NewUserDTO;
import lv.tilde.eduards.task1.DTOs.ViewUserDTO;
import lv.tilde.eduards.task1.controllers.UserController;
import lv.tilde.eduards.task1.enums.ResponseStatus;
import lv.tilde.eduards.task1.exceptions.CustomBadRequestException;
import lv.tilde.eduards.task1.objects.SystemUser;
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
        Optional<SystemUser> optionalUser = userDAO.findByUsername(username);
        if(optionalUser.isEmpty()){
            throw new CustomBadRequestException("User with username " + username + " doesn't exist.");
        }
        SystemUser user = optionalUser.get();
        return user.getId();
    }

    public void usernameAlreadyExists (String username) {
        if(userDAO.existsByUsername(username)){
            throw new CustomBadRequestException("User with username " + username + " already exists.");
        }
    }

    public void usernameDoesNotExist (String username) {
        if(!userDAO.existsByUsername(username)){
            throw new CustomBadRequestException("User with username " + username + " doesn't exist.");
        }
    }

    public ResponseStatus addNewUser (NewUserDTO newUserDTO){
        usernameAlreadyExists(newUserDTO.getUsername());
        SystemUser user = new SystemUser();
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

    public SystemUser viewUser (ViewUserDTO viewUserDTO){
        Optional<SystemUser> optionalUser = userDAO.findById(findUserIdByUsername(viewUserDTO.getUsername()));
        SystemUser user = optionalUser.get();
        LOGGER.info("Request to view " + user.getUsername() + " has been submitted.");
        return user;
    }

    public List<SystemUser> displayAllUsers (){
        List<SystemUser> list = new ArrayList<>();

        for (SystemUser user : userDAO.findAll()){
            list.add(user);
        }

        LOGGER.info("Request to view all users has been submitted.");
        return list;
    }

}
