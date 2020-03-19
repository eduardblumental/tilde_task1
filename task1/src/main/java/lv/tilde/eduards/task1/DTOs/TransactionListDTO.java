package lv.tilde.eduards.task1.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lv.tilde.eduards.task1.enums.TransactionType;

import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionListDTO {
    @NotEmpty
    private TransactionType transactionType;
    @NotEmpty
    private String username;
}
