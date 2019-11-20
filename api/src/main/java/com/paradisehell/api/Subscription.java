package com.paradisehell.api;

import java.lang.reflect.Method;

/**
 * 订阅者信息
 *
 * @author ChengTao <a href="mailto:tao@paradisehell.org">Contact me.</a>
 * @since 2019/11/20 10:05
 */
final class Subscription {
  Object subscriber;
  Method method;

  Subscription(Object subscriber, Method method) {
    this.subscriber = subscriber;
    this.method = method;
  }
}