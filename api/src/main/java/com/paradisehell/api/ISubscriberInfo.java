package com.paradisehell.api;

/**
 * 创建者信息接口
 *
 * @author ChengTao <a href="mailto:tao@paradisehell.org">Contact me.</a>
 * @since 2019/11/20 10:07
 */
public interface ISubscriberInfo {

  /**
   * 获取注册的方法信息数组
   *
   * @return 方法信息列表
   */
  SubscriberMethodInfo[] getSubscribedMethodInfos();
}
