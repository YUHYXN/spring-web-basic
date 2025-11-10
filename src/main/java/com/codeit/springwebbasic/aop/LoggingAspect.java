package com.codeit.springwebbasic.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Aspect // 이 클래스는 AOP담당자(Aspect) 라고 선언
@Component  // 빈 등록
public class LoggingAspect {

    // 1. Pointcut (어디에?? 공통기능을 동작시킬지?)
    // execution([수식어] 리턴타입 [클래스경로.]메서드이름(파라미터) [예외]) - []는 생략 가능한 문법
    // execution: 메서드 실행 시점 () << 여기가 어디서인지 위치를 정의 member.*(..) -> member 패키지의 모든 클래스의 모든 메서드
    // 아래는 MemberController 클래스의 createMember 메서드가 실행될 때를 지정
    // @Pointcut("execution(* com.codeit.springwebbasic.member.controller.MemberController.createMember(..))")
    // 모든 접근 제한자 허용, 모든 리턴타입 허용, MemberController 안에 있는 모든 메서드를 대상 (매개 값은 모든 파라미터)
    // @Pointcut("execution(* com.codeit.springwebbasic.*.*(..))")
    // .. : 0개 이상의 하위 패키지 포함 모든 하위 패키지를 전부 지목하고 싶을 때

    // com.codeit.springwebbasic의 모든 하위 패키지의 Controller...
    // @Pointcut("execution(* com.codeit.springwebbasic.*.controller.MemberController.createMember(..))")


    @Pointcut("execution(* com.codeit.springwebbasic.member.controller.MemberController.createMember(..))")
    private void allControllerMethods() {
        // 위에 지정한 (어디에?) 라는 메서드 위치에 사전에 지정해야 할 여러 설정, 사전 작업 등을 명시한다.
        // @pointcut을 생략하고, @Around에 바로 execution을 넣어도 되지만, 이렇게 분리하면 재사용성이 높아진다.
        System.out.println("allControllerMethods 메서드 호출");
    }

    // 2. Advice (무엇을>): Pointcut에 지정한 곳 주변(어라운드)에서 이 코드를 실행
    @Around("execution(* com.codeit.springwebbasic.member.controller.MemberController.createMember(..))")
    public Object logControllerCheck(ProceedingJoinPoint joinPoint) throws Throwable {
        // proceedingJoinPoint: 이 AOP가 적용되는 지점에 대한 정보를 담고있는 객체.

        // 3. 공통 기능 구현 (시작)
        long startTime = System.currentTimeMillis();
        String methodName = joinPoint.getSignature().getName(); // 메서드 이름
        Object[] args = joinPoint.getArgs();    // 메서드에 전달된 매개값들
        Signature signature = joinPoint.getSignature();
        System.out.println("시그니처 = " + signature);

//        signature.getDeclaringTypeName();   패키지 _ 클래스 이름 (getPackageName(), getSimpleName() 등도 제공)
//        joinPoint.getTarget(); 실제 대상 객체(Bean) 가져오기


        System.out.println("메서드 이름: " + methodName);
        System.out.println("매개변수: " + Arrays.toString(args));


        // 4. 핵심 기능 실행 (원래의 메서드의 기능을 실행하라!)
        Object result = joinPoint.proceed();


        // 5. 공통 기능 (종료 및 로그)
        Long endTime = System.currentTimeMillis();
        System.out.println("메서드 실행 시간: " + (endTime - startTime) + "ms");


        return result; // 원래 메서드의 리턴값을 그대로 반환
    }

    // @Before: 핵심 기능이 실행되기 직전까지만 딱 실행됨.
    // 얘는 따로 proceed() 같은 걸 호출할 필요가 없음.

    // @AfterReturning: 메서드 정상 종료 이후 실행할 내용

    // 저 위의 두 개를 한꺼번에 아우를 수 있는 기능이 @Around

}
