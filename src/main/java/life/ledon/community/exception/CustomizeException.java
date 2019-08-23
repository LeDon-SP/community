package life.ledon.community.exception;

public class CustomizeException extends RuntimeException {
    private String massage;

    public CustomizeException(ICustomizeErrorCode errorCode){
        this.massage = errorCode.getMessage();
    }
    public CustomizeException(String massage){
        this.massage = massage;
    }

    @Override
    public String getMessage() {
        return massage;
    }
}
