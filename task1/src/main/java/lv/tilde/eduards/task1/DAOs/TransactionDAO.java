package lv.tilde.eduards.task1.DAOs;

import lv.tilde.eduards.task1.enums.TransactionType;
import lv.tilde.eduards.task1.objects.Transaction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionDAO extends CrudRepository<Transaction, Long> {
    List<Transaction> findAllByTransactionTypeAndSenderIdOrReceiverId(TransactionType transactionType, Long senderId, Long receiverId);
}
