package com.mongoing.compensation;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author Maxim Kalina
 * @version $Id$
 */
@Component
public class CompensatableAdvisor extends AbstractPointcutAdvisor {

    private static final long serialVersionUID = 1L;

    private final StaticMethodMatcherPointcut pointcut = new
            StaticMethodMatcherPointcut() {
                @Override
                public boolean matches(Method method, Class<?> targetClass) {
                    return method.isAnnotationPresent(Compensatable.class);
                }
            };

    @Autowired
    private CompensatableMethodInterceptor interceptor;

    @Override
    public Pointcut getPointcut() {
        return this.pointcut;
    }

    @Override
    public Advice getAdvice() {
        return this.interceptor;
    }
}
