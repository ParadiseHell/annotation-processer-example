package com.paradisehell.api;

/**
 * ISubscriberInfo 简单实现
 *
 * @author ChengTao <a href="mailto:tao@paradisehell.org">Contact me.</a>
 * @since 2019/11/21 00:13
 */
public final class SimpleSubscriberInfo implements ISubscriberInfo {
  //<editor-fold desc="属性">
  private SubscriberMethodInfo[] mMethodInfos;
  //</editor-fold>

  //<editor-fold desc="构造函数">

  public SimpleSubscriberInfo(SubscriberMethodInfo[] methodInfos) {
    mMethodInfos = methodInfos;
  }

  //</editor-fold>

  //<editor-fold desc="方法实现">

  @Override
  public SubscriberMethodInfo[] getSubscribedMethodInfos() {
    return mMethodInfos;
  }
  //</editor-fold>
}
