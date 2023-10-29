package project.backend.exception;

//예외처리를 위한 클래스
public class BusinessException extends RuntimeException{
    public BusinessException(ExMessage exMessage) {
        super(exMessage.getMessage());
    }

    public BusinessException(String message) {
        super(message);
    }
}
