package lv.tilde.eduards.task1.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lv.tilde.eduards.task1.enums.TransactionType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionListDTO {
    @NotNull
    private TransactionType transactionType;
    @NotEmpty
    private String username;
}
