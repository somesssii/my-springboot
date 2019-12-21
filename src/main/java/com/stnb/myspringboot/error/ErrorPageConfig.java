package com.stnb.myspringboot.error;

import org.springframework.boot.web.server.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * 自定义错误页面
 * @author 11299
 */
@Component
public class ErrorPageConfig implements ErrorPageRegistrar {

    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND,"/404.html");
        registry.addErrorPages(error404Page);
    }
}
