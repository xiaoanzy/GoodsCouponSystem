package com.jhxaa.yhj.exception;

import com.jhxaa.yhj.pojo.ErrorMessage;
import com.jhxaa.yhj.utli.LogUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
//@Slf4j
public class MyExceptionHandler {

    static Logger LOG = LogUtil.getLog(MyExceptionHandler.class);


    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ErrorMessage errorHandler(Exception ex) {
        //  log.error("系统执行异常,异常消息为：" + ex.getMessage());
        ex.printStackTrace();
        return ExceptionEnum.SYSTEM_ERROR.value();
    }

    @ExceptionHandler(value = BusiException.class)
    @ResponseBody
    public ErrorMessage handleBusinessException(BusiException e) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setCode(e.getRetCode());
        errorMessage.setMessage(e.getMessage());
        return errorMessage;
    }

    @ExceptionHandler(value = MalformedJwtException.class)
    @ResponseBody
    public ErrorMessage handleMalformedJwtException(MalformedJwtException e) {
        LOG.error(String.format("%s异常[%s]", e.getClass().getSimpleName(), e.getMessage()), e);
        return ExceptionEnum.ILLEGAL_TOKEN.value();
    }

    @ExceptionHandler(value = SignatureException.class)
    @ResponseBody
    public ErrorMessage handleSignatureException(SignatureException e) {
        LOG.error(String.format("%s异常[%s]", e.getClass().getSimpleName(), e.getMessage()), e);
        return ExceptionEnum.ILLEGAL_TOKEN.value();
    }

    @ExceptionHandler(value = ExpiredJwtException.class)
    @ResponseBody
    public ErrorMessage handleExpiredJwtException(ExpiredJwtException e) {
        LOG.error(String.format("%s异常[%s]", e.getClass().getSimpleName(), e.getMessage()), e);
        return ExceptionEnum.FAILURE_TOKEN.value();
    }


    @ExceptionHandler(value = JobExecutionException.class)
    public void handleJobExecutionException(JobExecutionException e) {
        LOG.error(String.format("%s异常[%s]", e.getClass().getSimpleName(), e.getMessage()), e);
        System.gc();
        System.runFinalization();
        System.gc();
    }
}
