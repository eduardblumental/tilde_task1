package lv.tilde.eduards.task1.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NewUserDTO {

    @NotEmpty
    private String username;
    @NotNull
    private Long grossDebtors;
    @NotNull
    private Long grossCreditors;
    @NotNull
    private Long netDebtors;
    @NotNull
    private Long netCreditors;
}
