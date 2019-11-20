package com.paradisehell.example;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.paradisehell.api.EventBus;
import com.paradisehell.api.annotation.SubscribeOnMainThread;

public final class MainActivity extends AppCompatActivity {
  private static final String TAG = MainActivity.class.getSimpleName();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    EventBus.getInstance().register(this);
    setContentView(R.layout.activity_main);
    findViewById(R.id.button_call_on_child_thread).setOnClickListener(
        v -> AsyncTask.THREAD_POOL_EXECUTOR.execute(() -> {
          Log.w(TAG, "post thread : " + Thread.currentThread().getName());
          EventBus.getInstance().post("This is a string.");
        })
    );
    findViewById(R.id.button_call_on_main_thread).setOnClickListener(v -> {
      Log.w(TAG, "post thread : " + Thread.currentThread().getName());
      EventBus.getInstance().post(1);
    });
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    EventBus.getInstance().unregister(this);
  }

  @SubscribeOnMainThread
  public void postString(@NonNull String string) {
    Log.w(TAG, "''\n"
        + "Method : postString\n"
        + "Thread : " + Thread.currentThread().getName() + "\n"
        + "data : " + string + "\n"
    );
  }

  @SubscribeOnMainThread
  public void postInteger(@NonNull Integer integer) {
    Log.w(TAG, "''\n"
        + "Method : postInteger\n"
        + "Thread : " + Thread.currentThread().getName() + "\n"
        + "data : " + integer + "\n"
    );
  }
}
