package lv.tilde.eduards.task1.services;

import lv.tilde.eduards.task1.DAOs.TransactionDAO;
import lv.tilde.eduards.task1.DAOs.UserDAO;
import lv.tilde.eduards.task1.DTOs.NewTransactionDTO;
import lv.tilde.eduards.task1.DTOs.TransactionListDTO;
import lv.tilde.eduards.task1.controllers.UserController;
import lv.tilde.eduards.task1.enums.ResponseStatus;
import lv.tilde.eduards.task1.enums.TransactionType;
import lv.tilde.eduards.task1.exceptions.CustomBadRequestException;
import lv.tilde.eduards.task1.objects.Transaction;
import lv.tilde.eduards.task1.objects.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class TransactionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final TransactionDAO transactionDAO;
    private final UserDAO userDAO;
    private final UserService userService;


    public TransactionService(TransactionDAO transactionDAO, UserDAO userDAO, UserService userService) {
        this.transactionDAO = transactionDAO;
        this.userDAO = userDAO;
        this.userService = userService;
    }

    public ResponseStatus executeNewTransaction(NewTransactionDTO newTransactionDTO){
        userService.usernameDoesNotExist(newTransactionDTO.getSender());
        userService.usernameDoesNotExist(newTransactionDTO.getReceiver());

        Transaction transaction = new Transaction();
        transaction.setDateTime(LocalDateTime.now());
        transaction.setTransactionType(newTransactionDTO.getTransactionType());
        transaction.setAmount(newTransactionDTO.getAmount());
        transaction.setSenderId(userService.findUserIdByUsername(newTransactionDTO.getSender()));
        transaction.setReceiverId(userService.findUserIdByUsername(newTransactionDTO.getReceiver()));
        transactionDAO.save(transaction);

        Optional<User> optionalSender = userDAO.findById(transaction.getSenderId());
        User sender = optionalSender.get();

        Optional<User> optionalReceiver = userDAO.findById(transaction.getReceiverId());
        User receiver = optionalReceiver.get();

        if(transaction.getTransactionType() == TransactionType.LAND_BORROW){
            sender.setGrossDebtors(sender.getGrossDebtors() + transaction.getAmount());
            sender.setNetDebtors(sender.getNetDebtors() + transaction.getAmount());
            receiver.setGrossCreditors(receiver.getGrossCreditors() + transaction.getAmount());
            receiver.setNetCreditors(receiver.getNetCreditors() + transaction.getAmount());

        }   else if(transaction.getTransactionType() == TransactionType.RETURN_RECEIVE){
            sender.setNetCreditors(sender.getNetCreditors() - transaction.getAmount());
            receiver.setNetDebtors(receiver.getNetDebtors() - transaction.getAmount());

        }   else    {
            throw new CustomBadRequestException("Please, enter a valid transaction type.");
        }

        sender.setBalance(sender.getNetDebtors() - sender.getNetCreditors());
        receiver.setBalance(receiver.getNetDebtors() - receiver.getNetCreditors());


        userDAO.save(sender);
        userDAO.save(receiver);

        LOGGER.info("New transaction has been executed.");

        return ResponseStatus.OK;
    }

    public List<Transaction> displayTransactions (TransactionListDTO transactionListDTO){
        TransactionType type = transactionListDTO.getTransactionType();
        Long id = userService.findUserIdByUsername(transactionListDTO.getUsername());

        userService.usernameDoesNotExist(transactionListDTO.getUsername());

        List<Transaction> list = new ArrayList<>();

        for (Transaction transaction : transactionDAO.findAllByTransactionTypeAndSenderIdOrReceiverId(type, id, id)){
            list.add(transaction);
        }

        LOGGER.info("Request to view transactions has been submitted.");

        return list;
    }


}
