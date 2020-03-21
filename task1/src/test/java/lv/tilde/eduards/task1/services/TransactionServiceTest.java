package lv.tilde.eduards.task1.services;

import lv.tilde.eduards.task1.DAOs.TransactionDAO;
import lv.tilde.eduards.task1.DAOs.UserDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({MockitoExtension.class})
class TransactionServiceTest {

    TransactionService transactionService;

    @Mock
    private TransactionDAO transactionDAO;

    @Mock
    private UserDAO userDAO;

    @Mock
    private UserService userService;

    @BeforeEach
    void setUp(){
        transactionService = new TransactionService(transactionDAO,userDAO,userService);
    }


    @Test
    void executeNewTransaction() {
        
    }

    @Test
    void displayTransactions() {
    }
}