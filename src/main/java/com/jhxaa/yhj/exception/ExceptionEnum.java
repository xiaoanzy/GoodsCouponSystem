package com.jhxaa.yhj.exception;

import com.jhxaa.yhj.pojo.ErrorMessage;

public enum ExceptionEnum {


    SYSTEM_ERROR(10000, "系统错误"),
    SYSTEM_SIGN_ERROR(10001, "签名错误"),
    HTTP_REQUEST_ERROR_400(10002, "请求参数有误"),
    SERVICE_PARAM_EMPTY(10003, "参数不能为空"),
    DB_REPEAT_DATA(10004, "重复提交"),
    DB_UPDATE_ERRROR(10005, "更新DB异常"),
    USER_CHECK_FAIL(10006, "账号或密码错误"),
    USER_CHECK_EMPTY_FAIL(10007, "账号或密码不能为空"),
    CREATE_TOKEN_FAIL(10008, "生成token失败，请稍后再试"),
    ILLEGAL_TOKEN(10009, "非法token"),
    NOT_LOGIN(10010, "未登录"),
    FAILURE_TOKEN(10011, "token时效已过期"),
    HTTP_REQUEST_ERROR_FREQUENCY(10012, "请求频率过快"),
    SYSTEM_PARAMS_ERROR(10013, "非法参数"),

    //    HTTP_REQUEST_ERROR_500(10404,"找不到页面"),
    HTTP_REQUEST_ERROR_404(10404, "找不到页面"),
    HTTP_REQUEST_ERROR_403(10403, "请求被拒接"),
    ;
//request

    int code;
    String message;

    ExceptionEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ErrorMessage value() {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setCode(this.code);
        errorMessage.setMessage(this.message);
        return errorMessage;
    }
}
