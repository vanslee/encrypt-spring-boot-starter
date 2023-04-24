package com.ldx.encrypt.result;

/**
 * @author Uaena
 * @date 2023/4/24 20:44
 */
public class ServiceException extends RuntimeException{

    private int code;

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(int code) {
        super("Unknown error");
        this.code = code;
    }

    public ServiceException(int code, String message) {
        super(message);
        this.code = code;
    }

    public ServiceException(Res res) {
        super(res.getMsg());
        this.code = res.getCode();
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

}