package lv.tilde.eduards.task1.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NewUserDTO {

    @NotEmpty
    private String username;
    private Long grossDebtors;
    private Long grossCreditors;
    private Long netDebtors;
    private Long netCreditors;
}
