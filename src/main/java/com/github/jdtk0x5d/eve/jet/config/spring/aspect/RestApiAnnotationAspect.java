package com.github.jdtk0x5d.eve.jet.config.spring.aspect;

import com.github.jdtk0x5d.eve.jet.api.RestResponse;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;

/**
 * @author Tigran_Dadaiants dtkcommon@gmail.com
 */
@Aspect
@Component
public class RestApiAnnotationAspect {

  @Around("@within(com.github.jdtk0x5d.eve.jet.config.spring.annotations.RestApi)" +
      " && execution(public com.github.jdtk0x5d.eve.jet.api.RestResponse *(..))")
  public Object annotationPointcut(ProceedingJoinPoint joinPoint) throws Throwable {
    try {
      return joinPoint.proceed();
    } catch (HttpStatusCodeException e) {
      return new RestResponse(e);
    }
  }
}