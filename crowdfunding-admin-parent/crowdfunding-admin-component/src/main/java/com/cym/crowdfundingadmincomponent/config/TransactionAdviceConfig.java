package com.cym.crowdfundingadmincomponent.config;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.interceptor.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * program: ssm
 * Date: 2022-03-29  08:28
 * Author: cym
 * Description: 全局事务管理配置类
 * @author 86152
 */
@Aspect
@Configuration
public class TransactionAdviceConfig {
    /**
     * 事务的超时时间为10秒
     */
    private static final int TX_METHOD_TIMEOUT = 10;
    /**
     *  切入点表达式，指定哪些包的方法启用事务
     */
    private static final String AOP_POINTCUT_EXPRESSION = "execution (* com.cym.atcrowdfunding03admincomponent.service.*.*.*(..))";
    /**
     * 事务管理器
     */
    @Autowired
    private TransactionManager transactionManager;

    @Bean
    public TransactionInterceptor txAdvice ( ) {

        //配置只读事务
        RuleBasedTransactionAttribute readOnlyTx = new RuleBasedTransactionAttribute();
        readOnlyTx.setReadOnly(true);
        readOnlyTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        //事务传播行为，如果不存在事务，则开启一个新的事务
        TransactionInterceptor transactionInterceptor = new TransactionInterceptor();
        //检查是否有异常，如果有异常，则回滚
        RuleBasedTransactionAttribute requiredTx = new RuleBasedTransactionAttribute();
        requiredTx.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
        requiredTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        requiredTx.setTimeout(TX_METHOD_TIMEOUT);
        //配置无规则事务
        RuleBasedTransactionAttribute noTx = new RuleBasedTransactionAttribute();
        Map<String, TransactionAttribute> txMap = new HashMap<>(20);
        //读事务的方法,一般是读取数据库数据的方法
        txMap.put("read*",readOnlyTx);
        txMap.put("select*",readOnlyTx);
        txMap.put("get*",readOnlyTx);
        //无规则事务方法
        txMap.put("noTx",noTx);
        //写事务的方法,一般是写入数据库数据的方法
        txMap.put("add*",requiredTx);
        txMap.put("save*",requiredTx);
        txMap.put("edit*",requiredTx);
        txMap.put("update*",requiredTx);
        txMap.put("del*",requiredTx);
        NameMatchTransactionAttributeSource resource = new NameMatchTransactionAttributeSource();
        resource.setNameMap(txMap);
        return new TransactionInterceptor(transactionManager,resource);
    }

    /**
     * 配置事务切入点表达式
     * @return
     */
    @Bean
    public Advisor txAdviceAdvisor ( ) {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(AOP_POINTCUT_EXPRESSION);
        return new DefaultPointcutAdvisor(pointcut,txAdvice());
    }

}
