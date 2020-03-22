package lv.tilde.eduards.task1.services;

import lv.tilde.eduards.task1.DAOs.TransactionDAO;
import lv.tilde.eduards.task1.DAOs.UserDAO;
import lv.tilde.eduards.task1.DTOs.NewTransactionDTO;
import lv.tilde.eduards.task1.DTOs.TransactionListDTO;
import lv.tilde.eduards.task1.enums.ResponseStatus;
import lv.tilde.eduards.task1.enums.TransactionType;
import lv.tilde.eduards.task1.objects.SystemUser;
import lv.tilde.eduards.task1.objects.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

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
    void ShouldExecuteNewTransaction() {
        SystemUser user = new SystemUser();
        user.setId(2L);
        user.setUsername("Test1");
        user.setGrossDebtors(0L);
        user.setGrossCreditors(0L);
        user.setNetDebtors(0L);
        user.setNetCreditors(0L);
        user.setBalance(0L);

        NewTransactionDTO newTransactionDTO = new NewTransactionDTO();
        newTransactionDTO.setAmount(10L);
        newTransactionDTO.setTransactionType(TransactionType.LAND_BORROW);
        newTransactionDTO.setSender("Test1");
        newTransactionDTO.setReceiver("Test2");

        when(userDAO.findById(any())).thenReturn(Optional.of(user));

        assertEquals(ResponseStatus.OK, transactionService.executeNewTransaction(newTransactionDTO));
    }

    @Test
    void shouldDisplayTransactions() {
        TransactionListDTO transactionListDTO = new TransactionListDTO();
        transactionListDTO.setTransactionType(TransactionType.LAND_BORROW);
        transactionListDTO.setUsername("Test1");

        Transaction transaction = new Transaction();
        transaction.setId(1L);
        transaction.setAmount(10L);
        transaction.setDateTime(LocalDateTime.now());
        transaction.setTransactionType(TransactionType.LAND_BORROW);
        transaction.setSenderId(2L);
        transaction.setReceiverId(3L);

        when(userService.findUserIdByUsername(transactionListDTO.getUsername())).thenReturn(2L);
        when(transactionDAO.findAllByTransactionTypeAndSenderIdOrReceiverId(transaction.getTransactionType(),2L,2L)).thenReturn(Collections.singletonList(transaction));

        assertFalse(transactionService.displayTransactions(transactionListDTO).isEmpty());
    }
}