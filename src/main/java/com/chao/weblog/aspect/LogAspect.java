package com.chao.weblog.aspect;

import net.bytebuddy.asm.Advice;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Component
@Aspect
public class LogAspect {

    @Autowired
    private HttpServletRequest request;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.chao.weblog.web.*..*(..))")
    public void  log(){

    }

    @Before("log()")
    public void doBefore(JoinPoint point){
        logger.info("----------doBefore--------");
        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();
        String classMethod  = point.getSignature().getDeclaringTypeName()+"."+point.getSignature().getName();
        Object[] objects = point.getArgs();
        logger.info("Request:{}",new RequestLog(url,ip,classMethod,objects).toString());
    }

    @After("log()")
    public void doAfter(){
        logger.info("----------doAfter--------");
    }

    @AfterReturning(returning = "result",pointcut = "log()")
    public void getAfterReturn(Object result){
        logger.info("Result:{}",result);
    }

    private class RequestLog{
        private String url;
        private String ip;
        private String classMethod;
        private Object[]  objects;

        public RequestLog(String url, String ip, String classMethod, Object[] objects) {
            this.url = url;
            this.ip = ip;
            this.classMethod = classMethod;
            this.objects = objects;
        }

        @Override
        public String toString() {
            return "{" +
                    "host='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", ClassMethod='" + classMethod + '\'' +
                    ", objects=" + Arrays.toString(objects) +
                    '}';
        }
    }

}
