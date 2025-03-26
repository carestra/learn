package se.carestra.learn.spring.jdbc.postgresqldriver.repository;

import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

public final class TransactionalProxy {
  public static Object build(TransactionTemplate transactionTemplate, Object target) {
    var proxyFactoryBean = new ProxyFactoryBean();
    proxyFactoryBean.setTarget(target);
    proxyFactoryBean.setProxyTargetClass(true);
    proxyFactoryBean.addAdvice(
        (MethodInterceptor) invocation -> {
          var method = invocation.getMethod();
          var transactional = method.getAnnotation(Transactional.class);
          var resultForProxy =
              (transactional == null) ? method.invoke(target, invocation.getArguments()) :
                  transactionTemplate.execute(
                      status -> {
                        Object result = null;
                        try {
                          System.out.println("Invoking transactional method: " + method.getName());
                          result = method.invoke(target, invocation.getArguments());
                        } catch (Throwable e) {
                          throw new IllegalArgumentException(e);
                        }
                        return result;
                      }
                  );
          return resultForProxy;
        });
    return proxyFactoryBean.getObject();
  }
}
