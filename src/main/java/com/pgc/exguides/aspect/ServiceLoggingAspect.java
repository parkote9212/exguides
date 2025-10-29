package com.pgc.exguides.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j // 1. Lombok으로 Logger 자동 주입
@Aspect // 2. 이 클래스가 AOP Aspect임을 선언
@Component // 3. Spring이 이 클래스를 Bean으로 스캔하도록 선언
public class ServiceLoggingAspect {

    /**
     * @Around: 메소드 실행 전/후/성공/예외를 모두 감싸는 어드바이스
     * * Pointcut (대상 지정):
     * "execution(* com.pgc.exguides.service.*.*(..))"
     * - com.pgc.exguides.service 패키지 안에 있는
     * - 모든 클래스(*)의
     * - 모든 메소드(*)(파라미터는 상관없음(..))
     * 가 실행될 때 이 AOP를 적용합니다.
     */
    @Around("execution(* com.pgc.exguides.service.*.*(..))")
    public Object measureTimeAndLog(ProceedingJoinPoint pjp) throws Throwable {

        // 1. @Before (메소드 실행 전)
        log.info("--- 🚀 [AOP] 실행 시작: {}", pjp.getSignature().toShortString());
        long start = System.currentTimeMillis(); // 성능 측정 시작

        try {
            // 2. ★★★ 원본 메소드 실행 (예: StudentService.createStudent(...))
            Object result = pjp.proceed();

            // 3. @AfterReturning (메소드 성공 시)
            long end = System.currentTimeMillis();
            log.info("--- ✅ [AOP] 실행 성공: {}, 실행 시간: {}ms", pjp.getSignature().toShortString(), (end - start));
            log.info("--- ➡️ [AOP] 반환 값: {}", result);

            return result; // 원본 메소드의 반환 값을 그대로 반환

        } catch (Throwable e) {
            // 4. @AfterThrowing (메소드 예외 시)
            long end = System.currentTimeMillis();
            log.error("--- 🚨 [AOP] 예외 발생: {}, 실행 시간: {}ms", pjp.getSignature().toShortString(), (end - start));
            log.error("--- ❗️ [AOP] 예외 메시지: {}", e.getMessage());

            throw e; // 5. (중요) 예외를 다시 던져서 상위(컨트롤러)로 전파
        }
    }
}