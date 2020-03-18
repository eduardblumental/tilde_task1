package lv.tilde.eduards.task1.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lv.tilde.eduards.task1.Enums.TransactionType;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionDTO {
    private Long amount;
    private TransactionType transactionType;
    private String senderUsername;
    private String receiverUsername;
}
