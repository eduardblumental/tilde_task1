package lv.tilde.eduards.task1.exceptions;

public class CustomBadRequestException extends  RuntimeException {

    public CustomBadRequestException(String message) {
        super(message);
    }
}
