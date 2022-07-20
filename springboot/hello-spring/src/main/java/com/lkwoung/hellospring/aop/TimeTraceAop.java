package com.lkwoung.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeTraceAop {

    @Around("execution(* com.lkwoung.hellospring..*(..))") // 범위 지정(문법 참고)
    // @Around("execution(* com.lkwoung.hellospring.service..*(..))") // 서비스 하위만
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
        long start = System.currentTimeMillis();
        System.out.println("START : " + joinPoint.toString());
        try{
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("START : " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }
}
