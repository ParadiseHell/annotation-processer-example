package com.paradisehell.api;

/**
 * 被注册的方法的信息
 *
 * @author ChengTao <a href="mailto:tao@paradisehell.org">Contact me.</a>
 * @since 2019/11/20 10:11
 */
public final class SubscriberMethodInfo {
  //<editor-fold desc="属性">
  private String mMethodName;
  private Class<?> mParameterType;
  //</editor-fold>

  public SubscriberMethodInfo(String methodName, Class<?> parameterType) {
    mMethodName = methodName;
    mParameterType = parameterType;
  }

  //<editor-fold desc="Getter">

  String getMethodName() {
    return mMethodName;
  }

  Class<?> getParameterType() {
    return mParameterType;
  }
  //</editor-fold>
}
