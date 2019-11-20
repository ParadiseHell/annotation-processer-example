package com.paradisehell.api;

import java.util.List;

/**
 * 创建者索引接口
 *
 * @author ChengTao <a href="mailto:tao@paradisehell.org">Contact me.</a>
 * @since 2019/11/20 10:07
 */
public interface ISubscriberIndex {
  /**
   * 获取注册者的类型
   *
   * @return 注册者的类型
   */
  Class<?> getSubscriberType();

  /**
   * 获取注册的方法信息列表
   *
   * @return 方法信息列表
   */
  List<SubscribedMethodInfo> getSubscribedMethods();
}
