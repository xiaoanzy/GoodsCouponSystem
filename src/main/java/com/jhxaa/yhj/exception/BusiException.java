package com.jhxaa.yhj.exception;

import com.jhxaa.yhj.pojo.ErrorMessage;

public class BusiException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private int retCode; // 异常对应的返回码
    private String retMsg; // 异常对应的描述信息

    public BusiException() {
        super();
    }

    public BusiException(String message) {
        super(message);
        retMsg = message;
    }

    public BusiException(int retCode, String retMsg) {
        super();
        this.retCode = retCode;
        this.retMsg = retMsg;
    }

    public BusiException(int retCode, String retMsg, Throwable cause) {
        super(retMsg, cause);
        this.retCode = retCode;
        this.retMsg = retMsg;
    }


    public BusiException(ErrorMessage errorMessage) {
        super();
        this.retCode = errorMessage.getCode();
        this.retMsg = errorMessage.getMessage();
    }

    public BusiException(ErrorMessage errorMessage, Throwable cause) {
        super(errorMessage.getMessage(), cause);
        this.retCode = errorMessage.getCode();
        this.retMsg = errorMessage.getMessage();
    }

    public int getRetCode() {
        return retCode;
    }

    public String getRetMsg() {
        return retMsg;
    }

    @Override
    public String getMessage() {
        return retMsg;
    }

}
