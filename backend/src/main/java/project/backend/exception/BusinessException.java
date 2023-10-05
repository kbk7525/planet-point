package project.backend.exception;

public class BusinessException extends RuntimeException{
    public BusinessException(ExMessage exMessage) {
        super(exMessage.getMessage());
    }

    public BusinessException(String message) {
        super(message);
    }
}
