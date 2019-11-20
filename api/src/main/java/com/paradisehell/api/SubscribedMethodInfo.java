package com.paradisehell.api;

import java.lang.reflect.Method;

/**
 * 被注册的方法的信息
 *
 * @author ChengTao <a href="mailto:tao@paradisehell.org">Contact me.</a>
 * @since 2019/11/20 10:11
 */
public final class SubscribedMethodInfo {
  //<editor-fold desc="属性">
  private Class<?> mParameterType;
  private Method mMethod;
  //</editor-fold>

  public SubscribedMethodInfo(Class<?> parameterType, Method method) {
    mParameterType = parameterType;
    mMethod = method;
  }

  //<editor-fold desc="Getter">
  Class<?> getParameterType() {
    return mParameterType;
  }

  Method getMethod() {
    return mMethod;
  }
  //</editor-fold>
}
