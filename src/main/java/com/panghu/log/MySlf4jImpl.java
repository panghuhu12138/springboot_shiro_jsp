package com.panghu.log;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.logging.Log;

/**
 * @author jiafupeng
 * @create 2020/5/26 13:31
 * @desc   自定义mybatis打印sql实现类
 *         将debug日志输出成info日志 对sql进行
 **/
@Slf4j
public class MySlf4jImpl implements Log {

    public MySlf4jImpl(String clazz) {
        // Do Nothing
    }

    @Override
    public boolean isDebugEnabled() {
        // return log.isDebugEnabled();
        // 将debug级别输出权限改成info级别
        return log.isInfoEnabled();

    }

    @Override
    public boolean isTraceEnabled() {
        return false;
    }

    @Override
    public void error(String s, Throwable e) {
        log.error(s);
    }

    @Override
    public void error(String s) {
        log.error(s);
    }

    @Override
    public void debug(String s) {
        // log.debug(s);
        // debug日志输出成info级别日志
        log.info(s);
    }

    @Override
    public void trace(String s) {

    }

    @Override
    public void warn(String s) {
        log.warn(s);
    }
}
