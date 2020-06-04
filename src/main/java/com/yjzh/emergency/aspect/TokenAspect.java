package com.yjzh.emergency.aspect;

import com.yjzh.emergency.Decrypt;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
/**
 * @author zhangju 卓望信息(北京)
 * @version 1.0
 * @since 1.0
 * 2020/5/11 11:39
 **/
@Slf4j
@Aspect
@Component
@SuppressWarnings({"unused"})
public class TokenAspect {

    public static final String TOKEN_KEY = "token";

    /**
     * checkUrl,keyUrl,tokenScope是通过Spring的@Value注解来获取配置文件中的配置项
     * @Value等同于Spring原先的配置模式中的value
     * <bean id="" class="">
     * 		<property name="" value="">
     * </bean>
     */
    @Value(value = "${jwt.checkUrl}")
    String checkUrl;

    @Value(value = "${jwt.keyUrl}")
    String keyUrl;

    @Value(value = "${jwt.clientId}")
    String tokenScope;

    @Pointcut("@annotation(com.yjzh.emergency.annotation.Token)")
    public void annotationPointcut() {

    }

    @Before("annotationPointcut()")
    public void beforePointcut(JoinPoint joinPoint) {
        // 此处进入到方法前  可以实现一些业务逻辑
    }

    @Around("annotationPointcut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String[] params = methodSignature.getParameterNames();// 获取参数名称
        Object[] args = joinPoint.getArgs();// 获取参数值
        if (null == params || params.length == 0){
            String mes = "Using Token annotation, the token parameter is not passed, and the parameter is not valid.";
            log.info(mes);
            throw new Exception(mes);
        }
        boolean hasToken = false;
        int index = 0;
        for (int i = 0; i < params.length; i++) {
            if (TOKEN_KEY.equals(params[i])) {
                hasToken = true;
                index = i;
                break;
            }
        }
        if (!hasToken){
            String mes = "The token parameter is not included in the requested parameter, the parameter is not valid.";
            log.info(mes);
            throw new Exception(mes);
        }
        this.checkToken(String.valueOf(args[index]));
        return joinPoint.proceed();
    }

    /**
     * 在切入点return内容之后切入内容（可以用来对处理返回值做一些加工处理）
     * @param joinPoint
     */
    @AfterReturning("annotationPointcut()")
    public void doAfterReturning(JoinPoint joinPoint) {
    }

    private void checkToken(String token) {
        Decrypt decrypt = new Decrypt();// 这个类是自己的业务类，主要进行token验证(JWT)
        try {
            decrypt.check(token, checkUrl, keyUrl, tokenScope);
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

}
