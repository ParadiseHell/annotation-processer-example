package com.paradisehell.example;

import android.app.Application;
import com.paradisehell.api.EventBus;

/**
 * Description
 *
 * @author ChengTao <a href="mailto:tao@paradisehell.org">Contact me.</a>
 * @since 2019/11/21 01:07
 */
public final class ExampleApplication extends Application {
  @Override
  public void onCreate() {
    super.onCreate();
    EventBus.getInstance().addIndex(new TestIndex());
  }
}
