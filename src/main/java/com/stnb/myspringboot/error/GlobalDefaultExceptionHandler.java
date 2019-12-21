package com.stnb.myspringboot.error;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 统一业务异常处理类
 * @author 11299
 */
@RestControllerAdvice(basePackages = {"com.stnb.myspringboot",})
public class GlobalDefaultExceptionHandler {

    @ExceptionHandler({BusinessException.class})
    /**
     * 如果返回的为json数据或其他对象，就添加该注解
     */
    @ResponseBody
    public ErrorInfo defaultErrorHandler(HttpServletRequest request, Exception e) throws Exception {
        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setMessage(e.getMessage());
        errorInfo.setUrl(request.getRequestURI());
        errorInfo.setCode(ErrorInfo.SUCCESS);

        return errorInfo;
    }
}
