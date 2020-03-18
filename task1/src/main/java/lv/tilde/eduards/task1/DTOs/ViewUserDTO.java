package lv.tilde.eduards.task1.DTOs;

import javax.validation.constraints.NotEmpty;

public class ViewUserDTO {

    @NotEmpty
    private String username;
}
