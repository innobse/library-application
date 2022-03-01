package ru.learnup.vtb.library.libraryapplication.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * Description
 *
 * @author bse71
 * Created on 16.02.2022
 * @since
 */
@Component
@Aspect
public class LoggerAspect {

    @Pointcut("@annotation(ru.learnup.vtb.library.libraryapplication.annotations.Loggable)")
    public void bookServiceLog() {}

    @Around("bookServiceLog()")
    public void around(ProceedingJoinPoint point) {
        String methodName = point.getSignature().getName() + "()";
        print("Готовимся к выполнению метода " + methodName);

        try {
            point.proceed(new Object[]{"Мастер и маргарита"});
            print("Завершилась работа метода " + methodName);
        } catch (Throwable throwable) {
            print("Метод " + methodName + " завершился с ошибкой");
        }
    }

    @AfterReturning("bookServiceLog()")
    public void afterSuccess(JoinPoint point) {
        print("Завершилась работа метода " + point.getSignature().getName());
    }

    @AfterThrowing("bookServiceLog()")
    public void afterError(JoinPoint point) {
        print("Метод " + point.getSignature().getName() + "() завершился с ошибкой");
    }

    @Before("bookServiceLog()")
    public void before(JoinPoint point) {
        print("Готовимся к выполнению метода " + point.getSignature().getName());
    }

    public void print(String msg) {
        System.out.println(msg);
    }
}
