package life.ledon.community.exception;

public class CustomizeException extends RuntimeException {
    private String massage;
    private Integer code;

    public CustomizeException(ICustomizeErrorCode errorCode) {
        this.massage = errorCode.getMessage();
        this.code = errorCode.getCode();
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return massage;
    }
}
