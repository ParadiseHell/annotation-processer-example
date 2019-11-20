package com.paradisehell.api;

/**
 * 订阅者索引接口
 *
 * @author ChengTao <a href="mailto:tao@paradisehell.org">Contact me.</a>
 * @since 2019/11/21 00:25
 */
public interface ISubscriberIndex {
  /**
   * 获取订阅者信息
   *
   * @param subscriberClass 订阅者类型
   * @return 订阅者信息
   */
  ISubscriberInfo getSubscriberInfo(Class<?> subscriberClass);
}
