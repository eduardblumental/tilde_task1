package lv.tilde.eduards.task1.exceptions;

import lombok.Data;
import lv.tilde.eduards.task1.enums.ResponseStatus;

@Data
public class ApplicationError {
    private ResponseStatus status;
    private String reason;
}
