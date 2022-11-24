package com.doubleclick.greenrobot.android;

import android.os.Looper;

import com.doubleclick.greenrobot.EventBus;
import com.doubleclick.greenrobot.HandlerPoster;
import com.doubleclick.greenrobot.MainThreadSupport;
import com.doubleclick.greenrobot.Poster;

public class DefaultAndroidMainThreadSupport implements MainThreadSupport {

    @Override
    public boolean isMainThread() {
        return Looper.getMainLooper() == Looper.myLooper();
    }

    @Override
    public Poster createPoster(EventBus eventBus) {
        return new HandlerPoster(eventBus, Looper.getMainLooper(), 10);
    }
}
