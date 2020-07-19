package com.jhxaa.yhj.interceptor;

import com.jhxaa.yhj.common.Common;
import com.jhxaa.yhj.exception.BusiException;
import com.jhxaa.yhj.exception.ExceptionEnum;

import javax.servlet.http.HttpServletResponse;

public class HttpActuator {

    public HttpActuator() {

    }


    public void checkHttpStatusCode(HttpServletResponse response) {
        //   Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        Integer statusCode = response.getStatus();
        System.out.println("--------------------------:" + statusCode);
        if (true) {
            throw new BusiException(statusCode + "");
        }
        if (Common.HTTP_STATUS_CODE_400 == statusCode) {
            throw new BusiException(ExceptionEnum.HTTP_REQUEST_ERROR_400.value());
        } else if (Common.HTTP_STATUS_CODE_403 == statusCode) {
            throw new BusiException(ExceptionEnum.HTTP_REQUEST_ERROR_403.value());
        } else if (Common.HTTP_STATUS_CODE_404 == statusCode) {
            throw new BusiException(ExceptionEnum.HTTP_REQUEST_ERROR_404.value());
        } else if (Common.HTTP_STATUS_CODE_500 == statusCode) {
            throw new BusiException(ExceptionEnum.SYSTEM_ERROR.value());
        }
    }
}
