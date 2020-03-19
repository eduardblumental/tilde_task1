package lv.tilde.eduards.task1.DAOs;

import lv.tilde.eduards.task1.objects.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionDAO extends CrudRepository<Transaction, Long> {

}
