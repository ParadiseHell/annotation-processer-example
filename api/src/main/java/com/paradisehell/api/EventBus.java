package com.paradisehell.api;

import android.os.Handler;
import android.os.Looper;
import com.paradisehell.api.annotation.SubscribeOnMainThread;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * SubscribeOnMainThread 注解实现类
 *
 * @author ChengTao <a href="mailto:tao@paradisehell.org">Contact me.</a>
 * @since 2019/11/19 18:05
 */
public final class EventBus {
  //<editor-fold desc="单例">
  private static volatile EventBus sInstance;
  //</editor-fold>

  //<editor-fold desc="常量">
  private static final Map<Class<?>, ISubscriberIndex> SUBSCRIBER_INDEX_MAP = new HashMap<>();
  //</editor-fold>

  //<editor-fold desc="属性">
  private Handler mHandler;
  private Map<Class<?>, List<Subscription>> mSubscriptionsByTypeMap;
  //</editor-fold>

  //<editor-fold desc="单例获取">

  private EventBus() {
    mHandler = new Handler(Looper.getMainLooper());
    mSubscriptionsByTypeMap = new ConcurrentHashMap<>();
  }

  public static EventBus getInstance() {
    if (sInstance == null) {
      synchronized (EventBus.class) {
        if (sInstance == null) {
          sInstance = new EventBus();
        }
      }
    }
    return sInstance;
  }
  //</editor-fold>

  //<editor-fold desc="API">

  /**
   * 注册
   *
   * @param subscriber 订阅对象
   */
  public void register(Object subscriber) {
    if (subscriber == null) {
      return;
    }
    if (!findByIndex(subscriber)) {
      findByReflect(subscriber);
    }
  }

  /**
   * 反注册
   *
   * @param subscriber 反订阅对象
   */
  public void unregister(Object subscriber) {
    if (subscriber == null) {
      return;
    }
    if (!mSubscriptionsByTypeMap.isEmpty()) {
      Iterator<Map.Entry<Class<?>, List<Subscription>>> iterator =
          mSubscriptionsByTypeMap.entrySet().iterator();
      // 循环哈希表
      while (iterator.hasNext()) {
        Map.Entry<Class<?>, List<Subscription>> entry = iterator.next();
        // 循环列表
        Iterator<Subscription> subscriptionIterator = entry.getValue().iterator();
        while (subscriptionIterator.hasNext()) {
          // 从列表中移除订阅对象
          Subscription subscription = subscriptionIterator.next();
          if (subscription.subscriber.equals(subscriber)) {
            subscriptionIterator.remove();
          }
        }
        // 如果该类型没有订阅对象，移除
        if (entry.getValue().isEmpty()) {
          iterator.remove();
        }
      }
    }
  }

  /**
   * 方法调用
   *
   * @param object 对象
   */
  public void post(Object object) {
    if (object == null) {
      return;
    }
    if (!Looper.getMainLooper().equals(Looper.myLooper())) {
      mHandler.post(() -> doPost(object));
    } else {
      doPost(object);
    }
  }

  private void doPost(Object object) {
    if (!mSubscriptionsByTypeMap.isEmpty()) {
      List<Subscription> subscriptionList = mSubscriptionsByTypeMap.get(object.getClass());
      if (subscriptionList != null && !subscriptionList.isEmpty()) {
        for (Subscription subscription : subscriptionList) {
          try {
            subscription.method.invoke(subscription.subscriber, object);
          } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
          }
        }
      }
    }
  }
  //</editor-fold>

  //<editor-fold desc="私有方法">

  /**
   * 通过索引查找
   *
   * @param subscriber 订阅对象
   * @return 是否找到
   */
  private boolean findByIndex(Object subscriber) {
    ISubscriberIndex index = SUBSCRIBER_INDEX_MAP.get(subscriber.getClass());
    if (index != null
        && index.getSubscribedMethods() != null
        && !index.getSubscribedMethods().isEmpty()) {
    }
    return false;
  }

  /**
   * 通过反射查找
   *
   * @param subscriber 订阅对象
   * @return 是否找到
   */
  @SuppressWarnings("UnusedReturnValue")
  private boolean findByReflect(Object subscriber) {
    // 获取该类定义的方法
    Method[] methods = subscriber.getClass().getDeclaredMethods();
    boolean find = false;
    // 循环找出符合条件的方法
    for (Method method : methods) {
      // 1. 判断是否有 SubscribeOnMainThread 注解
      SubscribeOnMainThread annotation = method.getAnnotation(SubscribeOnMainThread.class);
      if (annotation != null) {
        // 2. 判断是否只有一个参数的方法
        Class<?>[] parameterTypes = method.getParameterTypes();
        if (parameterTypes.length == 1) {
          find = true;
          // 3. 添加记录者
          Class<?> parameter = parameterTypes[0];
          List<Subscription> subscriptionList = mSubscriptionsByTypeMap.get(parameter);
          if (subscriptionList == null) {
            subscriptionList = new LinkedList<>();
            mSubscriptionsByTypeMap.put(parameter, subscriptionList);
          }
          subscriptionList.add(new Subscription(subscriber, method));
        }
      }
    }
    return find;
  }
  //</editor-fold>

  //<editor-fold desc="索引相关方法">

  /**
   * 添加索引
   *
   * @param index 索引
   */
  public void addIndex(ISubscriberIndex index) {
    if (index != null) {
      SUBSCRIBER_INDEX_MAP.put(index.getSubscriberType(), index);
    }
  }
  //</editor-fold>
}
