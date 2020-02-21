package com.chao.weblog.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 自定义异常处理器
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    private  final  Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandler(HttpServletRequest request, Exception e) throws Exception {
        logger.error("Request URL:{} Exception:",request.getRequestURL(),e);

        if (AnnotationUtils.findAnnotation(e.getClass(),ResponseStatus.class)!=null) {
            throw e;
        }

        ModelAndView view = new ModelAndView();
        view.addObject("url",request.getRequestURL());
        view.addObject("exception",e);
        view.setViewName("/error/error");
        return view;
    }

}
