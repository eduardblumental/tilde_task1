package lv.tilde.eduards.task1.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lv.tilde.eduards.task1.enums.TransactionType;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NewTransactionDTO {

    @NotEmpty
    @NotNull
    private Long amount;
    @NotEmpty
    private TransactionType transactionType;
    @NotEmpty
    private String senderUsername;
    @NotEmpty
    private String receiverUsername;
}
