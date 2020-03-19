package lv.tilde.eduards.task1.controllers;

import lv.tilde.eduards.task1.DTOs.NewTransactionDTO;
import lv.tilde.eduards.task1.DTOs.TransactionListDTO;
import lv.tilde.eduards.task1.enums.ResponseStatus;
import lv.tilde.eduards.task1.exceptions.CustomBadRequestException;
import lv.tilde.eduards.task1.objects.Transaction;
import lv.tilde.eduards.task1.services.TransactionService;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping (value = "/new-transaction", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseStatus newTransaction (@RequestBody @Valid NewTransactionDTO newTransactionDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new CustomBadRequestException("Wrong Input. Please try again.");
        }

        return transactionService.executeNewTransaction(newTransactionDTO);
    }

    @PostMapping (value = "/transaction-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Transaction> transactionList (@RequestBody @Valid TransactionListDTO transactionListDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new CustomBadRequestException("Wrong Input. Please try again.");
        }

        return transactionService.displayTransactions(transactionListDTO);
    }

}
