package lv.tilde.eduards.task1.services;

import lv.tilde.eduards.task1.DAOs.UserDAO;
import lv.tilde.eduards.task1.DTOs.NewUserDTO;
import lv.tilde.eduards.task1.DTOs.ViewUserDTO;
import lv.tilde.eduards.task1.enums.ResponseStatus;
import lv.tilde.eduards.task1.objects.SystemUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    UserService userService;

    @Mock
    private UserDAO userDAO;

    @BeforeEach
    void setUp(){
        userService = new UserService(userDAO);
    }

    @Test
    void shouldFindUserIdByUsername() {
        SystemUser user = new SystemUser();
        user.setId(1L);
        user.setUsername("Test");

        when(userDAO.findByUsername(user.getUsername())).thenReturn(Optional.of(user));

        assertEquals(1L, userService.findUserIdByUsername("Test"));
    }

    @Test
    void shoudAddNewUser() {
        NewUserDTO newUserDTO = new NewUserDTO();
        newUserDTO.setUsername("Test");
        newUserDTO.setGrossDebtors(0L);
        newUserDTO.setGrossCreditors(0L);
        newUserDTO.setNetDebtors(0L);
        newUserDTO.setNetCreditors(0L);

        assertEquals(ResponseStatus.OK, userService.addNewUser(newUserDTO));
    }

    @Test
    void shouldViewUser() {
        ViewUserDTO viewUserDTO = new ViewUserDTO();
        viewUserDTO.setUsername("Test");

        SystemUser user = new SystemUser();
        user.setId(1L);
        user.setUsername("Test");
        user.setGrossDebtors(0L);
        user.setGrossCreditors(0L);
        user.setNetDebtors(0L);
        user.setNetCreditors(0L);
        user.setBalance(0L);

        when(userDAO.findByUsername(user.getUsername())).thenReturn(Optional.of(user));
        when(userDAO.findById(user.getId())).thenReturn(Optional.of(user));

        assertEquals(user, userService.viewUser(viewUserDTO));
    }

    @Test
    void shouldDisplayAllUsers() {
        SystemUser user = new SystemUser();
        user.setId(1L);
        user.setUsername("Test");
        user.setGrossDebtors(0L);
        user.setGrossCreditors(0L);
        user.setNetDebtors(0L);
        user.setNetCreditors(0L);
        user.setBalance(0L);

        when(userDAO.findAll()).thenReturn(Collections.singleton(user));

        assertFalse(userService.displayAllUsers().isEmpty());
    }
}