package com.hope.blog.log.handle;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.hope.blog.log.mapper.SysExceptionLogMapper;
import com.hope.blog.log.model.SysExceptionLog;
import com.hope.blog.log.model.SysLog;
import com.hope.blog.common.security.config.AuthUserDetails;
import com.hope.blog.utils.HttpContextUtil;
import com.hope.blog.utils.IPUtil;
import com.hope.blog.utils.ThrowableUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lijin on  2021/6/22
 */
@Aspect
@Component
@Slf4j
public class SysLogAspect {


    @Autowired
    private SysLogHandle sysLogHandle;

    @Autowired
    private SysExceptionLogMapper sysExceptionLogMapper;

    /**
     * 开始时间
     */
    Date startTime;

    @Pointcut("@annotation(operationLog)")
    public void pointcut(OperationLog operationLog) {

    }

    @Around(value = "pointcut(operationLog)", argNames = "joinPoint,operationLog")
    public Object doAround(ProceedingJoinPoint joinPoint, OperationLog operationLog) throws Throwable {

        startTime = new Date();

        // 先执行业务
        Object result = joinPoint.proceed();

        try {
            handle(joinPoint);

        } catch (Exception e) {
            log.error("日志记录出错!", e);
        }

        return result;
    }

    @AfterThrowing(value = "pointcut(operationLog)", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, OperationLog operationLog, Throwable e) throws Exception {
        HttpServletRequest request = HttpContextUtil.getRequest();
        String ip = IPUtil.getIpAddr(request);
        SysExceptionLog exception = new SysExceptionLog();
        exception.setIp(ip);
        exception.setIpAddress(IPUtil.getCityInfo(ip));
        //设置调用的方法
        exception.setMethod(joinPoint.getSignature().getName());
        exception.setParams(JSONObject.toJSONString(getFieldsName(joinPoint)));
        exception.setUrl(request.getRequestURI());
        exception.setExceptionJson(ThrowableUtil.getStackTrace(e));
        exception.setExceptionMessage(e.getMessage());
        AuthUserDetails securityUser = (AuthUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        exception.setUserName(securityUser.getUsername());
        exception.setUserId(securityUser.getUid());
        exception.setOperation(operationLog.value());
        exception.setCreateTime(new Date());
        sysExceptionLogMapper.insert(exception);
    }

    private void handle(ProceedingJoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        if (!(signature instanceof MethodSignature)) {
            throw new IllegalArgumentException("注解只能用于方法上");
        }
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        OperationLog operationLog = method.getAnnotation(OperationLog.class);
        // 如果用线程池异步，可能出现request为空的情况
        HttpServletRequest request = HttpContextUtil.getRequest();
        // 请求的IP
        String ip = IPUtil.getIpAddr(request);
        // 获取类名
        String targetName = joinPoint.getTarget().getClass().getName();
        // 获取方法名
        String methodName = signature.getName();
        // Request的请求方式 GET POST
        SysLog sysLog = new SysLog();
        sysLog.setClassPath(targetName);
        sysLog.setMethod(methodName);
        sysLog.setIp(ip);
        sysLog.setIpAddress(IPUtil.getCityInfo(ip));
        sysLog.setType(request.getMethod());
        sysLog.setUrl(request.getRequestURI());
        sysLog.setOperation(operationLog.value());
        sysLog.setCreateTime(new Date());
        AuthUserDetails securityUser = (AuthUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        sysLog.setUserName(securityUser.getUsername());
        sysLog.setUserId(securityUser.getUid());
        sysLog.setParams(JSONObject.toJSONString(getFieldsName(joinPoint)));
        Date endTime = new Date();
        Long spendTime = DateUtil.between(startTime, endTime, DateUnit.MS);
        // 计算请求接口花费的时间，单位毫秒
        sysLog.setSpendTime(spendTime);


        // 异步存储日志 此种方式用来解决异步线程下request为空的情况
        sysLogHandle.setSysLogHandle(sysLog);

        sysLogHandle.onRun();
    }

    /**
     * 获取参数名和值
     *
     * @param joinPoint
     * @return
     */
    public static Map<String, Object> getFieldsName(ProceedingJoinPoint joinPoint) {
        // 参数值
        Object[] args = joinPoint.getArgs();

        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        String[] parameterNames = methodSignature.getParameterNames();

        // 通过map封装参数和参数值
        HashMap<String, Object> paramMap = new HashMap<String, Object>();
        for (int i = 0; i < parameterNames.length; i++) {
            paramMap.put(parameterNames[i], args[i]);
        }
        return paramMap;
    }

    /**
     * 获取参数名和值
     *
     * @param joinPoint
     * @return
     */
    public static Map<String, Object> getFieldsName(JoinPoint joinPoint) {
        // 参数值
        Object[] args = joinPoint.getArgs();

        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        String[] parameterNames = methodSignature.getParameterNames();

        // 通过map封装参数和参数值
        HashMap<String, Object> paramMap = new HashMap<String, Object>();
        for (int i = 0; i < parameterNames.length; i++) {
            paramMap.put(parameterNames[i], args[i]);
        }
        return paramMap;
    }
}
